package com.zhangjf.service.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.zhangjf.module.PageOperationInfo;
import com.zhangjf.module.PageOperationInfoList;
import com.zhangjf.service.PageOperationService;
import com.zhangjf.utils.GlobalAttribute;
import com.zhangjf.utils.Result;
import com.zhangjf.utils.WebDriverUtils;


/**
 * 
 * @author 张俊繁
 *
 */
public class PageOperationServiceImpl implements PageOperationService{
	//此处没有考虑并发情况，所以默认这次获得的tab都是下次用
	private Set<String> tabSet = null;
	private String className = PageOperationServiceImpl.class.getSimpleName();
	private WebDriverUtils driverUtils = new  WebDriverUtils();
	
	public void process(WebDriver driver, Result result, ArrayList<PageOperationInfo> poiList) {
		// TODO Auto-generated method stub
		for(PageOperationInfo pageInfo : poiList){
			String operaType = pageInfo.getOperaType();
			String value = pageInfo.getValue();
			String eleType = pageInfo.getEleType();
			String keyType = pageInfo.getKeyType();
			String keyValue = pageInfo.getKeyValue();
			String nickName = pageInfo.getNickName();
			int sleepTime = pageInfo.getSleepTime();
			
			//获取tab
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_getTab)){
				tabSet = driver.getWindowHandles();
				result.setLogMess(className, "process", "the window handle set after getTab is: " + tabSet.toString());
			}
			//switchtab
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_switchTab)){
				this.switchTab(driver, result);
			}
			
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_keyEvent)){
				this.doKeyEvent(result, value);
			}
			
			
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_click)){
				driverUtils.doClick(driver, result, keyType, keyValue, eleType);
			}
			
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_putValue)){
				driverUtils.putValue(driver, result, keyType, keyValue, eleType, keyValue);
			}
			
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_getText)){
				driverUtils.getText(driver, result, keyType, keyValue, eleType, nickName);
			}
			
			if(operaType.equalsIgnoreCase(GlobalAttribute.oper_getAttrValue)){
				driverUtils.getValue(driver, result, keyType, keyValue, eleType, nickName);
			}
			
			if(sleepTime > 0){
				try {
					Thread.currentThread().sleep(sleepTime * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					result.setResultCode(false);
					result.setResMsg("error hapend in thread sleep");
				}
			}
		}
	}
	
	private void switchTab(WebDriver driver, Result result){
		if(tabSet == null || tabSet.size() == 0){
			result.setResultCode(false);
			result.setResMsg("before switch window handle, you should get window handle set");
			return;
		}
		Set<String> newTabSet = driver.getWindowHandles();
		if(newTabSet.size() == tabSet.size()){
			result.setResultCode(false);
			result.setResMsg("the size of window handle is the same, no new window handle to switch");
			return;
		}
		for(String tab: newTabSet){
			if(!tabSet.contains(tab)){
				result.setLogMess(className, "process", "now switch to window handle " + tab + ", and the window handle set now is: " + newTabSet.toString());
				driver.switchTo().window(tab);
				break;
			}
		}
	}
	
	private void doKeyEvent(Result result, String value){
		Robot robot;
		try {
			robot = new Robot();
			if(value.equalsIgnoreCase(GlobalAttribute.keyEvent_enter)){
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}else if(value.equalsIgnoreCase(GlobalAttribute.keyEvent_esc)){
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
			}else if(value.equalsIgnoreCase(GlobalAttribute.keyEvent_tab)){
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}else{
				result.setResultCode(false);
				result.setResMsg("now only support three keybord enter/esc/tab");
				return;
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			result.setResultCode(false);
			result.setResMsg("fail to do keyEvent");
			return;
		}
	}

}
