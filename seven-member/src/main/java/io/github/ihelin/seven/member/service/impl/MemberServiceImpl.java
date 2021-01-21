package io.github.ihelin.seven.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ihelin.seven.common.utils.HttpUtils;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.member.dao.MemberDao;
import io.github.ihelin.seven.member.dao.MemberLevelDao;
import io.github.ihelin.seven.member.entity.MemberEntity;
import io.github.ihelin.seven.member.entity.MemberLevelEntity;
import io.github.ihelin.seven.member.exception.PhoneExistException;
import io.github.ihelin.seven.member.exception.UserNameExistException;
import io.github.ihelin.seven.member.service.MemberService;
import io.github.ihelin.seven.member.vo.SocialUser;
import io.github.ihelin.seven.member.vo.UserLoginVo;
import io.github.ihelin.seven.member.vo.UserRegisterVo;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberLevelDao memberLevelDao;
    @Autowired
    private ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
            new Query<MemberEntity>().getPage(params),
            new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(UserRegisterVo userRegisterVo) {
        MemberEntity entity = new MemberEntity();
        // 设置默认等级
        MemberLevelEntity memberLevelEntity = memberLevelDao.getDefaultLevel();
        entity.setLevelId(memberLevelEntity.getId());

        // 检查手机号 用户名是否唯一
        checkPhone(userRegisterVo.getPhone());
        checkUserName(userRegisterVo.getUserName());

        entity.setMobile(userRegisterVo.getPhone());
        entity.setUsername(userRegisterVo.getUserName());

        // 密码要加密存储
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        entity.setPassword(bCryptPasswordEncoder.encode(userRegisterVo.getPassword()));
        // 其他的默认信息
        entity.setCity("湖南 长沙");
        entity.setCreateTime(new Date());
        entity.setStatus(0);
        entity.setNickname(userRegisterVo.getUserName());
        entity.setBirth(new Date());
        entity.setEmail("xxx@gmail.com");
        entity.setGender(1);
        entity.setJob("JAVA");
        baseMapper.insert(entity);
    }

    @Override
    public MemberEntity login(UserLoginVo userLoginVo) {
        String loginacct = userLoginVo.getLoginacct();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // 去数据库查询
        MemberEntity entity = this.baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq("username", loginacct).or().eq("mobile", loginacct));
        if (entity == null) {
            // 登录失败
            return null;
        } else {
            // 前面传一个明文密码 后面传一个编码后的密码
            boolean matches = bCryptPasswordEncoder.matches(userLoginVo.getPassword(), entity.getPassword());
            if (matches) {
                entity.setPassword(null);
                return entity;
            } else {
                return null;
            }
        }
    }

    @Override
    public MemberEntity login(SocialUser socialUser) {
        // 微博的uid
        String uid = socialUser.getUid();
        // 1.判断社交用户登录过系统
        MemberEntity entity = baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq("social_uid", uid));

        MemberEntity memberEntity = new MemberEntity();
        if (entity != null) {
            // 说明这个用户注册过, 修改它的资料
            memberEntity.setId(entity.getId());
            memberEntity.setAccessToken(socialUser.getAccessToken());
            memberEntity.setExpiresIn(socialUser.getExpiresIn());
            // 更新
            baseMapper.updateById(memberEntity);
            entity.setAccessToken(socialUser.getAccessToken());
            entity.setExpiresIn(socialUser.getExpiresIn());
            entity.setPassword(null);
            return entity;
        } else {
            // 2. 没有查到当前社交用户对应的记录 我们就需要注册一个
            HashMap<String, String> map = new HashMap<>();
            map.put("access_token", socialUser.getAccessToken());
            map.put("uid", socialUser.getUid());
            try {
                HttpResponse response = HttpUtils.doGet("https://api.weibo.com", "/2/users/show.json", "get", new HashMap<>(), map);
                // 3. 查询当前社交用户账号信息(昵称、性别等)
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 查询成功
                    String json = EntityUtils.toString(response.getEntity());
                    Map<String, Object> jsonObject = objectMapper.readValue(json, Map.class);
                    memberEntity.setNickname(jsonObject.get("name").toString());
                    memberEntity.setUsername(jsonObject.get("name").toString());
                    memberEntity.setGender("m".equals(jsonObject.get("gender").toString()) ? 1 : 0);
                    memberEntity.setCity(jsonObject.get("location").toString());
                    memberEntity.setJob("");
                }
            } catch (Exception e) {
                logger.warn("社交登录时远程调用出错 [尝试修复]",e);
            }
            memberEntity.setStatus(0);
            memberEntity.setCreateTime(new Date());
            memberEntity.setBirth(new Date());
            memberEntity.setLevelId(1L);
            memberEntity.setSocialUid(socialUser.getUid());
            memberEntity.setAccessToken(socialUser.getAccessToken());
            memberEntity.setExpiresIn(socialUser.getExpiresIn());

            // 注册 -- 登录成功
            baseMapper.insert(memberEntity);
            memberEntity.setPassword(null);
            return memberEntity;
        }
    }

    public void checkPhone(String phone) throws PhoneExistException {
        if (this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone)) > 0) {
            throw new PhoneExistException();
        }
    }

    public void checkUserName(String username) throws UserNameExistException {
        if (this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", username)) > 0) {
            throw new UserNameExistException();
        }
    }

}