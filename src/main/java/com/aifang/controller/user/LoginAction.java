package com.aifang.controller.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;

import com.aifang.bean.User;
import com.aifang.biz.OAuth;
import com.aifang.util.LogUtil;
import com.aifang.util.StringUtil;
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
	
	Map<String,String> cookiesMap;

	public void setCookiesMap(Map<String, String> cookiesMap) {
		this.cookiesMap = cookiesMap;
	}

	@Resource(name="oAuth")
	private OAuth oAuth;



	public OAuth getoAuth() {
		return oAuth;
	}

	public void setoAuth(OAuth oAuth) {
		this.oAuth = oAuth;
	}

	public String execute() {
		
		//完全通过oauth进行用户认证
		ActionContext ac = ActionContext.getContext();
		Map<?,?> params = ac.getParameters();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if(params.containsKey("access_token")){
			//第三步：到该流程完成，正式验证通过，做这个系统需要做的－－种cookie，并在第一步进行验证
			try{
				response.setCharacterEncoding("UTF-8");  
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();   
				String accessToken = request.getParameter("access_token");
				User user = oAuth.getFormatedUserInfo(accessToken);
				String guid = StringUtil.Md5(user.getUsername());
				Cookie ci = new Cookie("guid",guid);
				ci.setMaxAge(60*30);
				response.addCookie(ci);
				
				Cookie authInfo = new Cookie("authInfo",StringUtil.Base64Encoder(user.getUsername()));
				authInfo.setMaxAge(60*30);
				response.addCookie(authInfo);
				
				Map session = ac.getSession();
				session.put("dispatcher_user_session", user.getUsername());
				LogUtil.debug("myinfo:"+session.toString());
				
				return LOGINSUCCESS;
				
  
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(params.containsKey("code")){
			//第二步：已获得临时令牌，重定向到获取访问令牌页面
			System.out.println(request.getParameter("code"));
			try{
				String accessTokenUrl = oAuth.getRedirectToAccessTokenUrl((String)request.getParameter("code"));
				response.setHeader("Location", accessTokenUrl);
				response.setStatus(302);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		} else {
			//第一步：重定向到获取临时令牌页面,传入下一个页面带response_type参数，值为需要传入的code
			
			if(ac.getSession().containsKey("dispatcher_user_session")){
				return LOGINSUCCESS;
			}
			try{
				response.setCharacterEncoding("UTF-8");
				String url = oAuth.getRedirectToTempTokenUrl();
				response.setHeader("Location", oAuth.getRedirectToTempTokenUrl());
				response.setStatus(302);
			}catch(Exception e){
				e.printStackTrace();
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
