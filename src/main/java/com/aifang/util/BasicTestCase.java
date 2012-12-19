package com.aifang.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public abstract class BasicTestCase extends TestCase {
	
	protected  ApplicationContext testContext ;
	
	public void  setUp(){	
		PropertiesUtil.pps = new Properties();
		try{
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			FileInputStream fis = new FileInputStream(path + "/../classes/dispatcher.properties");
			PropertiesUtil.pps.load(fis);
		}catch(Exception e){
			e.printStackTrace();
		}
		testContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public Object getBean(String beanName){
		return testContext.getBean(beanName);
	}
}
