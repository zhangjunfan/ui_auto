package com.zhangjf.module;

import org.apache.commons.lang.StringUtils;

import com.zhangjf.utils.Result;

/**
 * 登录信息
 * @author Administrator
 *
 */
public class LoginInfo {

	private String userName;
	private String password;
	private String url;
	private String keyValue;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	public boolean checkValue(Result result){
		if(StringUtils.isBlank(url)){
			result.setResultCode(false);
			result.setResMsg("the url can not be empty");
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return "userName/password/url/keyValue, keyValue is keybord value";
	}
}
