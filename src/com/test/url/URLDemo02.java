package com.test.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLDemo02 {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.sina.com");
		
		System.out.println(url.toString());
		System.out.println(url.getProtocol());
		System.out.println(url.getHost());
		System.out.println(url.getPort());
		System.out.println(url.getPath());
		System.out.println(url.getFile());
		System.out.println(url.getRef());
		
		InputStream openStream = url.openStream();
		int len;
		byte[] data = new byte[1024];
		StringBuilder sb = new StringBuilder();
		while ((len = openStream.read(data) )!= -1) {
			sb.append(new String(data, 0, len));
		}
		System.out.println(sb.toString());
	}
}
