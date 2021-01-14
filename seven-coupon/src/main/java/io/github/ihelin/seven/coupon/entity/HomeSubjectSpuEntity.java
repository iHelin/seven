package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* sms_home_subject_spu
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_home_subject_spu")
public class HomeSubjectSpuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ×¨ÌâÃû×Ö
    */
    private String name;
    /**
    * ×¨Ìâid
    */
    private Long subjectId;
    /**
    * spu_id
    */
    private Long spuId;
    /**
    * ÅÅÐò
    */
    private Integer sort;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(Long subjectId){
        this.subjectId = subjectId;
    }

    public Long getSpuId(){
        return spuId;
    }

    public void setSpuId(Long spuId){
        this.spuId = spuId;
    }

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "HomeSubjectSpuEntity{" +
        "id='" + id + '\'' +
        "name='" + name + '\'' +
        "subjectId='" + subjectId + '\'' +
        "spuId='" + spuId + '\'' +
        "sort='" + sort + '\'' +
        '}';
    }

}
