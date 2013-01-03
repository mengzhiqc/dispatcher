package com.aifang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

public class StringUtil {

	
	static public String Md5(String str) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			byte[] array = m.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<array.length;++i){
				sb.append(Integer.toHexString(array[i] & 0xFF |0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	static public String Base64Encoder(String str) {
			byte[] array = str.getBytes();
			byte[] st =  Base64.encodeBase64(array);
			
			return null;

	}
}
