package com.zhangjf.service;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.zhangjf.module.PageOperationInfo;
import com.zhangjf.utils.Result;

/**
 * 
 * @author 张俊繁
 *
 */
public interface PageOperationService {

	public void process(WebDriver driver, Result result, ArrayList<PageOperationInfo> poiList);
}
