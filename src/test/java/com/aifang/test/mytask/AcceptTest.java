package com.aifang.test.mytask;

import com.aifang.model.Accept;
import com.aifang.model.Task;
import com.aifang.util.BasicTestCase;

public class AcceptTest extends BasicTestCase{
	
	public void testacceptTask()
	{
		Accept acp=(Accept)getBean("accept");
	
		/*Task task=new Task();
		
		acp.acceptTask(task.searchTask(5), "nuaa");
		acp.acceptTask(task.searchTask(6), "nuaa");
		acp.acceptTask(task.searchTask(7), "nuaa");
		acp.acceptTask(task.searchTask(8), "nuaa");
		*/
		Accept acpt=new Accept();
		//acp.deleteTask(4, "nuaa");
		acpt.showTask("nuaa");
  	}
	
           
}
