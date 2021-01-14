package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* oms_order_return_reason
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@TableName("oms_order_return_reason")
public class OrderReturnReasonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ÍË»õÔ­ÒòÃû
    */
    private String name;
    /**
    * ÅÅÐò
    */
    private Integer sort;
    /**
    * ÆôÓÃ×´Ì¬
    */
    private Integer status;
    /**
    * create_time
    */
    private Date createTime;


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

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderReturnReasonEntity{" +
        "id='" + id + '\'' +
        "name='" + name + '\'' +
        "sort='" + sort + '\'' +
        "status='" + status + '\'' +
        "createTime='" + createTime + '\'' +
        '}';
    }

}
