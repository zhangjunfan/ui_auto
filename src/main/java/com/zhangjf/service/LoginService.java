package com.zhangjf.service;

import org.openqa.selenium.WebDriver;

import com.zhangjf.module.LoginInfo;
import com.zhangjf.utils.Result;

/**
 * 
 * @author 张俊繁
 *
 */
public interface LoginService {

	public void login(WebDriver driver, Result result, LoginInfo loginInfo);
}
