package com.zhangjf.module;

import org.apache.commons.lang.StringUtils;
import com.zhangjf.utils.GlobalAttribute;
import com.zhangjf.utils.Result;

/**
 * 页面操作
 * @author 张俊繁
 *
 */
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
	
	public boolean checkValue(Result result){
		if(operaType.equalsIgnoreCase(GlobalAttribute.oper_getTab) || operaType.equalsIgnoreCase(GlobalAttribute.oper_switchTab)){
			return true;
		}else if(operaType.equalsIgnoreCase(GlobalAttribute.oper_keyEvent)){
			if(StringUtils.isBlank(value)){
				result.setResultCode(false);
				result.setResMsg("the request param value should not be empty when the operaType is " + operaType);
				return false;
			}
		}else{
			if(!(eleType.equalsIgnoreCase(GlobalAttribute.ele_webElement) || eleType.equalsIgnoreCase(GlobalAttribute.ele_Select))){
				result.setResultCode(false);
				result.setResMsg("the eleType should be either webElement or select, keyType and keyValue is " + keyType + "_" + keyValue);
				return false;
			}
			if(StringUtils.isBlank(keyType) || StringUtils.isBlank(keyValue)){
				result.setResultCode(false);
				result.setResMsg("both the keyType and keyValue should not be empty, now keyType and keyValue is " + keyType + "_" + keyValue);
				return false;
			}
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_putValue)){
				if(StringUtils.isBlank(value)){
					result.setResultCode(false);
					result.setResMsg("the request param value should not be empty when the operaType is " + operaType + ", keyType and keyValue is " + keyType + "_" + keyValue);
					return false;
				}
			}else if(operaType.equalsIgnoreCase(GlobalAttribute.oper_getText) || operaType.equalsIgnoreCase(GlobalAttribute.oper_getAttrValue)){
				if(StringUtils.isBlank(nickName)){
					result.setResultCode(false);
					result.setResMsg("the request param nickName should not be empty when the operaType is " + operaType + ", keyType and keyValue is " + keyType + "_" + keyValue);
					return false;
				}
			}
			result.setResultCode(false);
			result.setResMsg("invalid operaType: " + operaType + ", keyType and keyValue is " + keyType + "_" + keyValue);
			return false;
		}
		if(sleepTime < 0)
			sleepTime = 0;
		return true;
	}
	
}
