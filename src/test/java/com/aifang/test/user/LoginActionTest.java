package com.aifang.test.user;

import javax.annotation.Resource;

import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aifang.biz.OAuth;
import com.aifang.controller.user.LoginAction;
import com.aifang.util.CurlUtil;
import com.opensymphony.xwork2.Action;

import junit.framework.TestCase;
@RunWith(JUnit38ClassRunner.class)
public class LoginActionTest extends TestCase {
	
	/**
	 * 验证login功能是否成功
	 */
	public void testLoginAction(){
		//String rs = CurlUtil.getUrlResponse("http://localhost:8080/dispatcher/login.action?username=lenyemeng&passwd=123456");
		//System.out.print(rs);
		//assertEquals("loginSuccess", rs);
	}
}
