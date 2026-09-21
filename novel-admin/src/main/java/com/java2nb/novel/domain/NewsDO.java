package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * ニューステーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 10:05:51
 */
public class NewsDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//主キー
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//カテゴリID
			private Integer catId;
	//ジャンル名
			private String catName;
	//ソース
			private String sourceName;
	//タイトル
			private String title;
	//内容
			private String content;
	//公開日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;
	//公開者ID
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
	 * 設定：カテゴリID
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	/**
	 * 取得：カテゴリID
	 */
	public Integer getCatId() {
		return catId;
	}
	/**
	 * 設定：ジャンル名
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * 取得：ジャンル名
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * 設定：ソース
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * 取得：ソース
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * 設定：タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 取得：タイトル
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 設定：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 取得：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 設定：公開日時
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 取得：公開日時
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 設定：公開者ID
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 取得：公開者ID
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
