/*
package com.fatrui.accbooks.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;


import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public  class BaseXMLConfig {
	public static final Logger logger = LogManager.getLogger(BaseXMLConfig.class);
	
	public static void store(Document doc,File xmlConfig) throws IOException{
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        FileOutputStream fos=new FileOutputStream(xmlConfig);
        XMLWriter writer = new XMLWriter(fos, format);
		writer.write(doc);
        fos.close();
        writer.close();
        logger.info(xmlConfig.getAbsolutePath()+" store success.");
	}
	//
	public static void initConfig(String rootElement,File xmlConfig)throws Exception{
		 Document doc = DocumentHelper.createDocument();
		 doc.addElement(rootElement);
		 OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("utf-8");
	     File dir=new File(xmlConfig.getParent());
	     if (!dir.exists()) {
	    	 dir.mkdirs();
		}
	     FileOutputStream fos=new FileOutputStream(xmlConfig);
	     XMLWriter writer = new XMLWriter(fos, format);
	     writer.write(doc);
	     fos.close();
	     writer.close();
	}
	*/
/**
	 * 获取节点下子节点属性的值
	 * @param thisE 
	 * @param elementName
	 * @param attrName
	 * @return
	 *//*

	public static String getInnerElementAttribute(Element thisE,String elementName,String attrName){
		Element inner=thisE.element(elementName);
		if (inner==null) {
			return null;
		}
		return inner.attributeValue(attrName);
	}
}
*/
