package com.java2nb.common.domain;

import java.io.Serializable;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import lombok.Data;


/**
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-11-22 18:03:46
 */
public class GenColumnsDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //主キー
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //テーブル名
    private String tableName;
    //カラム名
    private String columnName;
    //カラム型
    private String columnType;
    //マッピングする Java 型
    private String javaType;
    //カラムコメント
    private String columnComment;
    //カラムの並び順（昇順）
    private Integer columnSort;
    //カラムのラベル名
    private String columnLabel;
    //画面表示タイプ：1、テキストボックス 2、ドロップダウン 3、数値 4、日付 5、テキストエリア 6、リッチテキスト 7、画像アップロード【単一ファイル】 8、画像アップロード【複数ファイル】 9、ファイルアップロード【単一ファイル】 10、ファイルアップロード【複数ファイル】 11、非表示フィールド 12、非表示
    private Integer pageType;
    //必須入力かどうか
    private Integer isRequired;
    //画面表示がドロップダウンの場合に使用。データ辞書タイプは辞書テーブルから取得する
    private String dictType;

    // 属性名（先頭大文字）。例：user_name => UserName
    private String attrName;
    // 属性名（先頭小文字）。例：user_name => userName
    private String attrname;

    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public Integer getColumnSort() {
        return columnSort;
    }

    public void setColumnSort(Integer columnSort) {
        this.columnSort = columnSort;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }
}
