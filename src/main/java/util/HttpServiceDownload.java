/*
package com.fatrui.accbooks.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.travelsky.updatetool.httpservice.UpdateService;
import com.travelsky.updatetool.httpservice.bean.PackageInfo;
import com.travelsky.updatetool.util.DownloadXMLConfig;

public class HttpServiceDownload {
	private static Logger logger = Logger.getLogger(HttpServiceDownload.class);
	public static void doServiceDownload(String fileName,String localPath,String remotePath,final String verNum){
		logger.info("doServiceDownload( fileName : "+fileName+" , downloadPath : "+remotePath+" )");
		 PackageInfo pi=UpdateService.getPackageInfo(fileName, remotePath);
		 if (pi!=null) {
			 FileOutputStream os =null;
			 if (!new File(localPath).exists()) {
				 new File(localPath).mkdirs();
			}
			 File file=new File(localPath+File.separator+fileName);
			 try {
				 byte[] fileStream=pi.getFileStream();
				 os = new FileOutputStream(file); 
			     os.write(fileStream, 0, fileStream.length);  
			     return ;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error(fileName+" FileOutputStream write exception : " +e);
			}finally{
				try {
					if(file.length() > 0) {
						DownloadXMLConfig.setFileStatus(verNum,fileName, "download");
					}else{
						DownloadXMLConfig.setFileStatus(verNum,fileName, "fail");
						if (file.exists()) {
							file.delete();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.error("DownloadXMLConfig setFileStatus exception : "+e);
				}finally{
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error(fileName+" FileOutputStream close failed. " +e);
					}  
				}
				
			}
			
		 }else{
			logger.error(fileName+" package download failed." );
		 }
		
	}
	
	 public static void downloadFile(URL theURL, String filePath,String fileName) throws IOException {  

	     File dirFile = new File(filePath);
	        if(!dirFile.exists()){//文件路径不存在时，自动创建目录
	          dirFile.mkdir();
	        }
	   //从服务器上获取图片并保存
	      URLConnection  connection = theURL.openConnection();
	      InputStream in = connection.getInputStream();  
	      FileOutputStream os = new FileOutputStream(filePath+fileName); 
	      byte[] buffer = new byte[4 * 1024];  
	      int read;  
	      while ((read = in.read(buffer)) > 0) {  
	          os.write(buffer, 0, read);  
	      }  
	      os.close();  
	      in.close();

	 }  
	 
	 public static void main(String[] args) {   
        String urlPath = "http://10.6.148.71:8280/opt/applog/msiUpdate/InfoRecord.log";   
        String filePath = "E:\\projectTest\\";
        String fileName="InfoRecord.log";
        try {   
        	  URL url = new URL(urlPath);   
              downloadFile(url,filePath,fileName);   
        } catch (IOException e) {   
            e.printStackTrace();  
        }   

	 }   
}
*/
