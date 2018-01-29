package com.wzy.server;
/**
 * 上下文
 * @author wzy
 *
 */

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	//每个servlet取个别名
	private Map<String, String> servlet;
	//url到servlet的映射
	private Map<String, String> mapping;
	
	public ServletContext() {
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
	}

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
	
}
