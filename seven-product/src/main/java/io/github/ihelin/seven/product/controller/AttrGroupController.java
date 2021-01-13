package io.github.ihelin.seven.product.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.product.entity.AttrEntity;
import io.github.ihelin.seven.product.entity.AttrGroupEntity;
import io.github.ihelin.seven.product.service.AttrGroupService;
import io.github.ihelin.seven.product.service.AttrService;
import io.github.ihelin.seven.product.service.CategoryService;
import io.github.ihelin.seven.product.vo.AttrGroupWithAttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * pms_attr_group
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-11 11:52:41
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @GetMapping("/{attrGroupId}/attr/relation")
    public R attrRelation(@PathVariable Long attrGroupId) {
        List<AttrEntity> entities = attrService.getRelationAttr(attrGroupId);
        return R.ok().put("data", entities);
    }

    @GetMapping("/{attrGroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable Long attrGroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrGroupId);
        return R.ok().put("data", page);
    }

    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable Long catelogId) {

        List<AttrGroupWithAttrVo> groupVos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().putData(groupVos);
    }

    /**
     * 列表
     */
    @GetMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable() Integer catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        List<Long> ids = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogIds(ids);
        return R.ok().put("data", attrGroup);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
