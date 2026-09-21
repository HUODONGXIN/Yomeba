package com.java2nb.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class BugLogDO {
	private Long id;
	private String level;
	private String message;
	private String stack;
	private String url;
	private String params;
	private String ip;
	private String userAgent;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getLevel() { return level; }
	public void setLevel(String level) { this.level = level; }
	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
	public String getStack() { return stack; }
	public void setStack(String stack) { this.stack = stack; }
	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	public String getParams() { return params; }
	public void setParams(String params) { this.params = params; }
	public String getIp() { return ip; }
	public void setIp(String ip) { this.ip = ip; }
	public String getUserAgent() { return userAgent; }
	public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
	public Date getGmtCreate() { return gmtCreate; }
	public void setGmtCreate(Date gmtCreate) { this.gmtCreate = gmtCreate; }
}
