/*
package com.fatrui.accbooks.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
*/
/**
 * 
* @ClassName: SocketUtil  
* @Description:Socket工具类，包含服务端和客户端，用于本地网络文件传输
* @author dgy  
* @date 2015年6月10日 下午2:46:30  
*
 *//*

public class SocketUtil {
	//端口号
	private static  int  port*/
/*= Integer.parseInt(ConfigFile.getValue("port"))*//*
;
	//线程池大小
	private static  int pool_size  = 10;
	private static ExecutorService service = Executors.newFixedThreadPool(pool_size);
		
	*/
/**
	 * 
	 * @Description: 服务端(随程序启动)，负责监听请求，发送文件
	 *//*

	public static void serve(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		try{
			serverSocket = new ServerSocket(port);
			while(true){
				socket = serverSocket.accept();
				service.execute(new Handler(socket));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	*/
/**
	 * @Description: 发送请求，从本地主机下载文件
	 * @param ipList  本地主机ip列表
	 * @param destFile 保存的结果文件名
	 *//*

	public static void localDownload(String[] ipList,String destFile){
		Socket socket = null;
		BufferedInputStream in = null;
		BufferedOutputStream out= null;
		try{
			//连接可下载的服务端
			for(int i=0;i<ipList.length;i++){
				//创建连接
				socket = new Socket(ipList[i],port);
				//如果连接上，就使用
				if(socket.isConnected()){
					break;
				}else{
					continue;
				}
			}
			//
			Thread.sleep(1000);			
			out = new BufferedOutputStream(new FileOutputStream(destFile));
			in = new BufferedInputStream(socket.getInputStream());
			
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			int read;
			while((read = in.read(buf,0,buf.length)) != -1){
				out.write(buf,0,read);
			}
			
			out.flush();			
			out.close();
			in.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
*/
