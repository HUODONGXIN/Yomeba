package com.java2nb.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 小説コメントテーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 21:59:28
 */
public class BookCommentDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //主キー
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //小説ID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long bookId;
    //コメント内容
    private String commentContent;
    //返信件数
    private Integer replyCount;
    //審査ステータス。0：審査待ち、1：審査通過、2：審査却下
    private Integer auditStatus;
    //コメント日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //コメントしたユーザー
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long createUserId;

    private String bookName;

    private String userName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 設定：主キー
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得：主キー
     */
    public Long getId() {
        return id;
    }

    /**
     * 設定：小説ID
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 取得：小説ID
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 設定：コメント内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 取得：コメント内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 設定：返信件数
     */
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 取得：返信件数
     */
    public Integer getReplyCount() {
        return replyCount;
    }

    /**
     * 設定：審査ステータス。0：審査待ち、1：審査通過、2：審査却下
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 取得：審査ステータス。0：審査待ち、1：審査通過、2：審査却下
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 設定：コメント日時
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得：コメント日時
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 設定：コメントしたユーザー
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 取得：コメントしたユーザー
     */
    public Long getCreateUserId() {
        return createUserId;
    }
}
