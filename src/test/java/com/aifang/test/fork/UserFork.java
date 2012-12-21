package com.aifang.test.fork;

import java.util.LinkedList;
import java.util.List;

import com.aifang.model.Users;

public class UserFork {
	
	public static Users forkLenyemeng(){
		Users user = new Users();
		user.setChinesename("孟智");
		user.setEmail("lenyemeng@anjuke.com");
		user.setUsername("lenyemeng");
		return user;
	}
	
	public static Users forkXiaoWang(){
		Users user = new Users();
		user.setChinesename("小王");
		user.setEmail("xiaowang@anjuke.com");
		user.setUsername("xiaowang");
		return user;
	}
	
	
	public static List<Users> fork4Persons(){
		List<Users> list = new LinkedList<Users>();
		list.add(productPersonFactory("小赵","xiaozhao@anjuke.com","xiaozhao",97));
		list.add(productPersonFactory("小美","xiaomei@anjuke.com","xiaomei",98));
		list.add(productPersonFactory("小孟","xiaomeng@anjuke.com","xiaomeng",99));
		list.add(productPersonFactory("小李","xiaoli@anjuke.com","xiaoli",100));
		return list;
	}
	
	public static Users productPersonFactory(String chinesename,String email,String username,Integer userId){
		Users user = new Users();
		user.setChinesename(chinesename);
		user.setEmail(email);
		user.setUsername(username);
		if(userId != null && userId > 0){
			user.setId(userId);
		}
		return user;
	}
}
