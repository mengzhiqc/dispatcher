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

public class OAuthAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -673399231913053094L;
	public static final String LOGINSUCCESS = "loginSuccess";

	public String execute() {
		// 判断请求类型，如果是post方式，为登录请求
		Map params = ActionContext.getContext().getParameters();
		try {
			if (null != params && params.containsKey("action")) {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				// 设置字符集
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				String action = params.get("action").toString();
				System.out.println(params);
				System.out.println(action.length());
				String rs = "";
				if ("" != action && null != action) {
					if (action == "getToken") {
						rs = "getTokenCode";
					} else if (action == "step2") {
						rs = "step2";
					} else if (action == "step3") {
						rs = "step3";
					} else {
						rs = "default";
					}

				} else {
					rs = "default2";
				}

				out.print(rs);
				out.flush();
				out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

}