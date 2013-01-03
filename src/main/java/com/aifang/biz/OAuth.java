package com.aifang.biz;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.aifang.bean.User;
import com.aifang.util.CurlUtil;
import com.aifang.util.PropertiesUtil;

@Component(value = "oAuth")
public class OAuth implements IOAuth {

	String oauthUrl = PropertiesUtil.pps.getProperty("oauth.url");
	String oauthClientId = PropertiesUtil.pps.getProperty("oauth.clientId");
	String oauthClientSecret = PropertiesUtil.pps.getProperty("oauth.passwd");

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


	public void Auth() {
		// TODO Auto-generated method stub

	}

	public String queryStringBuilder(Map<String, String> data) {
		if (data.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		for (String key : data.keySet()) {
			sb.append(key + "=" + data.get(key) + "&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();

	}

	/**
	 * 获取临时令牌,以下为PHP做法 header("HTTP/1.1 302 Found"); $array = array(
	 * "client_id"=>$client_id, "response_type"=>"code"/*默认 );
	 * header("Location: " .
	 * 'https://auth.corp.anjuke.com/authorize.php?'.http_build_query($array));
	 * 
	 * @return String url 程序直接定向到下一步
	 */
	public String getRedirectToTempTokenUrl() {
		// TODO Auto-generated method stub
		String url = "";

		StringBuilder sb = new StringBuilder();
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("client_id", oauthClientId);
		data.put("response_type", "code");
		sb.append(oauthUrl).append("/authorize.php?").append(CurlUtil.buildUriString(data));
		url = sb.toString();
		return url;
	}
	/**
	 * 用临时令牌获取访问令牌重定向url
	 * @param tokenCode
	 * @return
	 */
	public String getRedirectToAccessTokenUrl(String tokenCode) {
		String url = "";
		StringBuilder sb = new StringBuilder();
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("client_id", oauthClientId);
		data.put("client_secret", oauthClientSecret);
		data.put("grant_type", "authorization_code");
		data.put("code", tokenCode);
		sb.append(oauthUrl).append("/token.php?").append(CurlUtil.buildUriString(data));
		url = sb.toString();
		return url;
	}
	
	/**
	 * 根据accesstoken获取用户信息
	 * @param accessToken
	 * @return
	 */
	public String getUserInfo(String accessToken) {
		StringBuilder sb = new StringBuilder();
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("oauth_token", accessToken);
		data.put("getinfo", "true");
		sb.append(oauthUrl).append("/resource.php?").append(CurlUtil.buildUriString(data));
		String url = sb.toString();
		String info = CurlUtil.getUrlResponse(url);
		return info;
	}
	
	/**
	 * 获取格式化后的用户信息
	 * @param accessToken
	 * @return
	 * @throws ParseException
	 */
	public User getFormatedUserInfo(String accessToken) throws ParseException {
		User user = new User();
		String info = this.getUserInfo(accessToken);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(info);
		JSONObject jsonObject = (JSONObject) obj;
		user.setUsername((String)jsonObject.get("username"));
		return user;
	}

}
