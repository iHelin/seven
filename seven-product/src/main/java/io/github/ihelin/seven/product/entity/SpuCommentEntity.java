package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* pms_spu_comment
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:40
*/
@TableName("pms_spu_comment")
public class SpuCommentEntity implements Serializable {
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
    * spu_id
    */
    private Long spuId;
    /**
    * ÉÌÆ·Ãû×Ö
    */
    private String spuName;
    /**
    * »áÔ±êÇ³Æ
    */
    private String memberNickName;
    /**
    * ÐÇ¼¶
    */
    private Integer star;
    /**
    * »áÔ±ip
    */
    private String memberIp;
    /**
    * ´´½¨Ê±¼ä
    */
    private Date createTime;
    /**
    * ÏÔÊ¾×´Ì¬[0-²»ÏÔÊ¾£¬1-ÏÔÊ¾]
    */
    private Integer showStatus;
    /**
    * ¹ºÂòÊ±ÊôÐÔ×éºÏ
    */
    private String spuAttributes;
    /**
    * µãÔÞÊý
    */
    private Integer likesCount;
    /**
    * »Ø¸´Êý
    */
    private Integer replyCount;
    /**
    * ÆÀÂÛÍ¼Æ¬/ÊÓÆµ[jsonÊý¾Ý£»[{type:ÎÄ¼þÀàÐÍ,url:×ÊÔ´Â·¾¶}]]
    */
    private String resources;
    /**
    * ÄÚÈÝ
    */
    private String content;
    /**
    * ÓÃ»§Í·Ïñ
    */
    private String memberIcon;
    /**
    * ÆÀÂÛÀàÐÍ[0 - ¶ÔÉÌÆ·µÄÖ±½ÓÆÀÂÛ£¬1 - ¶ÔÆÀÂÛµÄ»Ø¸´]
    */
    private Integer commentType;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public Long getSpuId(){
        return spuId;
    }

    public void setSpuId(Long spuId){
        this.spuId = spuId;
    }

    public String getSpuName(){
        return spuName;
    }

    public void setSpuName(String spuName){
        this.spuName = spuName;
    }

    public String getMemberNickName(){
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName){
        this.memberNickName = memberNickName;
    }

    public Integer getStar(){
        return star;
    }

    public void setStar(Integer star){
        this.star = star;
    }

    public String getMemberIp(){
        return memberIp;
    }

    public void setMemberIp(String memberIp){
        this.memberIp = memberIp;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Integer getShowStatus(){
        return showStatus;
    }

    public void setShowStatus(Integer showStatus){
        this.showStatus = showStatus;
    }

    public String getSpuAttributes(){
        return spuAttributes;
    }

    public void setSpuAttributes(String spuAttributes){
        this.spuAttributes = spuAttributes;
    }

    public Integer getLikesCount(){
        return likesCount;
    }

    public void setLikesCount(Integer likesCount){
        this.likesCount = likesCount;
    }

    public Integer getReplyCount(){
        return replyCount;
    }

    public void setReplyCount(Integer replyCount){
        this.replyCount = replyCount;
    }

    public String getResources(){
        return resources;
    }

    public void setResources(String resources){
        this.resources = resources;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getMemberIcon(){
        return memberIcon;
    }

    public void setMemberIcon(String memberIcon){
        this.memberIcon = memberIcon;
    }

    public Integer getCommentType(){
        return commentType;
    }

    public void setCommentType(Integer commentType){
        this.commentType = commentType;
    }

    @Override
    public String toString() {
        return "SpuCommentEntity{" +
        "id='" + id + '\'' +
        "skuId='" + skuId + '\'' +
        "spuId='" + spuId + '\'' +
        "spuName='" + spuName + '\'' +
        "memberNickName='" + memberNickName + '\'' +
        "star='" + star + '\'' +
        "memberIp='" + memberIp + '\'' +
        "createTime='" + createTime + '\'' +
        "showStatus='" + showStatus + '\'' +
        "spuAttributes='" + spuAttributes + '\'' +
        "likesCount='" + likesCount + '\'' +
        "replyCount='" + replyCount + '\'' +
        "resources='" + resources + '\'' +
        "content='" + content + '\'' +
        "memberIcon='" + memberIcon + '\'' +
        "commentType='" + commentType + '\'' +
        '}';
    }

}
