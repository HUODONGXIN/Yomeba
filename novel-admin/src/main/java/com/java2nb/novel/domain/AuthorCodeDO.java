package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 作家招待コードテーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-05-13 11:29:15
 */
public class AuthorCodeDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//主キー
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//招待コード
			private String inviteCode;
	//有効期限
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date validityTime;
	//使用済みかどうか。0：未使用、1：使用済み
			private Integer isUse;
	//作成日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;
	//作成者ID
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long createUserId;

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
	 * 設定：招待コード
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 取得：招待コード
	 */
	public String getInviteCode() {
		return inviteCode;
	}
	/**
	 * 設定：有効期限
	 */
	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}
	/**
	 * 取得：有効期限
	 */
	public Date getValidityTime() {
		return validityTime;
	}
	/**
	 * 設定：使用済みかどうか。0：未使用、1：使用済み
	 */
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	/**
	 * 取得：使用済みかどうか。0：未使用、1：使用済み
	 */
	public Integer getIsUse() {
		return isUse;
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
}
