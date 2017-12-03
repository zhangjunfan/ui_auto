package com.zhangjf.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverUtils {
	
	private By getBy(WebDriver driver, String keyType, String keyValue){
		if(keyType.equalsIgnoreCase(GlobalAttribute.key_id)){
			return By.id(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_css)){
			return By.cssSelector(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_linktext)){
			return By.linkText(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_class)){
			return By.className(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_name)){
			return By.name(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_partialLinkText)){
			return By.partialLinkText(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_tagName)){
			return By.tagName(keyValue);
		}else if(keyType.equalsIgnoreCase(GlobalAttribute.key_xpath)){
			return By.xpath(keyValue);
		}
		return null;
	}
	
	/**
	 * 等待元素加载以及显示、不被遮挡。至多等待15s
	 * @param reult
	 * @return
	 */
	private WebElement getElement(Result result, WebDriver driver, String keyType, String keyValue){
		By by = this.getBy(driver, keyType, keyValue);
		if(by == null){
			result.setResultCode(false);
			result.setResMsg("the keyType is invalid.");
		}
		WebElement element;
		int num=15;
		while(num > 0){
			try{
				element = driver.findElement(by);
				if(element.isDisplayed()){
					return element;
				}
			}catch(Exception e){
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					return null;
				}
			}
		}
		return null;
	}
	
	
}
