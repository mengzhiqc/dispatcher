package com.aifang.test.dao;

import com.aifang.model.Users;
import com.aifang.util.BasicTestCase;

public class DaoTest extends BasicTestCase {

	public void testFindById(){
		Users users = (Users) getBean("users");
	}
}
