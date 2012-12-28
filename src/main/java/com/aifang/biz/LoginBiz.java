package com.aifang.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.aifang.dao.UserDao;
import com.aifang.model.Users;

@Component(value="loginBiz")
public class LoginBiz {

	//@Resource
	//private UserDao userDao;
	
	/**
	 * 保存用户信息，不重复插入
	 * TODO update信息还没有做
	 * @param Users userInfo
	 * @return boolean 成功返回true，传入null返回false
	 */
	public boolean saveOrUpdateUser(Users userInfo) {
//		if (null == userInfo){
//			return false;
//		}
//		List<Users> userlist = userDao.getUserInfosByUsername(userInfo.getUsername());
//		if(null == userlist || userlist.size() == 0){
//			userDao.addUser(userInfo);
//		}
		return true;
	}
}
