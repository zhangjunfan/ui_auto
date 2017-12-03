package com.zhangjf.module;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.zhangjf.utils.Result;

public class PageOperationInfoList {
	
	private ArrayList<PageOperationInfo> poiList;

	public ArrayList<PageOperationInfo> getPoiList() {
		return poiList;
	}

	public void setPoiList(ArrayList<PageOperationInfo> poiList) {
		this.poiList = poiList;
	}
	
	public boolean checkValue(Result result){
		if(poiList == null || poiList.size() == 0){
			result.setResultCode(false);
			result.setResMsg("the PageOperationInfo list is null, please check whether the request body is empty");
			return false;
		}
		for(PageOperationInfo poi : poiList){
			if(!poi.checkValue(result)){
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
