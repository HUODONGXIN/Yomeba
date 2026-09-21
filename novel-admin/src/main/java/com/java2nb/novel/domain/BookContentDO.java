package com.java2nb.novel.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 小説本文テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 19:52:06
 */
public class BookContentDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//主キー
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//目次ID
		//Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long indexId;
	//小説の話の内容
			private String content;

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
	 * 設定：目次ID
	 */
	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}
	/**
	 * 取得：目次ID
	 */
	public Long getIndexId() {
		return indexId;
	}
	/**
	 * 設定：小説の話の内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 取得：小説の話の内容
	 */
	public String getContent() {
		return content;
	}
}
