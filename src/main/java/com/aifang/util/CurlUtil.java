package com.aifang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 封装curl
 * 
 * @author lenye01
 * 
 */
public class CurlUtil {


	/**
	 * 通过URL获取返回值
	 * @param String url
	 * @return String rc
	 */
	public static String getUrlResponse(String url) {
		try {
			URL url1 = new URL(url);
			URLConnection uc = url1.openConnection();
			InputStream in = uc.getInputStream();
			String rc = convertStreamToString(in);
			return rc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将InputStream转换为String格式
	 * @param InputStream is
	 * @return String sb
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
