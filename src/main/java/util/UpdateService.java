/**   
* @Title: queryVersionService.java 
* @Package com.travelsky.softupate.httpservice 
* @Description: 访问查询和更新状态服务的service 
* @author litan   
* @date 2015-3-13 上午10:13:10 
*//*

package com.fatrui.accbooks.util;


import java.io.IOException;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.travelsky.updatetool.httpservice.bean.PackageInfo;
import com.travelsky.updatetool.httpservice.bean.UpdateToolRequestBean;
import com.travelsky.updatetool.httpservice.bean.UpdateToolResponseBean;
import com.travelsky.updatetool.util.HistoryXMLConfig;
import com.travelsky.updatetool.util.JacksonHelper;
import com.travelsky.updatetool.util.StringUtil;
import com.travelsky.updatetool.util.SysPropUtil;

*/
/**
 * <p>
 * 用于访问MSI_SYS后台服务提供的查询更新版本以及更新下载状态接口的类
 * </p>
 * <p><b>Copyright: All Rights Reserved (c) 2015-2016</b></p>
 * <p><b>Company:Travelsky</b></p>
 * @ClassName: queryVersionService 
 * @author xpwang 
 * @date 2016-3-11 上午10:13:10 
 *  
 *//*

public class UpdateService {
	
	private static final Logger logger = LogManager.getLogger(UpdateService.class);
	private static final String URL=SysPropUtil.getValue("serverUrl");
	
	*/
/**
	 * 前端应用程序在启动时，将唯一标识、本地最新的版本(主版本及功能模块版本)、本地IP、机场、航空公司 传给后台激活应用程序。
	@param apps[]   [exbg_1.0, rebook1.1, payseat_1.0]
	 * @return
	 *//*

	public static boolean activateClientApp(String UID,String clientType,String groupName, String rversion,String mainversion,String[] apps,String airline,String airport,String localIP  ){
		logger.info("UpdateService activateClientApp()");
		if (StringUtil.isEmpty(UID)||StringUtil.isEmpty(clientType)||StringUtil.isEmpty(rversion)||StringUtil.isEmpty(localIP)||StringUtil.isEmpty(mainversion)||apps.length==0) {
			logger.info("activateClientApp the parm is invalid!");
			return false;
		}
		UpdateToolRequestBean request=new UpdateToolRequestBean();
		request.setClientID(UID);
		request.setClientType(clientType);
		request.setClientGroup(groupName);
		request.setRversion(rversion);
		request.setApps(apps);
		request.setAirline(airline);
		request.setMainversion(mainversion);
		request.setAirport(airport);
		request.setLocalIP(localIP);
		UpdateToolResponseBean uRes=callService(URL,request,"activateClient");
		if (uRes==null) {
			logger.error("connect Service Exception! ");
			return false;
		}else{
			if (uRes.getErrcode()==0) {
				logger.info(uRes.getErrMsg());
				try {
					SysPropUtil.saveValue("clientID", UID);
					SysPropUtil.saveValue("clientType", clientType);
					HistoryXMLConfig.writeConfig(rversion,apps);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("write to history.xml Exception: "+e+" ! ");
					return false;
				}
				//获取公钥
				try {
					getPublicKey();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.warn("get RSA publicKey Exception : "+e);
				}
				return true;
			}else{
				logger.warn(uRes.getErrMsg());
				return false;
			}
		}
		
		
	}
	
	*/
/**
	 * 获取当前生效app数组
	 * @return
	 * @throws Exception
	 *//*

	public static String[] getEffectApps()throws Exception{
		return HistoryXMLConfig.getEffectApps();
	}
	*/
/**
	 * 获取当前生效版本号
	 * @return
	 * @throws Exception
	 *//*

	public static String getEffectVerNum()throws Exception{
		return HistoryXMLConfig.getEffectVerString();
	}
	*/
/**
	 * 获取指定app的公钥存到本地config中
	 * @throws Exception
	 *//*

	public static String getPublicKey()throws Exception{
		UpdateToolRequestBean request=new UpdateToolRequestBean();
		String clientType=SysPropUtil.getValue("clientType");
		request.setClientType(clientType);
		UpdateToolResponseBean uRes= callService(URL,request,"getPublicKye");
		if (uRes==null) {
			logger.error("connect Service Exception! ");
			throw new Exception("connect Service Exception !");
		}else if(uRes.getErrcode()!=0){
			logger.error("get PublicKey "+uRes.getErrcode()+" : "+uRes.getErrMsg());
			throw new Exception("get PublicKey "+uRes.getErrcode()+" : "+uRes.getErrMsg());
		}else{
			SysPropUtil.saveValue("update.key", uRes.getRsaPublicKey());
			logger.info("getPublicKey seccess! ");
			return uRes.getRsaPublicKey();
		}
		
	}
	
	public static UpdateToolResponseBean getUpdateInfo(String type,String clientID){
		UpdateToolResponseBean uRes=null;
		UpdateToolRequestBean uReq=new UpdateToolRequestBean();
		uReq.setClientType(type);
		uReq.setClientID(clientID);
		uRes=callService(URL,uReq,"queryUpdate");
		return uRes;
	}
	
	public static UpdateToolResponseBean getVersionInfo(String type,String rversion){
		UpdateToolResponseBean uRes=null;
		UpdateToolRequestBean uReq=new UpdateToolRequestBean();
		uReq.setClientType(type);
		uReq.setRversion(rversion);
		uRes=callService(URL,uReq,"getVersionInfo");
		return uRes;
	}

	
	*/
/**
	 * 访问服务器修改状态
	 * @param clientUID
	 * @param updateStatus
	 * @param exeTm
	 * @return
	 *//*

	public static UpdateToolResponseBean notifyStatus(String clientUID,String Type,String rversion,String updateStatus){
		UpdateToolResponseBean uRes=null;
		UpdateToolRequestBean uReq=new UpdateToolRequestBean();
		uReq.setClientID(clientUID);
		uReq.ExeTmDate(new Date());
		uReq.setStatus(updateStatus);
		uReq.setClientType(Type);
		uReq.setRversion(rversion);
		uRes=callService(URL,uReq,"notifyStatus");
		return uRes;
	}
	
	*/
/**
	 * 调用后台服务
	 * 该方法会调用后台的查询更新版本以及更新下载状态的服务
	 * @param url 服务的url地址
	 * @param requestBean 请求对象
	 * @param type 请求类型：qeury为查询是否需要更新；updateStatus为更新状态
	 * @return QueryVersionResponseBean
	 *//*

	public static UpdateToolResponseBean callService(String url,UpdateToolRequestBean requestBean,String type) {
		if (url==null||"".equals(url)) {
			logger.error("callService() the url is null, return null!");
			return null;
		}
		String re = null;
		UpdateToolResponseBean responseBean = null;
		ObjectMapper objectMapper = JacksonHelper.objectMapper();
		try {
			String requestJsonStr = objectMapper.writeValueAsString(requestBean);
			re = HttpConnectService.requestByHttpConn(url,requestJsonStr,type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Server connection failed : "+e);
		}
		System.out.println(re);
		if (re != null) {
			try {
				responseBean = objectMapper.readValue(re, UpdateToolResponseBean.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseBean;
	}
	
	*/
/*
	 * 获取更新包字节流
	 *//*

	public static PackageInfo getPackageInfo(String fileName,String downloadPath){
		logger.info("getPackageInfo( fileName : "+fileName+" , downloadPath : "+downloadPath+" )");
		if (StringUtil.isEmpty(fileName)||StringUtil.isEmpty(downloadPath)) {
			logger.info("getPackageInfo the parm is invalid!");
			return null;
		}
		UpdateToolRequestBean request=new UpdateToolRequestBean();
		PackageInfo pi=new PackageInfo();
		pi.setFileName(fileName);
		pi.setDownloadPath(downloadPath);
		request.setPackageinfo(pi);
		UpdateToolResponseBean uRes=callService(URL,request,"getPackageInfo");
		if (uRes==null) {
			logger.error("connect Service Exception! ");
			return null;
		}else{
			if (uRes.getErrcode()==0) {
				logger.info(uRes.getErrMsg());
				return uRes.getPackageinfo();
			}else{
				logger.warn(uRes.getErrMsg());
				return null;
			}
		}
	}
	
}
*/
