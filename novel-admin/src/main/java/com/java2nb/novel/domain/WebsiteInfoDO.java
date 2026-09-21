package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * サイト情報テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 11:05:43
 */
public class WebsiteInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//主キー
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//サイト名
			private String name;
	//サイトドメイン
			private String domain;
	//SEOキーワード
			private String keyword;
	//サイトの説明
			private String description;
	//運営者QQ
			private String qq;
	//サイトロゴ画像（デフォルト）
			private String logo;
	//サイトロゴ画像（ダーク）
			private String logoDark;
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
	 * 設定：サイト名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 取得：サイト名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 設定：サイトドメイン
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * 取得：サイトドメイン
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * 設定：SEOキーワード
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 取得：SEOキーワード
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 設定：サイトの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 取得：サイトの説明
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 設定：運営者QQ
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 取得：運営者QQ
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 設定：サイトロゴ画像（デフォルト）
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 取得：サイトロゴ画像（デフォルト）
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * 設定：サイトロゴ画像（ダーク）
	 */
	public void setLogoDark(String logoDark) {
		this.logoDark = logoDark;
	}
	/**
	 * 取得：サイトロゴ画像（ダーク）
	 */
	public String getLogoDark() {
		return logoDark;
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
