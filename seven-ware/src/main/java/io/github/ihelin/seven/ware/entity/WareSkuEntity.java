package io.github.ihelin.seven.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


/**
 * ÉÌÆ·¿â´æ
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */

@TableName("wms_ware_sku")
public class WareSkuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * ²Ö¿âid
     */
    private Long wareId;
    /**
     * ¿â´æÊý
     */
    private Integer stock;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * Ëø¶¨¿â´æ
     */
    private Integer stockLocked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getStockLocked() {
        return stockLocked;
    }

    public void setStockLocked(Integer stockLocked) {
        this.stockLocked = stockLocked;
    }
}
