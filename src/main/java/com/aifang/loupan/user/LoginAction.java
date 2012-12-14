package com.aifang.loupan.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

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
			try{
				HttpServletResponse response = ServletActionContext.getResponse();   
				//设置字符集   
				response.setCharacterEncoding("UTF-8");   
				PrintWriter out = response.getWriter();   
				String username = params.get("username").toString();
				if ("" != username && null != username) {
					LOG.debug("print log info");
					out.print(LOGINSUCCESS);
					out.flush();
					out.close();
					return null;
				}
				 
				
				  
			}catch(Exception e){}
			
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
