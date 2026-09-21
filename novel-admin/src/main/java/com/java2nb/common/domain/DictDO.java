package com.java2nb.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


/**
 * データ辞書テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-09-29 18:28:07
 */
public class DictDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //No.
    private Long id;
    //ラベル名
    private String name;
    //データ値
    private String value;
    //タイプ
    private String type;
    //説明
    private String description;
    //並び順（昇順）
    private BigDecimal sort;
    //親No.
    private Long parentId;
    //作成者
    private Integer createBy;
    //作成日時
    private Date createDate;
    //更新者
    private Long updateBy;
    //更新日時
    private Date updateDate;
    //備考
    private String remarks;
    //削除フラグ
    private String delFlag;

    /**
     * 設定：No.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得：No.
     */
    public Long getId() {
        return id;
    }

    /**
     * 設定：ラベル名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得：ラベル名
     */
    public String getName() {
        return name;
    }

    /**
     * 設定：データ値
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 取得：データ値
     */
    public String getValue() {
        return value;
    }

    /**
     * 設定：タイプ
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 取得：タイプ
     */
    public String getType() {
        return type;
    }

    /**
     * 設定：説明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 取得：説明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 設定：並び順（昇順）
     */
    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    /**
     * 取得：並び順（昇順）
     */
    public BigDecimal getSort() {
        return sort;
    }

    /**
     * 設定：親No.
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 取得：親No.
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 設定：作成者
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 取得：作成者
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 設定：作成日時
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 取得：作成日時
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 設定：更新者
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 取得：更新者
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 設定：更新日時
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 取得：更新日時
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 設定：備考
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 取得：備考
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 設定：削除フラグ
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 取得：削除フラグ
     */
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return "DictDO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", value='" + value + '\'' +
            ", type='" + type + '\'' +
            ", description='" + description + '\'' +
            ", sort=" + sort +
            ", parentId=" + parentId +
            ", createBy=" + createBy +
            ", createDate=" + createDate +
            ", updateBy=" + updateBy +
            ", updateDate=" + updateDate +
            ", remarks='" + remarks + '\'' +
            ", delFlag='" + delFlag + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DictDO dictDO = (DictDO) o;
        return Objects.equals(id, dictDO.id) && Objects.equals(name, dictDO.name)
            && Objects.equals(value, dictDO.value) && Objects.equals(type, dictDO.type)
            && Objects.equals(description, dictDO.description) && Objects.equals(sort, dictDO.sort)
            && Objects.equals(parentId, dictDO.parentId) && Objects.equals(createBy, dictDO.createBy)
            && Objects.equals(createDate, dictDO.createDate) && Objects.equals(updateBy,
            dictDO.updateBy) && Objects.equals(updateDate, dictDO.updateDate) && Objects.equals(remarks,
            dictDO.remarks) && Objects.equals(delFlag, dictDO.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, type, description, sort, parentId, createBy, createDate, updateBy,
            updateDate,
            remarks, delFlag);
    }
}
