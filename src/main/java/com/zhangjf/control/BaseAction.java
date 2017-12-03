package com.zhangjf.control;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;

import com.zhangjf.utils.Logs;
import com.zhangjf.utils.Result;

/**
 * 
 * @author 张俊繁
 *
 */
public abstract class BaseAction {
	public static WebDriver driver = null;
	
	public void excute(HttpServletRequest req, HttpServletResponse res){
		Result result = new Result();
		String reqContent = this.getRequestBody(req, result);
		if(result.isResultCode()){
			this.process(driver, reqContent, result);
		}
		String resContent = result.toString();
		res.setCharacterEncoding("UTF-8");  
	    res.addHeader("Content-Type", "application/json");
	    try {
			res.getWriter().write(resContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logs.writeLog(this.getClass().getSimpleName(),"excute", e);
		}
	}

	public abstract void process(WebDriver driver, String reqContent, Result result);
	
	private String getRequestBody(HttpServletRequest req, Result result){
		String reqContent = null;
		try {
			InputStream is = req.getInputStream();
			reqContent = IOUtils.toString(is, "UTF-8");
			is.close();
			result.setLogMess(this.getClass().getSimpleName(), "getRequestBody", "the request content is: " + reqContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result.setResultCode(false);
			result.setResMsg("error happend when get request content from request");
			Logs.writeLog(this.getClass().getSimpleName(), "getRequestBody", e);
			return null;
		}
		return reqContent;
	}
	
}
