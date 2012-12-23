package com.aifang.test.user;

import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;

import com.opensymphony.xwork2.Action;
import com.aifang.controller.user.IndexAction;
import junit.framework.TestCase;
@RunWith(JUnit38ClassRunner.class)
public class IndexActionTest extends TestCase {

	public void testIndexAction(){
		IndexAction action = new IndexAction();
        String result = action.execute();
        assertEquals(Action.SUCCESS, result);
	}
}
