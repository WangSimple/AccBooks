/*
package com.fatrui.accbooks.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

*/
/**
 * HttpClient方式调用服务，要经过MsiFilter的验证
 * 使用方法：HttpConnectService.getInstance()
 * @author zy v3.2.0_框架
 *
 *//*

public class HttpConnectService {
	private static final Logger logger = LogManager.getLogger(HttpConnectService.class);

	public static String requestByHttpConn(String url, String reqParms, String type) throws IOException {
		HttpParams httpParams = new BasicHttpParams();
		 // 设置连接超时和 Socket 超时，以及 Socket 缓存大小
        HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
        //HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
        //HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient(httpParams);

		HttpPost httppost = new HttpPost(url);		
		List<NameValuePair> formParams = new LinkedList<NameValuePair>();

		formParams.add(new BasicNameValuePair("data", reqParms));
		formParams.add(new BasicNameValuePair("type", type));

		HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");

		httppost.setEntity(entity);
		
		HttpResponse response = httpclient.execute(httppost);
		
		logger.debug("statusCode is " + response.getStatusLine().getStatusCode());
		HttpEntity resEntity = response.getEntity();
		logger.debug("----------------------------------------");
		logger.debug(response.getStatusLine());
		if (resEntity != null) {
			logger.debug("返回长度: " + resEntity.getContentLength());
			logger.debug("返回类型: " + resEntity.getContentType());
			InputStream in = resEntity.getContent();
			logger.debug("in is " + in);
			String inS = inputStream2String(in);
			logger.debug(inS);

			return inS;
		}
		return "";
	}

	private static String inputStream2String(InputStream in) throws IOException {
		//这里的编码规则要与上面的相对应
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		String tempbf;
		StringBuffer out = new StringBuffer(4096);
		while ((tempbf = br.readLine()) != null) {
			out.append(tempbf);
		}
		return out.toString();
	}
	
}
*/
