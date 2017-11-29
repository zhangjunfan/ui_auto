package com.zhangjf.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.joda.time.LocalDateTime;


/**
 * 记录log
 * @author 张俊繁
 *
 */
public class Logs {

	private static boolean dirExit = false;
	
	private static void writeToFile(String filePath, String content){
		File logFile = new File(filePath);
		if(!dirExit){
			File dirFile = logFile.getParentFile();
			if(dirFile.exists()){
				dirExit = true;
			}else{
				dirExit = dirFile.mkdirs();
			}
		}
		try {
			FileWriter fw = new FileWriter(logFile,true);
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将result的logMess写入log中
	 * @param result
	 */
	public static void writeLog(Result result){
		String date = new LocalDateTime().toString("yyyy-MM-dd");
		String filePath = Configs.configMap.get("logDir") + "/commonLog_" + date + ".log";
		writeToFile(filePath, result.getLogMess().toString());
	}
	
	/**
	 * 将异常信息写入log
	 * @param className  发生异常的类名，实际类名
	 * @param method  发生异常的方法
	 * @param e e.printStackTrace的详细信息写入log
	 */
	public static void writeLog(String className, String method, Exception e){
		LocalDateTime ldt = new LocalDateTime();
		String date = ldt.toString("yyyy-MM-dd");
		String filePath = Configs.configMap.get("logDir") + "/errorLog_" + date + ".log";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String fileContent = ldt.toString("yyyy-MM-dd HH:mm:ss:sss") + " | " + className + "#" 
		+ method + " " + sw.toString();
		writeToFile(filePath, fileContent);
	}
}
