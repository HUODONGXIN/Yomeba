package com.java2nb.system.domain;

import java.io.Serializable;



/**
 * 部門管理
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-09-27 14:28:36
 */
public class DeptDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long deptId;
	//親部門ID、最上位部門は0
	private Long parentId;
	//部門名
	private String name;
	//並び順
	private Integer orderNum;
	//削除フラグ  -1：削除済み  0：正常
	private Integer delFlag;

	/**
	 * 設定：
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 取得：
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 設定：親部門ID、最上位部門は0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 取得：親部門ID、最上位部門は0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 設定：部門名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 取得：部門名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 設定：並び順
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 取得：並び順
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 設定：削除フラグ  -1：削除済み  0：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 取得：削除フラグ  -1：削除済み  0：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	@Override
	public String toString() {
		return "DeptDO{" +
				"deptId=" + deptId +
				", parentId=" + parentId +
				", name='" + name + '\'' +
				", orderNum=" + orderNum +
				", delFlag=" + delFlag +
				'}';
	}
}
