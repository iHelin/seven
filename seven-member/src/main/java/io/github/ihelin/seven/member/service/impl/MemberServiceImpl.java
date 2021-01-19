package io.github.ihelin.seven.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.member.dao.MemberDao;
import io.github.ihelin.seven.member.dao.MemberLevelDao;
import io.github.ihelin.seven.member.entity.MemberEntity;
import io.github.ihelin.seven.member.entity.MemberLevelEntity;
import io.github.ihelin.seven.member.exception.PhoneExistException;
import io.github.ihelin.seven.member.exception.UserNameExistException;
import io.github.ihelin.seven.member.service.MemberService;
import io.github.ihelin.seven.member.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberLevelDao memberLevelDao;

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

    public void checkPhone(String phone) throws PhoneExistException{
        if(this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone)) > 0){
            throw new PhoneExistException();
        }
    }

    public void checkUserName(String username) throws UserNameExistException{
        if(this.baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", username)) > 0){
            throw new UserNameExistException();
        }
    }

}