package com.java2nb.common.domain;

/**
 * カラムの属性
 *
 *
 */
public class ColumnDO {
	// カラム名
	private String columnName;
	// カラムの型
	private String dataType;
	// カラムのコメント
	private String comments;

	// 属性名（先頭大文字）。例：user_name => UserName
	private String attrName;
	// 属性名（先頭小文字）。例：user_name => userName
	private String attrname;
	// 属性の型
	private String attrType;
	// auto_increment
	private String extra;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return "ColumnDO{" +
				"columnName='" + columnName + '\'' +
				", dataType='" + dataType + '\'' +
				", comments='" + comments + '\'' +
				", attrName='" + attrName + '\'' +
				", attrname='" + attrname + '\'' +
				", attrType='" + attrType + '\'' +
				", extra='" + extra + '\'' +
				'}';
	}
}
