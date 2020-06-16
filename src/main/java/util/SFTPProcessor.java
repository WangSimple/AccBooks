/*
package com.fatrui.accbooks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;



 public class SFTPProcessor {
	 private static Session session = null;
	 private static ChannelSftp channel = null;
	 private static Logger logger = Logger.getLogger(SFTPProcessor.class);
	 
	 
	 
	 public static ChannelSftp getConnect() {
		String ftpHost = SysPropUtil.getValue("download.host");
		  String port = SysPropUtil.getValue("download.port");
		  String ftpUserName = SysPropUtil.getValue("download.username");
		  String ftpPassword = SysPropUtil.getValue("download.pass");
		  //默认的端口22 此处我是定义到常量类中；
		  int ftpPort = 22;
		  //判断端口号是否为空，如果不为空，则赋值
		  if (port != null && !port.equals("")) {
			  ftpPort = Integer.valueOf(port);
		  }
		  JSch jsch = new JSch(); // 创建JSch对象
		  // 按照用户名,主机ip,端口获取一个Session对象
		  try {
			  logger.info("sftp [ ftpHost = "+ftpHost+"  ftpPort = "+ftpPort+"  ftpUserName = "+ftpUserName+"  ftpPassword = "+ftpPassword+" ]");
			   session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
			   logger.info("Session created.");
			   if (ftpPassword != null) {
			    session.setPassword(ftpPassword); // 设置密码
			   }
			   String ftpTO=SysPropUtil.getValue("download.timeout");
			   if (!(ftpTO==null||"".equals(ftpTO))) {
				   int ftpTimeout=Integer.parseInt(ftpTO);
				   session.setTimeout(ftpTimeout); // 设置timeout时候
			   }
			   //并且一旦计算机的密匙发生了变化，就拒绝连接。
			   session.setConfig("StrictHostKeyChecking", "no");
			   //默认值是 “yes” 此处是由于我们SFTP服务器的DNS解析有问题，则把UseDNS设置为“no”
			   session.setConfig("UseDNS", "no");
			   session.connect(); // 经由过程Session建树链接
			   logger.debug("Session connected.");
			   logger.debug("Opening SFTP Channel.");
			   channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
			   channel.connect(); // 建树SFTP通道的连接
			   logger.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = "
			     + ftpUserName + ", returning: " + channel);
		  } catch (JSchException e) {
		   // TODO Auto-generated catch block
			  e.printStackTrace();
			  logger.error("sftp getConnect error : "+e);
		  }
		  return channel;
	 }
	
	 public static void closeChannel() throws Exception {
		  try {
			   if (channel != null) {
				   channel.disconnect();
			   }
			   if (session != null) {
				   session.disconnect();
			   }
		  } catch (Exception e) {
			   logger.error("close sftp error", e);
			   throw new Exception( "close ftp error.");
		  }
	 }

		 
	 public static void uploadFile(String localFile, String newName, String remoteFoldPath) throws Exception{
		  InputStream input = null;
		  try {
		     input = new FileInputStream(new File(localFile));
		   // 改变当前路径到指定路径 
		   channel.cd(remoteFoldPath);
		   channel.put(input, newName);
		  } catch (Exception e) {
		   logger.error("Upload file error", e);
		   throw new Exception( "Upload file error.");
		  } finally {
		   if (input != null) {
		    try {
		     input.close();
		    } catch (IOException e) {
		     throw new Exception("Close stream error.");
		    }
		   }
		  }
	 }

	 
	 public void downloadFile(String remoteFile, String remotePath, String localFile) throws Exception {
		  logger.info("sftp download File remotePath :"+remotePath+File.separator+remoteFile+" to localPath : "+localFile+" !");
		  OutputStream output = null;
		  File file = null;
		  try {
			   file = new File(localFile);
			   if (!checkFileExist(localFile)) {
			    file.createNewFile();
			   }
			   output = new FileOutputStream(file);
			   channel.cd(remotePath);
			   channel.get(remoteFile, output);
		  } catch (Exception e) {
			   logger.error("Download file error", e);
			   throw new Exception("Download file error.");
		  } finally {
			   if (output != null) {
				    try {
				    	output.close();
				    } catch (IOException e) {
				    	throw new Exception("Close stream error.");
				    }
			   }
			   
		  }
	 }

	 
	 @SuppressWarnings("unchecked")
	 public Vector listFiles(String remotePath) throws Exception {
		  Vector vector = null;
		  try {
		   vector = channel.ls(remotePath);
		  } catch (SftpException e) {
		   logger.error("List file error", e);
		   throw new Exception("list file error.");
		  }
		  return vector;
	 }


	 private boolean checkFileExist(String localPath) {
		  File file = new File(localPath);
		  return file.exists();
	 }

	 public static void main(String[] args) throws Exception {
		//初始化配置文件
		try {
			SysPropUtil.init("\\updconfig.properties");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 SFTPProcessor ftpUtil = new SFTPProcessor();
		 ChannelSftp channeltest = ftpUtil.getConnect();
		 System.out.println(channeltest.isConnected());
		 ftpUtil.downloadFile("cussplus_3.5.0.1.zip.001", "/opt/applog/msiUpdate/Kiosk/3.7/COMMON/PEK", "E:\\projectTest\\downloadPath\\cussplus_3.5.0.1.zip.001");
		 ftpUtil.closeChannel();
		 System.out.println(channeltest.isConnected());
	 }
 }*/
