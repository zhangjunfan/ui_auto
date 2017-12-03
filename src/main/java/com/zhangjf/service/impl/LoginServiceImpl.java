package com.zhangjf.service.impl;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.zhangjf.module.LoginInfo;
import com.zhangjf.module.PageOperationInfo;
import com.zhangjf.service.LoginService;
import com.zhangjf.utils.Result;

/**
 * 
 * @author 张俊繁
 *
 */
public class LoginServiceImpl implements LoginService{

	public void login(WebDriver driver, Result result, LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		driver.get(loginInfo.getUrl());
		ArrayList<PageOperationInfo> poiList = loginInfo.getPoiList();
		if(poiList == null && poiList.size() == 0){
			result.setResultCode(true);
			return;
		}
		
	}

}
