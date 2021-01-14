package io.github.ihelin.seven.product.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.product.entity.ProductAttrValueEntity;
import io.github.ihelin.seven.product.service.AttrService;
import io.github.ihelin.seven.product.service.ProductAttrValueService;
import io.github.ihelin.seven.product.vo.AttrRespVo;
import io.github.ihelin.seven.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * pms_attr
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService productAttrValueService;

    @GetMapping("/base/listforspu/{spuId}")
    public R attrListForSpu(@PathVariable Long spuId) {

        List<ProductAttrValueEntity> data = productAttrValueService.attrListForSpu(spuId);
        return R.ok().put("data", data);
    }

    @GetMapping("/{attrType}/list/{catalogId}")
    public R baseAttrList(@PathVariable String attrType,
                          @PathVariable Long catalogId,
                          @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryBaseAttrPage(catalogId, params, attrType);
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
//        AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attr = attrService.getAttrInfo(attrId);

        return R.ok().put("data", attr);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateAttr(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities) {
        productAttrValueService.updateSpuAttr(spuId, entities);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
