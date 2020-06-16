/*
package com.fatrui.accbooks.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.travelsky.update.validate.RSAUtil;
import com.travelsky.update.validate.ValidateTool;

public class FileUtil {
	
	private static Logger logger = LogManager.getLogger(FileUtil.class);
	
	
	public static void main(String[] args){
String zipPath=	"E:\\projectTest\\upload\\EasyCkiWeb_1.0.zip.*";
		String combinePath=	"E:\\projectTest\\upload\\EasyCkiWeb_1.0.zip";
		try {
			combineZipFile(zipPath,combinePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Zip7zUtil.syncUnzip("E:\\projectTest\\upload\\cussplus_3.5.0.1\\cussplus_3.5.0.1.zip.001", "E:\\projectTest\\upload\\cussplus_3.5.0.1\\A");
	}
	
	
	
*
	 * 复制文件夹到指定目录
	 * @param src
	 * @param dest
	 * @throws IOException


	public static void copyFolder(File src, File dest) throws IOException {  
	    if (src.isDirectory()) {  
	        if (!dest.exists()) {  
	            dest.mkdir();  
	        }  
	        String files[] = src.list();  
	        for (String file : files) {  
	            File srcFile = new File(src, file);  
	            File destFile = new File(dest, file);  
	            // 递归复制  
	            copyFolder(srcFile, destFile);  
	        }  
	    } else {  
	        InputStream in = new FileInputStream(src);  
	        OutputStream out = new FileOutputStream(dest);  
	  
	        byte[] buffer = new byte[1024];  
	  
	        int length;  
	          
	        while ((length = in.read(buffer)) > 0) {  
	            out.write(buffer, 0, length);  
	        }  
	        in.close();  
	        out.close();  
	    }  
	}  
	
*
	 * 解压校验MD5码，对比后删除解压后的文件
	 * @param  zipPath 解压文件的路径
	 * @param activeCode 对比的MD5码
	 * @return


	public static boolean validateFile(String zipFilePath,String activeCode) {
		logger.debug("check downloadFile MD5 !");
		boolean isR=false;
		//int index=zipFilePath.lastIndexOf("/");
		//+zipFilePath.substring(index,zipFilePath.lastIndexOf('.'));
		String unzipToPath=SysPropUtil.getConfigPath()+File.separator+"unZipFolder";
		try {
			ZipUtil.unzip(zipFilePath, unzipToPath, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dlActiveCode=ValidateTool.createNewMD5code(unzipToPath, "");
		if (ValidateTool.CompareACtiveCode(activeCode, dlActiveCode)) {
			isR=true;
		}
 删除解压后的文件

		FileUtil.deleteFile(new File(unzipToPath));
		new File(unzipToPath).delete();
		return isR;
	}
*
	 * 解压校验数字签名，该方法待测试
	 * 通过RSA校验下载的package


	public static boolean validateFile(String zipFilePath,byte[] publicKey,byte[] sign) {
		logger.debug("check downloadFile RSA !");
		boolean isR=false;
		
		int index=zipFilePath.lastIndexOf("/");
		zipFilePath.substring(index,zipFilePath.lastIndexOf('.'));
		String unzipToPath=SysPropUtil.getConfigPath()+File.separator+"unZipFolder";
		try {
			ZipUtil.unzip(zipFilePath, unzipToPath, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("unzip file exception : "+e+" !");
			return false;
		}
		String dlActiveCode=ValidateTool.createNewMD5code(zipFilePath, "");
		try {
			if (RSAUtil.verify(dlActiveCode.getBytes(), publicKey, sign)) {
				logger.debug("RSA verify seccess !");
				isR=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("RSA verify exception :"+ e);
		}finally{
			// 删除解压后的文件 
			FileUtil.deleteFile(new File(unzipToPath));
			new File(unzipToPath).delete();
		}
		return isR;
	}
*
	 * 
	 * @Description:递归删除文件夹下的文件
	 * @param file


	public static void deleteFile(File file) {
		File[] files = file.listFiles();
		for(File f:files) {
			if(f.isDirectory()) {
				deleteFile(f);
			}
			System.out.println("delete--"+f.getName());
			f.delete();
		}
		
	}
*
	 * 合并压缩文件
	 * @param zipPath	E:\\projectTest\\localDir\\config.zip.001
	 * @param combinePath	E:\\projectTest\\localDir\\config.zip
	 * @throws Exception


	public static void  combineZipFile(String zipPath,String combinePath)throws Exception{
		logger.info("combineZipFile"+zipPath);
		if (StringUtil.isEmpty(zipPath)) {
			logger.error("call combineZipFile error: the args zipPath is null");
			throw new Exception("the args zipPath is null") ; 
		}
		
		
		String cmd ="cmd /c copy /B "+zipPath+" "+combinePath;
		logger.info("commond is:" + cmd);
		Process pro=null;
		try {
			pro=Runtime.getRuntime().exec(cmd);
			long start_time;
			start_time=System.currentTimeMillis();
			int rescode=5;  
			while((System.currentTimeMillis()-start_time)<20000)
			{
			  try
			  {
			    rescode=pro.exitValue();
			    break;
			  }
			  catch(Exception e)
			  {
			  }
			}
			
 			//pro.waitFor();
			//Thread.sleep(10000);
			logger.info("combineZipFile end");

			pro.destroy();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("exec cmd error!" + e.getMessage());
			logger.error("the file : "+zipPath+" combineZipFile failed!");
			throw e;
		}
	}
	
	
*
	 * 远程服务器下载文件
	 * @param url  http://127.0.0.1:8080/MsiSys/upload/EasyCkiWeb_3.0/EasyCkiWeb_3.0.part001.zip
	 * @param localPath E:\projectTest\downloadPath/updateFile.zip


	public static  void downloadFileByWget(String url,String localPath) throws Exception{
		if (url == null) {
			logger.error("call downloadFile error: the args url is null");
			throw new Exception("the args url is null") ;
		}
		final String dlPath = SysPropUtil.getValue("download.path");
		final String logFile = "result.txt";
		//获取WGET相关配置
		String rateLimit=SysPropUtil.getValue("download.rate.limit");
		if (!"".equals(rateLimit)) {
			rateLimit=" --limit-rate="+rateLimit;
		}else{
			rateLimit="";
		}
		String reConTime=SysPropUtil.getValue("download.reconnect.time");
		if (!"".equals(reConTime)) {
			reConTime=" -t "+reConTime+" ";
		}else{
			reConTime="";
		}
		String conTimeout=SysPropUtil.getValue("download.connect.timeout");
		if (!"".equals(conTimeout)) {
			conTimeout=" -T "+conTimeout+" ";
		}else{
			conTimeout="";
		}
		String cmd = SysPropUtil.getValue("wget.path")+"\\wget -r -c " +rateLimit+reConTime+conTimeout+" -o "+ dlPath +"\\"+ logFile + " -O " + localPath + " " +url;
		logger.debug("commond is:" + cmd);
		Process pro=null;
		try {
			pro=Runtime.getRuntime().exec(cmd);
			pro.waitFor();
			pro.destroy();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("exec cmd error!" + e.getMessage());
			logger.error("the file : "+url+" download failed!");
			throw e;
		}
		
	}
	
	
	
	//创建文件
	public static boolean createFile(String path,String data,String encoding)  {
		File file =new File(path);
		if (file.exists()) {
			logger.warn("createFile warn :  the file "+file.getPath()+" is  exists recover this");
			//return false;
		}
		try {
			OutputStreamWriter fw=new OutputStreamWriter(new FileOutputStream(file),encoding);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(data);
			bw.flush();
			bw.close();
			logger.info("the file  "+file.getPath()+" createFile success .");
			return true;
		} catch (IOException e) {
			// TODO: handle exception
			logger.warn("createFile exception :"+e);
			e.printStackTrace();
			return false;
		}
		
	}
	
	
		
	

	
	
	
	
	//将文件移动到指定文件夹  如果指定目录存在相同文件名的文件则覆盖
	public static boolean moveFile(String srcFile,String destPath){
			return moveFile(new File(srcFile),destPath);
	}
	public static boolean moveFile(File srcFile,String destPath){
		if (!srcFile.exists()) {
			logger.warn("the file ["+srcFile.getPath()+"] is not exists");
			return false;
		}
      File dir = new File(destPath);
      if (!dir.exists()) {
		dir.mkdir();
      }
       // Move file to new directory
      boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
      return success;
}
	//复制文件到指定路径
	public static void copyFile(String oldPath,String newPath)   
	{   
      try{   
           int byteread =0;   
           File oldfile=new File(oldPath);   
           if(oldfile.exists()){     
              InputStream inStream=new FileInputStream(oldPath);    
              FileOutputStream fs=new FileOutputStream(newPath);   
              byte[] buffer=new byte[1444];    
              while((byteread=inStream.read(buffer))!= -1){   
                 fs.write(buffer,0,byteread);   
              }   
              inStream.close();   
           }   
      }   
      catch(Exception e){   
         e.printStackTrace();   
      }   
    }  
		
	//对比两个字符串哪里不相等
	public static String getdiffChar(String str1,String str2){
		char[] carr=null;
		String endstr="";
		String compareStr="";
		if (str1.length()>str2.length()) {
			 carr=str2.toCharArray();
			 endstr=str1.substring(str2.length());
			 compareStr=str1;
		}else{
			 carr=str1.toCharArray();
			 endstr=str2.substring(str1.length());
			 compareStr=str2;
		}
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < carr.length-1; i++) {
			if (compareStr.charAt(i)!=carr[i]) {
				sb.append(i+",");
			}
		}
		sb.append(endstr);
		return sb.toString();
	}
	
}
*/
