package com.aifang.util;

import java.text.SimpleDateFormat;

public class TimeUtil {
	
	static public String getTime()
	{
		long currentTime = System.currentTimeMillis();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		return sdf.format(currentTime).toString();
	
	}

}
