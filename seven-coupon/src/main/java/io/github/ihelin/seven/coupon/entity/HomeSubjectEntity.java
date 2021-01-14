package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* sms_home_subject
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_home_subject")
public class HomeSubjectEntity implements Serializable {

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
    * ×¨Ìâ±êÌâ
    */
    private String title;
    /**
    * ×¨Ìâ¸±±êÌâ
    */
    private String subTitle;
    /**
    * ÏÔÊ¾×´Ì¬
    */
    private Integer status;
    /**
    * ÏêÇéÁ¬½Ó
    */
    private String url;
    /**
    * ÅÅÐò
    */
    private Integer sort;
    /**
    * ×¨ÌâÍ¼Æ¬µØÖ·
    */
    private String img;


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

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getSubTitle(){
        return subTitle;
    }

    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public String getImg(){
        return img;
    }

    public void setImg(String img){
        this.img = img;
    }

    @Override
    public String toString() {
        return "HomeSubjectEntity{" +
        "id='" + id + '\'' +
        "name='" + name + '\'' +
        "title='" + title + '\'' +
        "subTitle='" + subTitle + '\'' +
        "status='" + status + '\'' +
        "url='" + url + '\'' +
        "sort='" + sort + '\'' +
        "img='" + img + '\'' +
        '}';
    }

}
