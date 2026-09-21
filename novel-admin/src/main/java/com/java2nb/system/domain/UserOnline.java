package com.java2nb.system.domain;

import java.util.Date;

/**
 *
 *
 */
public class UserOnline {

    /**
     */
    private String id;

    private String userId;

    private String username;

    /**
     * ユーザーホストアドレス
     */
    private String host;

    /**
     * ユーザーログイン時のシステムIP
     */
    private String systemHost;

    /**
     * ユーザーブラウザ種別
     */
    private String userAgent;

    /**
     * オンライン状態
     */
    private String status = "on_line";

    /**
     * session作成日時
     */
    private Date startTimestamp;
    /**
     * session最終アクセス日時
     */
    private Date lastAccessTime;

    /**
     * タイムアウト時間
     */
    private Long timeout;

    /**
     * バックアップされた現在のユーザーセッション
     */
    private String onlineSession;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnlineSession() {
        return onlineSession;
    }

    public void setOnlineSession(String onlineSession) {
        this.onlineSession = onlineSession;
    }


    public String getSystemHost() {
        return systemHost;
    }

    public void setSystemHost(String systemHost) {
        this.systemHost = systemHost;
    }



}
