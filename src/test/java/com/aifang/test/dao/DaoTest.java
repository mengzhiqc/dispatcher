package com.aifang.test.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import junit.framework.Assert;

import com.aifang.model.Users;
import com.aifang.test.fork.UserFork;
import com.aifang.util.BasicTestCase;

public class DaoTest extends BasicTestCase {

	public void testFindById(){
		Users users = (Users) getBean("users");
		Users forkUser = UserFork.forkLenyemeng();
		users.deleteUserByUserName(forkUser.getUsername());
		users.addUser(forkUser);
		Integer userId = users.getUserInfoByUsername(forkUser.getUsername()).getId();
		Users toCheckUser = (Users)users.findById(userId);
		Assert.assertEquals(toCheckUser.getEmail(), forkUser.getEmail());
		users.deleteUserByUserName(forkUser.getUsername());

	}
	
	/**
	 * TODO：由于目前插入方式不正确，所以这个测试用例比较作鸡
	 */
	public void testFindByIds(){
//		Users users = (Users)getBean("users");
//		users.truncateTable(users.getClass().getName().toLowerCase());
//		List<Users> get4P = UserFork.fork4Persons();
//		Iterator<Users> iget4p = get4P.iterator();
//		if(iget4p != null){
//		while(iget4p.hasNext()){
//			Users currentUser = (Users)(iget4p.next());
//			users.insertOne(currentUser);
//		}
//		List<Integer> ids = new LinkedList<Integer>();
//		ids.add(1);
//		ids.add(2);
//		ids.add(3);
//		ids.add(4);
//		List<Object> userList = users.findByIds(ids);
//		Assert.assertEquals(4, userList.size());
//		Users user97 = (Users) userList.get(0);
//		Assert.assertNotNull(user97);
//		users.truncateTable(users.getClass().getName().toLowerCase());
//		}
	}
	
	public void testInsertOne(){
		Users forkUser = UserFork.forkLenyemeng();
		Users users = (Users) getBean("users");
		users.deleteUserByUserName(forkUser.getUsername());
		users.insertOne(forkUser);
		Users findUser = users.getUserInfoByUsername(forkUser.getUsername());
		Assert.assertEquals(forkUser.getEmail(), findUser.getEmail());
		users.deleteUserByUserName(forkUser.getUsername());
		
	}
}
