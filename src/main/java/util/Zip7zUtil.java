/**   
* @Title: Zip7ZUtil.java 
* @Package com.travelsky.softupate.utils 
* @Description: TODO 
* @author litan   
* @date 2015-5-14 下午2:06:25 
*//*

package com.fatrui.accbooks.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

*/
/**
 * <p>
 * 使用7z进行压缩解压的工具类
 * </p>
 * <p><b>Copyright: All Rights Reserved (c) 2015-2016</b></p>
 * <p><b>Company:Travelsky</b></p>
 * @ClassName: Zip7ZUtil 
 * @author litan 
 * @date 2015-5-14 下午2:06:25 
 *  
 *//*

public class Zip7zUtil {
	private static String exePath=SysPropUtil.getValue("7z.path");
	private static Logger logger = LogManager.getLogger(Zip7zUtil.class);
	public static void zip(String srcRootPath,String zipFile) {
		String cmd = "7z a -tzip " + zipFile +  " " + srcRootPath;
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	*
	 * 同步解压
	 * @param zipFilePath
	 * @param unZipPath
	*/
/*public static boolean syncUnzip(String zipFilePath,String unZipPath) {
		logger.info("unzip "+zipFilePath+" to "+unZipPath);
		boolean result;
		String cmd = exePath+"\\7z x -o" + unZipPath + " -y " + zipFilePath;
		logger.info("do syncUnzip exec : "+cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while (true) {
				try {
					line = br.readLine();
					if (line == null) {
						result = true;
						break;
					}else{
						System.out.println(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(" syncUnzip exception : "+e);
					result = false;
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(" syncUnzip exception : "+e);
			result = false;
		}
		return result;
	}*//*

	
	public static boolean syncUnzip(String zipFilePath,String unZipPath) {
		logger.info("unzip "+zipFilePath+" to "+unZipPath);
		boolean result;
		String cmd = exePath+"\\7z x -o" + unZipPath + " -y " + zipFilePath;
		logger.info("do syncUnzip exec : "+cmd);
		try {
			File zipFile=new File(zipFilePath);
			long zipSize=zipFile.length();
			long unzipSize=0;
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while (true) {
				try {
					line = br.readLine();
					logger.info("7z info : " + line);
					if (line != null) {
						
						String[] arr=line.split("=");
						if (arr.length==2) {
							if (arr[0].trim().equals("Physical Size")) {
								zipSize=Long.parseLong(arr[1].trim());
							}
						}
						String[] arr2=line.split(":");
						if (arr2[0].trim().equals("Compressed")) {
							unzipSize=Long.parseLong(arr2[1].trim());
						}
					}else{
						logger.info("unzipSize = "+unzipSize+" , zipSize = "+zipSize);
						if (unzipSize==zipSize) {
							result = true;
						}else{
							result = false;
							logger.error(" syncUnzip "+zipFilePath+" to "+unZipPath+" failed ! ");
						}
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(" syncUnzip exception : "+e);
					result = false;
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(" syncUnzip exception : "+e);
			result = false;
		}
		return result;
	}
	*/
/**
	 * 异步解压，会通过listener回调结果
	 * @param zipFilePath
	 * @param unZipPath
	 * @param listener
	 *//*

	public static void asyncUnzip(String zipFilePath,String unZipPath,final ICallbackListener listener) {
		if (zipFilePath == null || zipFilePath.equals("") || unZipPath == null || unZipPath.equals("")) {
			listener.callback("fail");
			return;
		}
		String cmd = exePath+"\\7z x -o" + unZipPath + " -y -tzip " + zipFilePath;
		try {
			final Process p = Runtime.getRuntime().exec(cmd);
			if (listener != null) {
				new Thread() {
					public void run() {
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String line = null;
						while (true) {
							try {
								line = br.readLine().trim();
								if (line == null) {
									listener.callback("OK");
									break;
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								listener.callback("fail");
							}
							
						}
					}
				};
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listener.callback("fail");
		}
	}
}
*/
