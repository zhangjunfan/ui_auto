package com.zhangjf.control;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.zhangjf.module.PageOperationInfo;
import com.zhangjf.module.PageOperationInfoList;
import com.zhangjf.service.PageOperationService;
import com.zhangjf.service.impl.PageOperationServiceImpl;
import com.zhangjf.utils.Logs;
import com.zhangjf.utils.Result;
/**
 * 
 * @author 张俊繁
 *
 */
@Controller
public class PageOperationControl extends BaseAction{

	private Gson gson = new Gson();
	private String className = this.getClass().getSimpleName();
	private PageOperationService pos = new PageOperationServiceImpl();
	
	@RequestMapping(path = "/pageOperation.do", method = RequestMethod.POST)
	public void execute(HttpServletRequest req, HttpServletResponse res){
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
		if(driver == null){
			result.setResultCode(false);
			result.setResMsg("you need to login before do page operation");
			return;
		}
		PageOperationInfoList pageOperationInfoList = this.parseReqContent(reqContent, result);
		if(pageOperationInfoList == null){
			return;
		}
		ArrayList<PageOperationInfo> poiList = pageOperationInfoList.getPoiList();
		if(poiList == null && poiList.size() == 0){
			result.setResultCode(false);
			result.setResMsg("the value of poiList in request content is null");
			return;
		}
		pos.process(driver, result, poiList);
	}

	private PageOperationInfoList parseReqContent(String reqContent, Result result){
		try{
			PageOperationInfoList pageOperationInfoList = gson.fromJson(reqContent, PageOperationInfoList.class);
			result.setLogMess(className, "parseReqContent","after parse, the pageOperationInfoList is: " + pageOperationInfoList.toString());
			if(pageOperationInfoList.checkValue(result)){
				return pageOperationInfoList;
			}else{
				result.setResultCode(false);
				result.setResMsg("the PageOperationInfoList is null after parse, maybe the request content is null");
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
