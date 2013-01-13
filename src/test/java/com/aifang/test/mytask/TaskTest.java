package com.aifang.test.mytask;

import com.aifang.model.Task;
import com.aifang.util.BasicTestCase;

public class TaskTest extends BasicTestCase {
	
	public void testaddTask(){
		
		Task task=(Task)getBean("task");
		task.addTask("disp",20,"it is nice","liaokylin");
		Task t=task.searchTask(1);
		assertEquals("liaokylin",t.getUsername());
	//	task.deleteTask("liaokylin", 3);
	//	task.searchTask(3);
		//task.updateTask("modle", 12, "it is bad", "apple", 5);
		//task.updateTask("hello", 16, "it is bad", "egg", 6);
		//task.updateTask("new day", 30, "it is bad", "ipad", 8);
		//assertEquals(20,task.getScore());
	}
	
	     

}
