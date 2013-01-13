package com.aifang.test.mytask;

import com.aifang.model.Task;
import com.aifang.model.User;
import com.aifang.util.BasicTestCase;

public class UserTest extends BasicTestCase{
	
	public void testAddUser()
	{
		User user=(User)getBean("user");
		user.addUser("wangwu", "nihao", "12@12.com");
		User u=user.getUserInforByUsername("wangwu");
		assertEquals("wangwu", u.getUsername());
	}

/*	public void testgetUserInforByUsername() throws Exception
	{
        User user=(User)getBean("user");
		//User user=new User();
		//user.addUser("wangwu","zhang", "asd@asd.com");
       
       assertEquals("wangwu", user.getUserInforByUsername("wangwu").getUsername());

	    
		
	}*/
	

	/*public void testaddTask()
	{
		Task task=new Task();
		task.addTask("nihao", 10, "aaaa", "lisi");
	}*/
		

}
