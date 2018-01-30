package com.test.url;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://www.baidu.com:80/index.html");
		
		System.out.println(url.toString());
		System.out.println(url.getProtocol());
		System.out.println(url.getHost());
		System.out.println(url.getPort());
		System.out.println(url.getPath());
		System.out.println(url.getFile());
		System.out.println(url.getRef());
		
		url = new URL("http://www.baidu.com:80/index.html");
		url = new URL(url, "a.txt");
		
		
	}
}
