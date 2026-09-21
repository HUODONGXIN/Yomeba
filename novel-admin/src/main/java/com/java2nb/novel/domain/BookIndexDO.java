package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 小説目次テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 19:51:54
 */
public class BookIndexDO implements Serializable {
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
	//話番号
			private Integer indexNum;
	//話タイトル
			private String indexName;
	//文字数
			private Integer wordCount;
	//有料かどうか。1：有料、0：無料
			private Integer isVip;
	//話の料金（yomiコイン）
			private Integer bookPrice;
	//保存方式
			private String storageType;
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
	 * 設定：話番号
	 */
	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}
	/**
	 * 取得：話番号
	 */
	public Integer getIndexNum() {
		return indexNum;
	}
	/**
	 * 設定：話タイトル
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	/**
	 * 取得：話タイトル
	 */
	public String getIndexName() {
		return indexName;
	}
	/**
	 * 設定：文字数
	 */
	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}
	/**
	 * 取得：文字数
	 */
	public Integer getWordCount() {
		return wordCount;
	}
	/**
	 * 設定：有料かどうか。1：有料、0：無料
	 */
	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	/**
	 * 取得：有料かどうか。1：有料、0：無料
	 */
	public Integer getIsVip() {
		return isVip;
	}
	/**
	 * 設定：話の料金（yomiコイン）
	 */
	public void setBookPrice(Integer bookPrice) {
		this.bookPrice = bookPrice;
	}
	/**
	 * 取得：話の料金（yomiコイン）
	 */
	public Integer getBookPrice() {
		return bookPrice;
	}
	/**
	 * 設定：保存方式
	 */
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	/**
	 * 取得：保存方式
	 */
	public String getStorageType() {
		return storageType;
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
