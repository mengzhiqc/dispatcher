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
		Users toInsertUsers = new Users();
		toInsertUsers.setChinesename("孟智");
		toInsertUsers.setUsername("lenyemeng");
		toInsertUsers.setEmail("lenyemeng@anjuke.com");
		users.addUser(toInsertUsers);
		Users user = users.getUserInfoByUsername("lenyemeng");
		assertEquals("lenyemeng@anjuke.com", user.getEmail());
		users.deleteUserById(user.getId());
	}

	
}
