package com.zhangjf.module;

import com.google.gson.Gson;
import com.zhangjf.utils.Result;

public class PageOperationInfo {

	private String keyType;     //查找页面元素通过的手段，比如id,name,xpath,linktext
	private String keyValue;    //页面元素的属性，配合keyType一起查找，比如"usrName","/html/body/..."
	private String eleType;     //元素类型，webElement或者Select
	private String operaType;   //操作类型，点击、填值、获取页面的值、获取窗口句柄、切换浏览器窗口、键盘事件
	private String value;       //填什么值、键盘事件模拟按哪个键
	private String nickName;    //如果是获取页面的值，那么这个值返回的时候存的昵称。
	private int sleepTime;      //休息时间，单位秒
	
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getEleType() {
		return eleType;
	}
	public void setEleType(String eleType) {
		this.eleType = eleType;
	}
	public String getOperaType() {
		return operaType;
	}
	public void setOperaType(String operaType) {
		this.operaType = operaType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public void checkValue(Result result){
		
	}
	
	@Override
	public String toString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
