package com.aifang.test.dao;

import java.util.HashMap;
import java.util.List;


import junit.framework.Assert;

import com.aifang.dao.UserDao;
import com.aifang.model.Users;
import com.aifang.test.fork.UserFork;
import com.aifang.util.BasicTestCase;

public class DaoTest extends BasicTestCase {

	public void testFindById(){
		UserDao userDao = (UserDao)getBean("userDao");
		Users forkUser = UserFork.forkLenyemeng();
		userDao.deleteUserByUserName(forkUser.getUsername());
		userDao.addUser(forkUser);
		Integer userId = userDao.getUserInfoByUsername(forkUser.getUsername()).getId();
		Users toCheckUser = userDao.findById(userId);
		Assert.assertEquals(toCheckUser.getEmail(), forkUser.getEmail());
		userDao.deleteUserByUserName(forkUser.getUsername());

	}
	
	/**
	 * TODO：由于目前插入方式不正确，所以这个测试用例比较作鸡
	 */
//	public void testFindByIds(){
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
//	}
	
//	public void testInsertOne(){
//		UserDao userDao = (UserDao)getBean("userDao");
//		Users forkUser = UserFork.forkLenyemeng();
//		userDao.deleteUserByUserName(forkUser.getUsername());
//		userDao.insertOne(forkUser);
//		Users findUser = userDao.getUserInfoByUsername(forkUser.getUsername());
//		Assert.assertEquals(forkUser.getEmail(), findUser.getEmail());
//		userDao.deleteUserByUserName(forkUser.getUsername());
//		
//	}
//	
//	public void testFindByWhere(){
//		UserDao userDao = (UserDao)getBean("userDao");
//		Users forkUser = UserFork.forkLenyemeng();
//		userDao.insertOne(forkUser);
//		HashMap<String,String> where = new HashMap<String,String>(){{
//			put("username","lenyemeng");
//		}};
//		List userList = userDao.findByWhere((HashMap)null);
//		Assert.assertEquals(true, (userList.size()>0));
//		List userList1 = userDao.findByWhere(where);
//		Assert.assertEquals(1, userList1.size());
//	}
}
