package com.aifang.biz;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.aifang.util.CurlUtil;
import com.aifang.util.PropertiesUtil;

@Component(value="oAuth")
public class OAuth implements IOAuth {
	
	
	public String getoAuthUrl() {
		return oAuthUrl;
	}


	public void setoAuthUrl(String oAuthUrl) {
		this.oAuthUrl = oAuthUrl;
	}



	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}



	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	
	private String oAuthUrl;
	private String clientName;
	private String clientId;
	
	
	/**
	 * 获取临时令牌,以下为PHP做法
	 * header("HTTP/1.1 302 Found");
     *   $array = array(
     *       "client_id"=>$client_id,
     *       "response_type"=>"code"/*默认
     *  );
     * header("Location: " . 'https://auth.corp.anjuke.com/authorize.php?'.http_build_query($array));
	 * @return String
	 */
	
	public String getTempToken(){
		String oauthUrl = PropertiesUtil.pps.getProperty("oauth.url");
		String rs = CurlUtil.getUrlResponse(oauthUrl);
		System.out.println(oauthUrl);
		String tempToken = "";
		return tempToken;
	}
	
	/**
	 * curl到指定的目标地址获取返回
	 * @param String url
	 * @return
	 */
	public String requestUrl(String url){
		String responseString = "";
		return responseString;
	}
	
	
	
	public void Auth() {
		// TODO Auto-generated method stub
		
	}
	
	
	public String queryStringBuilder(Map<String,String> data) {	
		if (data.isEmpty()){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		for (String key : data.keySet()){
			sb.append(key+"="+data.get(key)+"&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
		
	}

}


