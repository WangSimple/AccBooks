/*
package com.fatrui.accbooks.util;

import java.io.File;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;




public class HistoryVersionUtil {
	private static final Logger logger = LogManager.getLogger(HistoryVersionUtil.class);
	
	private static final String historyPath="C:\\cuss\\travelskycuss\\updconf\\history.xml";

	//获取当前生效的主版本
	public static String getEffectVersion()throws Exception{
		Document document;
		try {
			 // 1.创建一个SAXReader对象reader
			SAXReader reader = new SAXReader();
			File xmlConfig=new File(historyPath);
			document = reader.read(xmlConfig);
		} catch (Exception e) {
			logger.error("init VersionUtil failed : "+e);
			throw new Exception("init VersionUtil failed : "+e);
		}
		Node node=document.getRootElement().selectSingleNode("version[@isDefault='true']");
		Element effectversion= (Element) node;
		if (effectversion!=null) {
			logger.info("effect version  : "+effectversion.attributeValue("value"));
			return effectversion.attributeValue("value");
		}else{
			logger.warn("can not find the effect version");
			throw new Exception("can not find the effect version");
		}
	}
	
	public static void main(String [] args){
		try {
			HistoryVersionUtil.getEffectVersion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
*/
