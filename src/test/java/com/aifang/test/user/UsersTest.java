package com.aifang.test.user;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import com.aifang.biz.LoginBiz;
import com.aifang.model.Users;


import com.aifang.test.fork.UserFork;
import com.aifang.util.BasicTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")


public class UsersTest {
	
	@Resource
	private Users users;
	@Resource
	private LoginBiz loginBiz;
	
	/**
	 * 测试是否插入成功
	 * @throws Exception
	 */
	@Test
	public void testAddUser() throws Exception {
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
	@Test
	public void testSaveOrUpdateUser() {
		Users forkUser = UserFork.forkLenyemeng();
		users.deleteUserByUserName(forkUser.getUsername());
		//测试传入null的情况
		loginBiz.saveOrUpdateUser(null);
		loginBiz.saveOrUpdateUser(forkUser);
		List<Users> userlist = users.getUserInfosByUsername(forkUser.getUsername());
		Assert.assertNotNull(userlist);
		Assert.assertEquals(1, userlist.size());
		loginBiz.saveOrUpdateUser(forkUser);
		List<Users> userlist2 = users.getUserInfosByUsername(forkUser.getUsername());
		Assert.assertNotNull(userlist2);
		Assert.assertEquals(1, userlist2.size());
	}
	

	
}
