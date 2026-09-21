package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * ニュースカテゴリテーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 10:03:41
 */
public class CategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//主キー
			private Integer id;
	//ジャンル名
			private String name;
	//並び順
			private Integer sort;
	//作成者ID
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long createUserId;
	//作成日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createTime;
	//更新者ID
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long updateUserId;
	//更新日時
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date updateTime;

	/**
	 * 設定：主キー
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 取得：主キー
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 設定：ジャンル名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 取得：ジャンル名
	 */
	public String getName() {
		return name;
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
