/*
package com.fatrui.accbooks.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import com.travelsky.updatetool.util.ZipUtil;

public class Handler implements Runnable{
	private Socket socket;
	public Handler(Socket socket) {
		super();
		this.socket = socket;
	}
	public void run() {
		BufferedInputStream in = null;
		BufferedOutputStream out= null;
		//要发送的文件
		File file = null;
		FileInputStream fis = null;
		try{
			//生成要下载的文件
			file = ZipUtil.createDownloadZip();
			
			if(file ==null || !file.exists() || file.length() == 0) {
				return;
			}
			
			fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			out = new BufferedOutputStream(socket.getOutputStream());
						
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			int read;
			while((read = in.read(buf,0,buf.length)) != -1){
				out.write(buf,0,read);
			}
			out.flush();
			
			out.close();
			in.close();	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				socket.close();
				//删除临时全量文件
				//file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
*/
