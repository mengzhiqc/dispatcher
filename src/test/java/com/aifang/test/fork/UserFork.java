package com.aifang.test.fork;

import com.aifang.model.Users;

public class UserFork {
	
	public static Users forkLenyemeng(){
		Users user = new Users();
		user.setChinesename("孟智");
		user.setEmail("lenyemeng@anjuke.com");
		user.setUsername("lenyemeng");
		return user;
	}
}
