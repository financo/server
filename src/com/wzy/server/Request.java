package com.wzy.server;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Request {
	//请求方式
	private String method;
	//请求资源
	private String url;
	//请求参数
	private Map<String, List<String>> parameterValues;
	
	private static final String CRLF = "\r\n";
	private InputStream is;
	private String requestInfo;
	
	public Request() {
		method = "";
		url = "";
		parameterValues = new HashMap<String, List<String>>();
		requestInfo = "";
	}
	
	public Request(InputStream is) {
		this();
		this.is = is;
		try {
			byte[] data = new byte[20480];
			int length = is.read(data);
			requestInfo = new String(data, 0, length);
		} catch (Exception e) {
			return;
		}
		parseRequestInfo();
	}
	
	private void parseRequestInfo() {
		if (null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}
		//用于保存请求参数
		String paramString = "";
		
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/");
		//获取请求方法
		this.method = firstLine.substring(0, idx).trim();
		//获取请求地址
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim();
		if (this.method.equalsIgnoreCase("post")) {
			this.url = urlStr;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF),requestInfo.length());
		
		}else if (this.method.equalsIgnoreCase("get")) {
			//判断是否有请求参数
			if (urlStr.contains("?")) {
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];
				paramString = urlArray[1];
			}else {
				this.url = urlStr;
			}
		}
		//不存在请求桉树
		if ("".equals(paramString)) {
			return;
		}
		//请求参数封装到map中
		parseParam(paramString);
	}
	
	private void parseParam(String paramString) {
		StringTokenizer token = new StringTokenizer(paramString, "&");
		while (token.hasMoreElements()) {
			String keyValue = token.nextToken();
			String[] keyValues = keyValue.split("=");
			if (keyValues.length == 1) {
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}
			
			String key = keyValues[0].trim();
			String value = null == keyValues[1] ? null : decode(keyValues[1].trim(), "utf-8");
			//参数转换成表map
			if (!parameterValues.containsKey(key)) {
				parameterValues.put(key, new ArrayList<String>());
			}
			
			List<String> values = parameterValues.get(key);
			values.add(value);
		}
	}
	
	private String decode (String value, String code) {
		try {
			return URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getParameterValues(String name) {
		List<String> values = null;
		if ((values = parameterValues.get(name)) == null) {
			return null;
		}else {
			return values.toArray(new String[0]);
		}
	}
	
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if (null == values) {
			return null;
		}
		return values[0];
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
