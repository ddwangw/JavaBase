package com.io.base.spider;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Test {
	public static void main(String[] args) {
		HttpClient httpClient = new HttpClient();
		//http://wenku.baidu.com/view/45f2a35a804d2b160b4ec0da?fr=prin 
		GetMethod getMethod = new GetMethod("https://wenku.baidu.com/view/45f2a35a804d2b160b4ec0da?fr=prin");
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if(statusCode != HttpStatus.SC_OK) {
				System.err.println("获取失败："+getMethod.getStatusLine());
			}
			//获取
			byte[] responseBody = getMethod.getResponseBody();
			System.out.println(new String(responseBody));
			
		} catch (HttpException e) {
			System.out.println("获取失败，请重新获取");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			getMethod.releaseConnection();
		}
	}
}
