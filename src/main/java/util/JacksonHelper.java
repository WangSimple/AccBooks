/*
package com.fatrui.accbooks.util;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

*/
/**
 * 
* <p>
* 获取jackson的mapper对象
* </p>
* <p><b>Copyright: All Rights Reserved (c) 2015-2016</b></p>
* <p><b>Company:Travelsky</b></p>
* @ClassName: JacksonHelper 
* @author litan 
* @date 2015-3-13 上午10:43:14 
*
 *//*

public class JacksonHelper {
	
	private static final ObjectMapper objectMapper = new ObjectMapper()
			.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)//允许json的key不带双引号
			.configure(Feature.ALLOW_SINGLE_QUOTES, true)//允许json使用单引号
			.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES , false);//允许未知属性
	
	static{
		
		*/
/**
		 * 允许空对象 . 但是注意: new Object(); 仍然无法转为"{}", 该逻辑被jackson在代码中写死......
		 *//*

		objectMapper.getSerializationConfig().set(  
				org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

	}	
	
	public static final ObjectMapper objectMapper(){
		return objectMapper;
	}
}
*/
