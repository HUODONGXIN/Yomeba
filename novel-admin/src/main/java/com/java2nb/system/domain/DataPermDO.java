package com.java2nb.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * データ権限管理
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-11-25 11:40:03
 */
public class DataPermDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //
    //JavaのlongはJSのnumberより表現範囲が広く、一部の数値はJSで保持できない(不正確な値になる)
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //権限名
    private String name;
    //テーブル名
    private String tableName;
    //所属モジュール
    private String moduleName;
    //ユーザー権限制御属性名
    private String crlAttrName;
    //テーブル権限制御カラム名
    private String crlColumnName;
    //権限コード、all_プレフィックスは全データ参照権限、sup_プレフィックスは配下データ参照権限、own_プレフィックスは自データ参照権限を表す
    private String permCode;
    //並び順
    private Integer orderNum;
    //作成日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    //更新日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

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
     * 設定：権限名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得：権限名
     */
    public String getName() {
        return name;
    }

    /**
     * 設定：テーブル名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 取得：テーブル名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 設定：所属モジュール
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * 取得：所属モジュール
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 設定：ユーザー権限制御属性名
     */
    public void setCrlAttrName(String crlAttrName) {
        this.crlAttrName = crlAttrName;
    }

    /**
     * 取得：ユーザー権限制御属性名
     */
    public String getCrlAttrName() {
        return crlAttrName;
    }

    /**
     * 設定：テーブル権限制御カラム名
     */
    public void setCrlColumnName(String crlColumnName) {
        this.crlColumnName = crlColumnName;
    }

    /**
     * 取得：テーブル権限制御カラム名
     */
    public String getCrlColumnName() {
        return crlColumnName;
    }

    /**
     * 設定：権限コード、all_プレフィックスは全データ参照権限、sup_プレフィックスは配下データ参照権限、own_プレフィックスは自データ参照権限を表す
     */
    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    /**
     * 取得：権限コード、all_プレフィックスは全データ参照権限、sup_プレフィックスは配下データ参照権限、own_プレフィックスは自データ参照権限を表す
     */
    public String getPermCode() {
        return permCode;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataPermDO that = (DataPermDO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
            && Objects.equals(tableName, that.tableName) && Objects.equals(moduleName, that.moduleName)
            && Objects.equals(crlAttrName, that.crlAttrName) && Objects.equals(crlColumnName,
            that.crlColumnName) && Objects.equals(permCode, that.permCode) && Objects.equals(orderNum,
            that.orderNum) && Objects.equals(gmtCreate, that.gmtCreate) && Objects.equals(gmtModified,
            that.gmtModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tableName, moduleName, crlAttrName, crlColumnName, permCode, orderNum, gmtCreate,
            gmtModified);
    }
}
