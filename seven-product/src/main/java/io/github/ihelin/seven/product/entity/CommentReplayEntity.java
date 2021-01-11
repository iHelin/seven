package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_comment_replay
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_comment_replay")
public class CommentReplayEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ÆÀÂÛid
    */
    private Long commentId;
    /**
    * »Ø¸´id
    */
    private Long replyId;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getCommentId(){
        return commentId;
    }

    public void setCommentId(Long commentId){
        this.commentId = commentId;
    }

    public Long getReplyId(){
        return replyId;
    }

    public void setReplyId(Long replyId){
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        return "CommentReplayEntity{" +
        "id='" + id + '\'' +
        "commentId='" + commentId + '\'' +
        "replyId='" + replyId + '\'' +
        '}';
    }

}
