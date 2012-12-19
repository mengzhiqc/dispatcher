package com.aifang.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.aifang.bean.User;
import com.aifang.model.Users;

@Component(value="loginBiz")
public class LoginBiz {

	@Resource(name="users")
	private Users users;
	
	public boolean saveOrUpdateUser(Users users){
		users.addUser(users);
		return true;
	}
}
