/**   
* @Title: DownloadFile.java 
* @Package com.travelsky.softupate 
* @Description: 下载升级文件的类
* @author litan   
* @date 2015-3-19 下午1:12:03 
*//*

package com.fatrui.accbooks.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.travelsky.updatetool.util.DownloadXMLConfig;
import com.travelsky.updatetool.util.SysPropUtil;




public class HttpWgetDownload {
	
	private static String wgetPath=SysPropUtil.getValue("wget.path");
	private static String logPath=SysPropUtil.getValue("wget.log");
	private static String host=SysPropUtil.getValue("download.host");
	private static String port=SysPropUtil.getValue("download.port");//download.remoteDir
	private static String timeout=SysPropUtil.getValue("download.timeout");
	private static String httpReq="http://"+host+":"+port+"/";
	//服务器上存放更新包的文件夹名称
	private static final String uploadDirName=SysPropUtil.getValue("download.remoteDir");//
	private static Logger logger = Logger.getLogger(HttpWgetDownload.class);
	
	public static  void doHttpDownload(final String fileName,String localPath,String remotePath,final String verNum) {
		int uploadIndex=remotePath.lastIndexOf(uploadDirName);
		if (uploadIndex<0) {
			logger.warn(fileName+" download failed. can not find the remote Dir("+uploadDirName+") from remote Path("+remotePath+").");
			return ;
		}
		remotePath=remotePath.substring(uploadIndex);
		final String filePath=localPath+File.separator+fileName;
		String url=httpReq+remotePath+"/"+fileName;
		long timeoutms;
		try {
			timeoutms=Long.parseLong(timeout)/1000;
		} catch (Exception e) {
			timeoutms=60;
		}
		String cmd = wgetPath+"\\wget  -r -c  --limit-rate=1M -t 10  -T "+timeoutms+"  -o " + logPath + " -O " + filePath + " " + url;
		logger.info("commond is:" + cmd);
		try {
			final File logFile=new File(logPath);
			if (!logFile.exists()) {
				logFile.getParentFile().mkdirs();
				logFile.createNewFile();
			}
			File localDir=new File(localPath);
			if (!localDir.exists()) {
				localDir.mkdirs();	
			}
			Process download=Runtime.getRuntime().exec(cmd);
			download.waitFor();
			new Thread () {
				public void run() {
					logger.info("start resultFile monitor!");
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e)  {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						BufferedReader reader = null;;
						try {
							reader = new BufferedReader(new FileReader(logFile));
							while(true) {
								String line = reader.readLine();
								if (line != null) {
									logger.debug(line);
								} else {
									break;
								}
							}
							break;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								if (reader != null)
									reader.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					validateFile();
				}
				
				public void validateFile() {
					File download = new File(filePath);
					try {
						if(download.length() > 0) {
							DownloadXMLConfig.setFileStatus(verNum,fileName, "download");
						}else{
							DownloadXMLConfig.setFileStatus(verNum,fileName, "fail");
							if (download.exists()) {
								 download.delete();
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("DownloadXMLConfig setFileStatus exception : "+e);
					}
				}
			}.start();
		} catch (Exception e) {
			logger.error("exec cmd error!" + e.getMessage());
		}
	
	}
	
	public static void main(String []args) throws Exception{
		SysPropUtil.init("\\updconfig.properties");
		doHttpDownload("CussStore.zip.003", "E:\\projectTest", "/MsiUpdate/upload/PEK", "1.0");
		
	}
}
*/
