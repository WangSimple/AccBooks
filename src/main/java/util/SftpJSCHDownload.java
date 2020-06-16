/*
package com.fatrui.accbooks.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.travelsky.updatetool.util.DownloadXMLConfig;
import com.travelsky.updatetool.util.SFTPProcessor;

public class SftpJSCHDownload {
	private static Logger logger = Logger.getLogger(SftpJSCHDownload.class);
	*/
/**
	 * 通过SFTP方式下载
	 * @throws Exception
	 *//*

	public static void doSFTPDownload(String[] fileNames,String localPath,String remotePath,String verNum)throws Exception{
		SFTPProcessor ftpUtil = new SFTPProcessor();
		ChannelSftp channel = ftpUtil.getConnect();
		if (channel==null||!channel.isConnected()) {
			logger.error("get sftp channel failed.");
			return ;
		}
		for (int j = 0; j < fileNames.length; j++) {
			try {
				new File(localPath).mkdirs();
				ftpUtil.downloadFile(fileNames[j], remotePath, localPath+File.separator+fileNames[j]);
				DownloadXMLConfig.setFileStatus(verNum, fileNames[j], "download");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("download update package( "+fileNames[j]+" ) Exception: "+e+"!");
				return ;
			}
		}
		try {
			ftpUtil.closeChannel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
*/
