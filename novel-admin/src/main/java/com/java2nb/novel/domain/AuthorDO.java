package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 作家テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-05-13 11:16:51
 */
public class AuthorDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//主キー
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//ユーザーID
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long userId;
	//ログインアカウント（user テーブルと紐付け）
			private String username;
	//ログインパスワード（MD5 で暗号化して保存）
			private String password;
	//招待コード
			private String inviteCode;
	//ペンネーム
			private String penName;
	//電話番号
			private String telPhone;
	//QQ または WeChat のアカウント
			private String chatAccount;
	//メールアドレス
			private String email;
	//作品の方向性。0：男性向け、1：女性向け
			private Integer workDirection;
	//作成日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;
	//0：正常、1：利用停止
			private Integer status;

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
	 * 設定：ログインアカウント
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 取得：ログインアカウント
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
	 * 設定：ペンネーム
	 */
	public void setPenName(String penName) {
		this.penName = penName;
	}
	/**
	 * 取得：ペンネーム
	 */
	public String getPenName() {
		return penName;
	}
	/**
	 * 設定：電話番号
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	/**
	 * 取得：電話番号
	 */
	public String getTelPhone() {
		return telPhone;
	}
	/**
	 * 設定：QQ または WeChat のアカウント
	 */
	public void setChatAccount(String chatAccount) {
		this.chatAccount = chatAccount;
	}
	/**
	 * 取得：QQ または WeChat のアカウント
	 */
	public String getChatAccount() {
		return chatAccount;
	}
	/**
	 * 設定：メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 取得：メールアドレス
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 設定：作品の方向性。0：男性向け、1：女性向け
	 */
	public void setWorkDirection(Integer workDirection) {
		this.workDirection = workDirection;
	}
	/**
	 * 取得：作品の方向性。0：男性向け、1：女性向け
	 */
	public Integer getWorkDirection() {
		return workDirection;
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
	 * 設定：0：正常、1：利用停止
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 取得：0：正常、1：利用停止
	 */
	public Integer getStatus() {
		return status;
	}
}
