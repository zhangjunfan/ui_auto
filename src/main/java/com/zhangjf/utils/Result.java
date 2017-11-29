package com.zhangjf.utils;

import org.joda.time.LocalDateTime;

import com.google.gson.Gson;

/**
 * 接口返回值类
 * @author 张俊繁
 *
 */
public class Result {
	
	private boolean resultCode = false;
	private StringBuffer logMess = new StringBuffer();
	private String resMsg;
	
	private String s;
	
	public boolean isResultCode() {
		return resultCode;
	}

	public void setResultCode(boolean resultCode) {
		this.resultCode = resultCode;
	}

	public StringBuffer getLogMess() {
		return logMess;
	}

	public void setLogMess(String logInfo) {
		String time = new LocalDateTime().toString("yyyy-MM-dd HH:mm:ss:sss");
		this.logMess.append(time + " | " + logInfo);
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	
	/**
	 * 以json格式返回对象内容
	 */
	@Override
	public String toString(){
		logMess = null;
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
