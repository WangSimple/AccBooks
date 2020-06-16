/*
package com.fatrui.accbooks.util;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.travelsky.updatetool.httpservice.UpdateService;
import com.travelsky.updatetool.httpservice.bean.UpdateBaseBean;
import com.travelsky.updatetool.httpservice.bean.UpdateToolResponseBean;
import com.travelsky.updatetool.httpservice.bean.UpdateVersionBean;



public class DownloadXMLConfig extends BaseXMLConfig{
	private static final String rootElement="versionLib";
	private static File xmlConfig=new File(SysPropUtil.getConfigPath()+File.separator+"updAppInfo.xml");
	private static Document document;
	
	static{
		try {
			 // 1.创建一个SAXReader对象reader
			SAXReader reader = new SAXReader();
			// 2.通过reader对象的read方法加载xml文件，获取Document对象
			if (!xmlConfig.exists()) {
				initConfig(rootElement,xmlConfig);
			}
			document = reader.read(xmlConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("init DownloadXMLConfig failed : "+e);
		}
	}
	
	public static void  store() throws IOException{
		store(document,xmlConfig);
	}
	
	*/
/**
	 * 根据xml获取所有未下载的数据
	 * @return
	 *//*

	public static List<UpdateToolResponseBean> getWaitingPackage(){
		List<UpdateToolResponseBean> reslist=new ArrayList<UpdateToolResponseBean>();
		List<Element> verlist=getVersByStatus(new String[]{"waiting"},new Date());
		for (Element version:verlist) {
			UpdateToolResponseBean response=new UpdateToolResponseBean();
			UpdateVersionBean versionBean=new UpdateVersionBean();
			List<Element> eFilelist=getFilesByStatus(version,null);
			if (eFilelist==null||eFilelist.size()==0) {
				continue;
			}
			
			String rversion=version.attributeValue("name");
			String startTm=version.element("start_tm").attributeValue("value");
			String apps=version.element("apps").attributeValue("value");
			String downloadPeriod=version.element("download_period").attributeValue("value");
			String localDir=version.element("local_dir").attributeValue("value");
			String downloadPath=version.element("downLoadPath").attributeValue("value");
			String sign=version.element("sign").attributeValue("value");
			StringBuffer sb=new StringBuffer("");
			for (Element eFile : eFilelist) {
				sb.append(UpdateBaseBean.fileRegex+eFile.getText());
			}
			String[] filenames=sb.toString().substring(UpdateBaseBean.appRegex.length()).split(UpdateBaseBean.appRegex);
			versionBean.setVersionNum(rversion);
			versionBean.setVerTm(startTm);
			versionBean.setLocalDir(localDir);
			versionBean.setDownloadPath(downloadPath);
			versionBean.setPackageNames(filenames);
			versionBean.setSign(sign);
			versionBean.setAppNameVerNum(apps);
			
			response.setVersionBean(versionBean);
			response.setDownloadPeriod(downloadPeriod);
			reslist.add(response);
		}
		return reslist;
	}
	
	
	
	*/
/**
	 * 根据xml获取需要更新的数据
	 * @return
	 *//*

	public static UpdateToolResponseBean getUpdateResponse(){
		Element version=getNewVersion(new Date());
		if (version==null) {
			return null;
		}
		UpdateToolResponseBean response=new UpdateToolResponseBean();
		
		String status=version.element("update_status").attributeValue("value");
		String verNum=version.attributeValue("name");
		String downloadPeriod=version.element("download_period").attributeValue("value");
		if ("download".equals(status)||"fail".equals(status)) {
			UpdateVersionBean versionBean=new UpdateVersionBean();
			List<Element> eFilelist=getFilesByStatus(version,null);
			if (eFilelist==null||eFilelist.size()==0) {
				return null;
			}
			StringBuffer sb=new StringBuffer("");
			for (Element eFile : eFilelist) {
				sb.append(";"+eFile.getText());
			}
			String[] filenames=sb.toString().substring(1).split(";");
			
			String startTm=version.element("start_tm").attributeValue("value");
			String mainVersion=version.attributeValue("name");
			
			String localDir=version.element("local_dir").attributeValue("value");
			String apps=version.element("apps").attributeValue("value");
			String downloadPath=version.element("downLoadPath").attributeValue("value");
			String sign=version.element("sign").attributeValue("value");
			
			versionBean.setPackageNames(filenames);
			versionBean.setLocalDir(localDir);
			versionBean.setSign(sign);
			versionBean.setDownloadPath(downloadPath);
			
			versionBean.setVersionNum(mainVersion);
			versionBean.setVerTm(startTm);
			versionBean.setAppNameVerNum(apps);
			response.setVersionBean(versionBean);
			response.setDownloadPeriod(downloadPeriod);
		}
		response.setUpdStatus(status);
		response.setRversion(verNum);
		return response;
	}
	
	public static void writeUpdateinfo(UpdateToolResponseBean uRes,String status)throws Exception{
		logger.info("DownloadXMLConfig writeUpdateinfo.");
		if (uRes==null) {
			logger.error("UpdateToolResponseBean is null.");
			throw new Exception("UpdateToolResponseBean is null.");
		}
		String verNum=uRes.getRversion();
		Element	version=getVersionByNum(verNum);
		if (version!=null) {
			document.getRootElement().remove(version);
		}
        version=document.getRootElement().addElement("version");
        version.addAttribute("name",uRes.getRversion());
        version.addElement("update_status").addAttribute("value",status);
        version.addElement("start_tm").addAttribute("value",uRes.getStartTm());
        version.addElement("download_period").addAttribute("value",uRes.getDownloadPeriod());
        store();
	}
	
	public static void writeVersionInfo(UpdateToolResponseBean uRes)throws Exception{
		logger.info("DownloadXMLConfig writeVersionInfo.");
		if (uRes==null) {
			logger.error("UpdateToolResponseBean is null.");
			throw new Exception("UpdateToolResponseBean is null.");
		}
		String verNum=uRes.getVersionBean().getVersionNum();
		Element	version=getVersionByNum(verNum);
		if (version==null) {
			logger.error("con't find version("+verNum+") from updappinfo.xml .");
			throw new Exception("con't find version("+verNum+") from updappinfo.xml .");
		}
        version.addElement("apps").addAttribute("value",uRes.getVersionBean().getAppNameVerNum());
        version.addElement("downLoadPath").addAttribute("value",uRes.getVersionBean().getDownloadPath());
        version.addElement("local_dir").addAttribute("value",uRes.getVersionBean().getLocalDir());
        version.addElement("sign").addAttribute("value",uRes.getVersionBean().getSign());
    	Element filelist = version.addElement("fileList");
    	String[] filearr=uRes.getVersionBean().getPackageNames();
 		for (int j = 0; j < filearr.length; j++) {
 			Element filename= filelist.addElement("filename").addAttribute("status","waiting");
 			filename.setText(filearr[j]);
		}
        store();
	}

	*/
/**
	 * 根据版本号获取Version节点
	 * @param mainVersion
	 * @return
	 *//*

	public static Element getVersionByNum(String mainVersion){
		
		Element versions = document.getRootElement();
		Element version=null; 
		for (Iterator i = versions.elementIterator("version"); i.hasNext();) { 
			Element foo = (Element) i.next(); 
			String name=foo.attributeValue("name");
			if (name==null) {
				continue;
			}
        	if (name.equals(mainVersion)) {
				version=foo;
				break;
			}
		}
		return version;
	}
	*/
/**
	 * 根据status获取version Element  如果参数为null 返回全部version
	 * @param status
	 * @return
	 *//*

	public static List<Element> getVersByStatus(String[] status){
		
		 return getVersByStatus(status,null);
	}
	public static List<Element> getVersByStatus(String[] status,Date downloadTime){
		
		 List<Element> verlist=new ArrayList<Element>();
		 Element versions = document.getRootElement();
		 List<Element> versionlist=versions.elements("version");
		 for (Element foo : versionlist) {
			 Element updstatus=foo.element("update_status");
			 Element dlperiod=foo.element("download_period");
			 if (updstatus==null||dlperiod==null) {
				continue;
			}
			if (downloadTime!=null) {
				try {
					String dltime=dlperiod.attributeValue("value");
					String bTime=dltime.split("-")[0];
					String eTime=dltime.split("-")[1];
					Date bDate=UpdateBaseBean.getDateByTime(bTime);
					Date eDate=UpdateBaseBean.getDateByTime(eTime);
					if (downloadTime.getTime()>eDate.getTime()||downloadTime.getTime()<bDate.getTime()) {
						continue;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					continue;
				}
			}
			 if (status!=null&&status.length!=0) {
				 boolean inner=false;
				 for (int j = 0; j < status.length; j++) {
					 if (updstatus.attributeValue("value").equals(status[j])) {
						inner=true;
						break;
					 }	
				 }
				 if (!inner) {
					continue;
				}
			 }
			 verlist.add(foo);
		}
		 return verlist;
	}
	
	public static void deleteElement(Element element)throws Exception{
		document.getRootElement().remove(element);
		store();
	}
	public static void deleteVersion(String verNum)throws Exception{
		Element e=getVersionByNum(verNum);
		deleteElement(e);
	}
	
	//获取APP列表
	public static String getAppsByVerNum(String verNum) throws Exception{
		Element version=getVersionByNum(verNum);
		return getAppsByVerNum(version);
	}
	public static String getAppsByVerNum(Element version) throws Exception{
		return version.element("apps").attributeValue("value");
	}
	
	*/
/**
	 * 修改数据库中 status 
	 * 如果修改成功则 修改配置文件中version  status  
	 * @param mainVersion
	 * @param status
	 * @throws Exception
	 *//*

	public static void setVerStatus(String mainVersion,String status) throws Exception{
		UpdateToolResponseBean response=UpdateService.notifyStatus(SysPropUtil.getValue("clientID"),SysPropUtil.getValue("clientType"),mainVersion,status);
		if (response.getErrcode()==0) {
			Element version=getVersionByNum(mainVersion);
			if (version==null) {
				return;
			}
			Element update_status=version.element("update_status");
			update_status.addAttribute("value", status);
			store();
		}else if (response.getErrcode()==200) {
			logger.error("UpdateService notifyStatus failed : "+response.getErrMsg());
			throw new Exception("UpdateService notifyStatus failed : "+response.getErrMsg());
			
		}else if (response.getErrcode()==404) {
			logger.error("UpdateService notifyStatus exception : "+response.getErrMsg());
			throw new Exception("UpdateService notifyStatus exception : "+response.getErrMsg());
		}else{
			logger.error("UpdateService notifyStatus unknow error : "+response.getErrMsg());
			throw new Exception("UpdateService notifyStatus unknow error : "+response.getErrMsg());
		}
		
	}
	
	
	public static Element getAppByName(String mainVersion,String appName){
		Element version = getVersionByNum(mainVersion);
		if (version==null) {
			return null;
		}
		Element applications= version.element("applications");
		if (applications==null) {
			return null;
		}
		Element application=null;
		for (Iterator i = applications.elementIterator("application"); i.hasNext();) { 
			Element foo = (Element) i.next(); 
			Element ename=foo.element("name");
			String name=ename.attributeValue("value");
        	if (name.equals(appName)) {
        		application=foo;
				break;
			}
		}
		return application;
	}
	
	public static List<Element> getAppsByStatus(Element version,String[] status){
		if (version==null) {
			return null;
		}
		List<Element> applist=new ArrayList<Element>();
		Element applications= version.element("applications");
		if (applications==null) {
			return null;
		}
		Element application=null;
		for (Iterator i = applications.elementIterator("application"); i.hasNext();) { 
			 Element foo = (Element) i.next(); 
			 Element updstatus=foo.element("update_status");
			 if (status==null||status.length==0) {
				 applist.add(foo);
			 }else{
				 for (int j = 0; j < status.length; j++) {
					 if (updstatus.attributeValue("value").equals(status[j])) {
						 applist.add(foo);
						 break;
					 }	
				 }
			 }
		}
		return applist;
	}

	public static List<Element> getAppsByStatus(String[] status){
		List<Element> elist=new ArrayList<Element>();
		List<Element> verlist=getVersByStatus(null);
		for (Element ver : verlist) {
			List<Element> alist=getAppsByStatus(ver, status);
			for (Element element : alist) {
				elist.add(element);
			}
		}
		return elist;
	}
	
	
	*/
/**
	 * 检查指定version节点下的所有 application的status是否都为 参数status
	 * @param application
	 * @param status
	 * @return
	 *//*

	public static boolean checkAppStatus(Element version,String status){
		boolean isr=true;
		if (version==null) {
			return false;
		}
		Element applications= version.element("applications");
		if (applications==null) {
			return false;
		}
		for (Iterator i = applications.elementIterator("application"); i.hasNext();) { 
			Element foo = (Element) i.next(); 
			Element update_status=foo.element("update_status");
			String astatus=update_status.attributeValue("value");
        	if (!astatus.equals(status)) {
        		isr=false;
				break;
			}
		}
		return isr;
	}
	
	*/
/*public static void setAppStatus(String mainVersion,String nameVer,String status) throws Exception{
		Element application=getAppByName( mainVersion, nameVer);
		if (application==null) {
			return;
		}
		Element update_status=application.element("update_status");
		update_status.addAttribute("value", status);
		if (status=="download") {
			Element version=getVersionByNum(mainVersion);
			if (checkAppStatus(version, "download")) {
				setVerStatus(mainVersion, "download");
			}
		}
		if (status=="effect") {
			Element version=getVersionByNum(mainVersion);
			if (checkAppStatus(version, "effect")) {
				//如果大版本生效，删除updAppInfo.xml中内容，添加到history.xml
				String[] apps=getApps(getAppsByStatus(version, null));
				HistoryXMLConfig.writeConfig(mainVersion,apps,true);
				setVerStatus(mainVersion, "effect");
				document.getRootElement().remove(version);
			}
		}
		store();
	}*//*

	
	
	public static Element getFileByName(String mainVersion,String fileName){
		Element version = getVersionByNum(mainVersion);
		if (version==null) {
			return null;
		}
		Element filelist=version.element("fileList");
		if (filelist==null) {
			return null;
		}
		Element filename=null;
		for (Iterator i = filelist.elementIterator("filename"); i.hasNext();) { 
			Element foo = (Element) i.next(); 
			String name=foo.getText();
        	if (name.equals(fileName)) {
        		filename=foo;
				break;
			}
		}
		return filename;
	}
	*/
/**
	 * 获取app下状态为status的files集合，如果status=null 返回所有
	 * @param application
	 * @param status
	 * @return
	 *//*

	public static List<Element> getFilesByStatus(Element version,String[] status){
		if (version==null) {
			return null;
		}
		Element filelist=version.element("fileList");
		if (filelist==null) {
			return null;
		}
		List<Element> flist=new ArrayList<Element>();
		Element filename=null;
		for (Iterator i = filelist.elementIterator("filename"); i.hasNext();) { 
			Element foo = (Element) i.next(); 
			 if (status==null||status.length==0) {
				 flist.add(foo);
			 }else{
				for (int j = 0; j < status.length; j++) {
					if (foo.attributeValue("status").equals(status[j])) {
						flist.add(foo);
						break;
					}	
				}
			 }
		}
		return flist;
	}
	
	*/
/**
	 * 设置filename 状态
	 * @param mainVersion
	 * @param appName
	 * @param packageName
	 * @param status
	 * @throws Exception
	 *//*

	public static void setFileStatus(String mainVersion,String packageName,String status) throws Exception{
		Element filename=getFileByName( mainVersion, packageName);
		if (filename==null) {
			return;
		}
		filename.addAttribute("status", status);
		//不判断了，RSA验证成功后修改ver  status
		*/
/*if (status=="download") {
			Element version=getVersionByNum(mainVersion);
			if (checkFileStatus(version,"download")) {
				setVerStatus(mainVersion,"download");
			}
		}*//*

		
		store();
	}
	
	*/
/**
	 * 检查指定app节点下的所有 filename的status是否都为 参数status
	 * @param application
	 * @param status
	 * @return
	 *//*

	public static boolean checkFileStatus(Element version,String status){
		boolean isr=true;
		if (version==null) {
			return false;
		}
		Element filelist=version.element("fileList");
		if (filelist==null) {
			return false;
		}
		for (Iterator i = filelist.elementIterator("filename"); i.hasNext();) { 
			Element foo = (Element) i.next(); 
			String fstatus=foo.attributeValue("status");
        	if (!fstatus.equals(status)) {
        		isr=false;
				break;
			}
		}
		return isr;
	}
	
	
	
	*/
/**
	 * 根据配置文件获取本地已经下载的最新版本的app信息
	 * @return
	 *//*
*/
/*
	public static String[] getDownloadApp(){
		return getApps(getAppsByStatus(new String[]{"download"}));
	}
	public static String[] getAllApp(){
		return getApps(getAppsByStatus(null));
	}
	public static String[] getApps(List<Element> elist){
		List<String> applist=new ArrayList<String>();
		for (Element element : elist) {
			applist.add(getInnerElementAttribute(element, "name", "value"));
		}
		String[] arr={};
		return applist.toArray(arr);
	}*//*

	//获取最新的版本(生效时间最晚的)
	public static Element getNewVersion(Date  cutOff){
		Element version=null;
		long datetime=0;
		List<Element> verlist=getVersByStatus(new String[]{"download","fail","rollback","r_fail"});
		for (Element ver:verlist) {
			String startTm=ver.element("start_tm").attributeValue("value");
			try {
				long date=UpdateBaseBean.dateSdf.parse(startTm).getTime();
				if (cutOff!=null) {
					if (date>cutOff.getTime()) {
						continue;
					}
				}
				if (datetime<date) {
					datetime=date;
					version=ver;
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			
		}
		return version;
	}
	
	
	public static void main(String [] args){
		
		System.out.println(123);
	}
}
*/
