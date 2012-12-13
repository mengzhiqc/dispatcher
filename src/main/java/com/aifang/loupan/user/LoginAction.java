package com.aifang.loupan.user;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -673399231913053094L;
	public static final String LOGINSUCCESS = "loginSuccess";

	private String username;
	private String password;

	public String execute() {
		// 判断请求类型，如果是post方式，为登录请求
		Map params = ActionContext.getContext().getParameters();
		if (params.containsKey("username")) {
			String username = params.get("username").toString();
			if ("" != username && null != username) {
				LOG.debug("print log info");
				return LOGINSUCCESS;
			}
		}

		return SUCCESS;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
