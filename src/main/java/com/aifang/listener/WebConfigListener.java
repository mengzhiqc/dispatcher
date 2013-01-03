package com.aifang.listener;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aifang.util.PropertiesUtil;

public class WebConfigListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		PropertiesUtil.pps = new Properties();
		try{
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			FileInputStream fis = new FileInputStream(path + "dispatcher.properties");
			PropertiesUtil.pps.load(fis);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
