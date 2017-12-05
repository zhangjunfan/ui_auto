package com.zhangjf.utils;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtils {
	private String className = WebDriverUtils.class.getSimpleName();
	
	public void doClick(WebDriver driver, Result result, String keyType, String keyValue, String eleType){
		WebElement element = this.getElement(result, driver, keyType, keyValue);
		if(element == null){
			return;
		}
		if(eleType.equalsIgnoreCase(GlobalAttribute.ele_webElement)){
			element.click();
		}else{
			result.setResultCode(false);
			result.setResMsg("select can not be clicked");
		}
		
	}
	
	/**
	 * 
	 * @param value 如果是select，那么value是下拉单的值，也就是selectByVisibleText
	 */
	public void putValue(WebDriver driver, Result result, String keyType, String keyValue, String eleType, String value){
		WebElement element = this.getElement(result, driver, keyType, keyValue);
		if(element == null){
			return;
		}
		if(eleType.equalsIgnoreCase(GlobalAttribute.ele_webElement)){
			element.clear();
			element.sendKeys(value);
		}else{
			new Select(element).selectByVisibleText(value);
		}
	}
	
	/**
	 * 
	 * @param value 如果是select，那么只获取被选中的那个
	 */
	public void getText(WebDriver driver, Result result, String keyType, String keyValue, String eleType, String nickName){
		WebElement element = this.getElement(result, driver, keyType, keyValue);
		if(element == null){
			return;
		}
		if(eleType.equalsIgnoreCase(GlobalAttribute.ele_webElement)){
			result.addSearchResult(nickName, element.getText().trim());
		}else{
			WebElement option = new Select(element).getFirstSelectedOption();
			result.addSearchResult(nickName, option.getText().trim());
		}
	}
	
	/**
	 * 
	 * @param value 只获取属性名=value的值
	 */
	public void getValue(WebDriver driver, Result result, String keyType, String keyValue, String eleType, String nickName){
		WebElement element = this.getElement(result, driver, keyType, keyValue);
		if(element == null){
			return;
		}
		if(eleType.equalsIgnoreCase(GlobalAttribute.ele_webElement)){
			result.addSearchResult(nickName, element.getAttribute("value"));
		}else{
			WebElement option = new Select(element).getFirstSelectedOption();
			result.addSearchResult(nickName, option.getAttribute("value"));
		}
	}
	
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
				if(num ==1){
					result.setResultCode(false);
					result.setResMsg("the element does not display");
				}
			}catch(Exception e){
				if(num ==1){
					result.setResultCode(false);
					result.setResMsg("fail to find element");
					Logs.writeLog(className, "getElement", e);
				}
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					result.setResultCode(false);
					Logs.writeLog(className, "getElement", e1);
					return null;
				}
			}
			num--;
		}
		if(StringUtils.isBlank(result.getResMsg())){
			result.setResultCode(false);
			result.setResMsg("fail to local element");
		}
		return null;
	}
	
	
}
