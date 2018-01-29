package com.wzy.server;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.wzy.server.xml.Entity;
import com.wzy.server.xml.Mapping;
import com.wzy.server.xml.WebHandler;
import com.wzy.servlet.Servlet;

public class WebApp {
	private static ServletContext context;
	
	static {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			WebHandler webHandler = new WebHandler();
			
			parser.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("WEB-INFO/web.xml")
					, webHandler);
			context = new ServletContext();
			
			Map<String, String> servlet = context.getServlet();
			for (Entity entity : webHandler.getEntityList()) {
				servlet.put(entity.getName(), entity.getClazz());
			}
			
			Map<String, String> mapping = context.getMapping();
			for (Mapping map : webHandler.getMappingList()) {
				List<String> urls = map.getUrlPattern();
				for (String url : urls) {
					mapping.put(url, map.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Servlet getServlet(String url) {
		if ((null == url) || "".equals(url = url.trim())) {
			return null;
		}
		try {
			return (Servlet) Class.forName(context.getServlet().get(context.getMapping().get(url))).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
