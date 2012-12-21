package com.aifang.test.user;


import java.util.List;

import org.junit.Assert;

import com.aifang.biz.LoginBiz;
import com.aifang.model.Users;


import com.aifang.test.fork.UserFork;
import com.aifang.util.BasicTestCase;


public class UsersTest extends BasicTestCase {
	
	
	/**
	 * 测试是否插入成功
	 * @throws Exception
	 */
	public void testAddUser() throws Exception {
		Users users = (Users) getBean("users");
		Users toInsertUsers = UserFork.forkLenyemeng();
		users.deleteUserByUserName(toInsertUsers.getUsername());
		users.addUser(toInsertUsers);
		List<Users> userlist = users.getUserInfosByUsername(toInsertUsers.getUsername());
		assertEquals(1, userlist.size());
		users.deleteUserByUserName(toInsertUsers.getUsername());
	}
	
	/**
	 * 测试SaveOrUpdateUser方法成功与否
	 */
	public void testSaveOrUpdateUser() {
		LoginBiz logBiz = (LoginBiz) getBean("loginBiz");
		Users users = (Users) getBean("users");
		Users forkUser = UserFork.forkLenyemeng();
		users.deleteUserByUserName(forkUser.getUsername());
		//测试传入null的情况
		logBiz.saveOrUpdateUser(null);
		logBiz.saveOrUpdateUser(forkUser);
		List<Users> userlist = users.getUserInfosByUsername(forkUser.getUsername());
		Assert.assertNotNull(userlist);
		Assert.assertEquals(1, userlist.size());
		logBiz.saveOrUpdateUser(forkUser);
		List<Users> userlist2 = users.getUserInfosByUsername(forkUser.getUsername());
		Assert.assertNotNull(userlist2);
		Assert.assertEquals(1, userlist2.size());
	}
	

	
}
