package com.zhangjf.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.zhangjf.module.LoginInfo;
import com.zhangjf.service.LoginService;
import com.zhangjf.service.impl.LoginServiceImpl;
import com.zhangjf.utils.Drivers;
import com.zhangjf.utils.Logs;
import com.zhangjf.utils.Result;
/**
 * 
 * @author 张俊繁
 *
 */
@Controller
public class LoginAction extends BaseAction{
	private Gson gson = new Gson();
	private String className = this.getClass().getSimpleName();
	private LoginService ls = new LoginServiceImpl();
	
	@RequestMapping(path = "/login.do", method = RequestMethod.POST)
	public void excute(HttpServletRequest req, HttpServletResponse res){
		super.excute(req, res);
	}

	@Override
	public void process(WebDriver driver, String reqContent, Result result) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(reqContent)){
			result.setResultCode(false);
			result.setResMsg("the request content is empty");
			return;
		}
		LoginInfo loginInfo = this.parseReqContent(reqContent, result);
		if(loginInfo == null){
			return;
		}
		if(loginInfo.getPoiList() == null || loginInfo.getPoiList().size() == 0){
			if(driver == null){
				result.setResultCode(false);
				result.setResMsg("the request content of test case's first step should content login info, not only url");
				return;
			}
			driver.get(loginInfo.getUrl());
		}
		driver = Drivers.getChromeDriver();
		if(driver == null){
			result.setResultCode(false);
			result.setResMsg("fail to get driver, maybe driverPath is null in config.properties");
			return;
		}
		ls.login(driver, result, loginInfo);
	}
	
	private LoginInfo parseReqContent(String reqContent, Result result){
		try{
			LoginInfo loginInfo = gson.fromJson(reqContent, LoginInfo.class);
			result.setLogMess(className, "parseReqContent","after parse, the LoginInfo is: " + loginInfo.toString());
			if(loginInfo.checkValue(result)){
				return loginInfo;
			}else{
				result.setResultCode(false);
				result.setResMsg("the LoginInfo is null after parse, maybe the request content is null");
				return null;
			}	
		}catch(Exception e){
			result.setResultCode(false);
			result.setResMsg("fail to parse the request content, it maybe not json format");
			Logs.writeLog(className, "parseReqContent", e);
			return null;
		}
		
	}

}
