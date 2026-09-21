package com.java2nb.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * ファイルアップロード
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-09-19 16:02:20
 */
public class FileDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // ファイルタイプ
    private Integer type;
    // URL
    private String url;
    // 作成日時
    private Date createDate;


    public FileDO() {
        super();
    }


    public FileDO(Integer type, String url, Date createDate) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
    }


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
     * 設定：ファイルタイプ
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 取得：ファイルタイプ
     */
    public Integer getType() {
        return type;
    }

    /**
     * 設定：URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 取得：URL
     */
    public String getUrl() {
        return url;
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

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
