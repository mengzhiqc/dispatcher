package com.aifang.controller.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.aifang.biz.OAuth;
import com.aifang.util.CurlUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.annotation.Resource;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -673399231913053094L;
	public static final String LOGINSUCCESS = "loginSuccess";

	private String username;
	private String password;
	
	@Resource(name="oAuth")
	private OAuth oAuth;



	public OAuth getoAuth() {
		return oAuth;
	}

	public void setoAuth(OAuth oAuth) {
		this.oAuth = oAuth;
	}

	public String execute() {
		if (null != oAuth){
			oAuth.getTempToken();
		}else{
			System.out.println("oa is null");
		}
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
