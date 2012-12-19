package com.aifang.test.user;


import com.aifang.model.Users;


import com.aifang.util.BasicTestCase;
import com.aifang.util.LogUtil;


public class UsersTest extends BasicTestCase {
	
	
	/**
	 * 测试是否插入成功
	 * @throws Exception
	 */
	public void testAddUser() throws Exception {
		Users users = (Users) getBean("users");
		users.addUser("lenyemeng", "lenyemeng@anjuke.com", "孟智");
		Users user = users.getUserInfoByUsername("lenyemeng");
		LogUtil.debug(user.getChinesename());
		assertEquals("lenyemeng@anjuke.com", user.getEmail());
		assertEquals("lenyemeng@anjuke.com", "lenyemeng@anjuke.com");

	}

	
}
