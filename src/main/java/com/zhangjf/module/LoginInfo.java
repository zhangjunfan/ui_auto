package com.zhangjf.module;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.zhangjf.utils.Result;

/**
 * 登录信息
 * @author Administrator
 *
 */
public class LoginInfo {

	private String url;
	private ArrayList<PageOperationInfo> poiList;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<PageOperationInfo> getPoiList() {
		return poiList;
	}

	public void setPoiList(ArrayList<PageOperationInfo> poiList) {
		this.poiList = poiList;
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
		return new Gson().toJson(this);
	}
}
