package com.zhangjf.service;

import org.openqa.selenium.WebDriver;

import com.zhangjf.module.PageOperationInfoList;
import com.zhangjf.utils.Result;

/**
 * 
 * @author 张俊繁
 *
 */
public interface PageOperationService {

	public void process(WebDriver driver, Result result, PageOperationInfoList pageOperationInfoList);
}
