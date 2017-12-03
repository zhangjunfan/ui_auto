package com.zhangjf.utils;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * 获取webdriver
 * @author 张俊繁
 *
 */
public class Drivers {

	/**
	 * 通过config.properties获取webdriver地址，返回chrome webdriver
	 * @return
	 */
	public static WebDriver getChromeDriver(){
		String driverPath = Configs.configMap.get("chromeDriverPath");
		if(StringUtils.isBlank(driverPath)){
			return null;
		}
		System.setProperty("webdriver.chrome.driver", Configs.configMap.get("chromeDriverPath"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		return driver;
	}
}
