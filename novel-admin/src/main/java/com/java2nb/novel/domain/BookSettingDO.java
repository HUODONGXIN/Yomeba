package com.java2nb.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * ホーム画面小説設定テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-18 10:01:13
 */
public class BookSettingDO implements Serializable {

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
    //並び順
    private Integer sort;
    //タイプ。0：カルーセル画像、1：上部小説欄の設定、2：今週のイチオシ、3：人気レコメンド、4：厳選レコメンド
    private Integer type;
    //作成日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //作成者ID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long createUserId;
    //更新日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //更新者ID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long updateUserId;

    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
     * 設定：並び順
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 取得：並び順
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 設定：タイプ。0：カルーセル画像、1：上部小説欄の設定、2：今週のイチオシ、3：人気レコメンド、4：厳選レコメンド
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 取得：タイプ。0：カルーセル画像、1：上部小説欄の設定、2：今週のイチオシ、3：人気レコメンド、4：厳選レコメンド
     */
    public Integer getType() {
        return type;
    }

    /**
     * 設定：作成日時
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得：作成日時
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 設定：作成者ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 取得：作成者ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 設定：更新日時
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得：更新日時
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 設定：更新者ID
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 取得：更新者ID
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }
}
