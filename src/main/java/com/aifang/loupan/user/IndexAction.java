/**
 * 这是用户中心首页
 * 负责管理用户的所有信息，包括自己发的任务，自己接收的任务，自己任务的状态等的展示
 */
package com.aifang.loupan.user;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author kavin
 * 
 */
public class IndexAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5907365426567480132L;
	private String username;
	private String email;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String execute() {
		
		try{
			ActionContext ctx = ActionContext.getContext();
			Map application = ctx.getApplication();
			Map params = ctx.getParameters();
			if(params.containsKey("username")){
				username = (String) params.get("username");
			}else{
				username = "lenye01";
			}
		} catch(Exception e){
			
		}
		return SUCCESS;
	}

}
