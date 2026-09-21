package com.java2nb.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * チャージ注文
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:57
 */
public class PayDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //主キー
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //予約
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long outTradeNo;
    //注文番号
    private String tradeNo;
    //予約
    private Integer payChannel;
    //取引 yomiコイン数
    private Integer totalAmount;
    //支払ユーザーID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long userId;
    //支払ステータス。0：支払失敗、1：支払成功、2：支払待ち
    private Integer payStatus;
    //作成日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String userName;

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
     * 設定：予約
     */
    public void setOutTradeNo(Long outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 取得：予約
     */
    public Long getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 設定：注文番号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 取得：注文番号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 設定：予約
     */
    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    /**
     * 取得：予約
     */
    public Integer getPayChannel() {
        return payChannel;
    }

    /**
     * 設定：取引 yomiコイン数
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 取得：取引 yomiコイン数
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * 設定：支払ユーザーID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 取得：支払ユーザーID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 設定：支払ステータス。0：支払失敗、1：支払成功、2：支払待ち
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 取得：支払ステータス。0：支払失敗、1：支払成功、2：支払待ち
     */
    public Integer getPayStatus() {
        return payStatus;
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
}
