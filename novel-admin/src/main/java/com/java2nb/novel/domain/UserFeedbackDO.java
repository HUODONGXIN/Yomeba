package com.java2nb.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-18 11:08:54
 */
public class UserFeedbackDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //主キーID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //ユーザーID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long userId;
    //フィードバック内容
    private String content;
    //フィードバック日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 設定：主キーID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得：主キーID
     */
    public Long getId() {
        return id;
    }

    /**
     * 設定：ユーザーID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 取得：ユーザーID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 設定：フィードバック内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 取得：フィードバック内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 設定：フィードバック日時
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得：フィードバック日時
     */
    public Date getCreateTime() {
        return createTime;
    }
}
