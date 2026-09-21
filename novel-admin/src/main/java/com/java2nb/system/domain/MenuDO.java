package com.java2nb.system.domain;

import java.io.Serializable;
import java.util.Date;

public class MenuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private Long menuId;
	// 親メニューID、最上位メニューは0
	private Long parentId;
	// メニュー名
	private String name;
	// メニューURL
	private String url;
	// 権限(複数はカンマ区切り、例: user:list,user:create)
	private String perms;
	// タイプ 0：ディレクトリ 1：メニュー 2：ボタン
	private Integer type;
	// メニューアイコン
	private String icon;
	// 並び順
	private Integer orderNum;
	// 作成日時
	private Date gmtCreate;
	// 更新日時
	private Date gmtModified;

	/**
	 * 設定：
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * 取得：
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * 設定：親メニューID、最上位メニューは0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 取得：親メニューID、最上位メニューは0
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 設定：メニュー名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得：メニュー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 設定：メニューURL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 取得：メニューURL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 設定：権限(複数はカンマ区切り、例: user:list,user:create)
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}

	/**
	 * 取得：権限(複数はカンマ区切り、例: user:list,user:create)
	 */
	public String getPerms() {
		return perms;
	}

	/**
	 * 設定：タイプ 0：ディレクトリ 1：メニュー 2：ボタン
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 取得：タイプ 0：ディレクトリ 1：メニュー 2：ボタン
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 設定：メニューアイコン
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 取得：メニューアイコン
	 */
	public String getIcon() {
		return icon;
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
	 * 設定：作成日時
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * 取得：作成日時
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * 設定：更新日時
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * 取得：更新日時
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	@Override
	public String toString() {
		return "MenuDO{" +
				"menuId=" + menuId +
				", parentId=" + parentId +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", perms='" + perms + '\'' +
				", type=" + type +
				", icon='" + icon + '\'' +
				", orderNum=" + orderNum +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				'}';
	}
}
