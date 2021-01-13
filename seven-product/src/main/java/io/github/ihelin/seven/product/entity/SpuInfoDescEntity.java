package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * spuÐÅÏ¢½éÉÜ
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-11 11:52:41
 */
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private Long spuId;
    private String decript;


    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getDecript() {
        return decript;
    }

    public void setDecript(String decript) {
        this.decript = decript;
    }

    @Override
    public String toString() {
        return "SpuInfoDescEntity{" +
                "spuId='" + spuId + '\'' +
                "decript='" + decript + '\'' +
                '}';
    }

}
