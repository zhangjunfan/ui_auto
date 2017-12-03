package com.zhangjf.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhangjf.utils.Logs;
import com.zhangjf.utils.Result;

/**
 * 
 * @author 张俊繁
 *
 */
@Controller
public class LogoutAction extends BaseAction{
	
	@RequestMapping(path = "/quit.do", method = RequestMethod.POST)
	public void execute(HttpServletRequest req, HttpServletResponse res){
		super.excute(req, res);
	}

	@Override
	public void process(WebDriver driver, String reqContent, Result result) {
		// TODO Auto-generated method stub
		try{
			driver.quit();
			driver = null;
		}catch(Exception e){
			result.setResultCode(false);
			result.setResMsg("fail to close broswer");
			Logs.writeLog(this.getClass().getSimpleName(), "process", e);
		}
		
	}

}
