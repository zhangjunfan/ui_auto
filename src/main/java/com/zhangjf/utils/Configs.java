package com.zhangjf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author 张俊繁
 *
 */
public class Configs {
	
	/**
	 * 配置信息map，每两个小时更新一次
	 */
	public static SortedMap<String, String> configMap = Collections.emptySortedMap();
	
	static{
		
		Runnable configInit = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				InputStream is = Configs.class.getClassLoader().getResourceAsStream("config.properties");
				Properties pro = new Properties();
				try {
					pro.load(is);
					is.close();
					Set<String> keySet = pro.stringPropertyNames();
					for(String key : keySet){
						String value = new String(pro.getProperty(key).getBytes("ISO-8859-1"),"UTF-8");
						configMap.put(key, value);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(configInit, 0, 2, TimeUnit.HOURS);
	}

}
