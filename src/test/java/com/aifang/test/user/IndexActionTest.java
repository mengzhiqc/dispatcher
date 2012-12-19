package com.aifang.test.user;

import com.opensymphony.xwork2.Action;
import com.aifang.controller.user.IndexAction;
import junit.framework.TestCase;

public class IndexActionTest extends TestCase {

	public void testIndexAction(){
		IndexAction action = new IndexAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
	}
}
