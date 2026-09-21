package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 
 * 
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:08
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	//主キー
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//ログイン名
			private String username;
	//アカウントコード
			private String account;
	//ログインパスワード
			private String password;
	//ニックネーム
			private String nickName;
	//ユーザーアバター
			private String userPhoto;
	//ユーザーの性別。0：男、1：女
			private Integer userSex;
	//アカウント残高
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long accountBalance;
	//ユーザーステータス。0：正常
			private Integer status;
	//作成日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;
	//更新日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date updateTime;

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
	 * 設定：ログイン名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() { return account; }
	public void setAccount(String account) { this.account = account; }
	/**
	 * 取得：ログイン名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 設定：ログインパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 取得：ログインパスワード
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 設定：ニックネーム
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 取得：ニックネーム
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 設定：ユーザーアバター
	 */
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	/**
	 * 取得：ユーザーアバター
	 */
	public String getUserPhoto() {
		return userPhoto;
	}
	/**
	 * 設定：ユーザーの性別。0：男、1：女
	 */
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	/**
	 * 取得：ユーザーの性別。0：男、1：女
	 */
	public Integer getUserSex() {
		return userSex;
	}
	/**
	 * 設定：アカウント残高
	 */
	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}
	/**
	 * 取得：アカウント残高
	 */
	public Long getAccountBalance() {
		return accountBalance;
	}
	/**
	 * 設定：ユーザーステータス。0：正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 取得：ユーザーステータス。0：正常
	 */
	public Integer getStatus() {
		return status;
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
