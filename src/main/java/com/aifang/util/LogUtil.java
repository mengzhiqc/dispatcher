package com.aifang.util;

import org.apache.log4j.Logger;


public class LogUtil {

	static Logger log = Logger.getRootLogger();
	
	
	static public void info(String msg) {
		log.info(msg);
	} 
	
	static public void warn(String msg) {
		log.warn(msg);
	}
	
	static public void debug(String msg) {
		log.debug("DebugInfo: ["+new Object(){
			public String getClassName(){
				String className = this.getClass().getName();
				return className.substring(0, className.lastIndexOf('$'));
			}
		}.getClassName()+"] - "+msg);
	}
	
}
