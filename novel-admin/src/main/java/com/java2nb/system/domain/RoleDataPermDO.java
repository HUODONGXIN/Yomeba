package com.java2nb.system.domain;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * ロールとデータ権限の対応関係
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-11-25 11:32:49
 */
public class RoleDataPermDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//
		//JavaのlongはJSのnumberより表現範囲が広く、一部の数値はJSで保持できない(不正確な値になる)
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long id;
	//ロールID
		//JavaのlongはJSのnumberより表現範囲が広く、一部の数値はJSで保持できない(不正確な値になる)
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long roleId;
	//権限ID
		//JavaのlongはJSのnumberより表現範囲が広く、一部の数値はJSで保持できない(不正確な値になる)
	//そのため文字列にシリアライズして解決する
	@JsonSerialize(using = LongToStringSerializer.class)
			private Long permId;

	/**
	 * 設定：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 取得：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 設定：ロールID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 取得：ロールID
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * 設定：権限ID
	 */
	public void setPermId(Long permId) {
		this.permId = permId;
	}
	/**
	 * 取得：権限ID
	 */
	public Long getPermId() {
		return permId;
	}
}
