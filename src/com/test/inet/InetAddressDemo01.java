package com.test.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo01 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
		
		addr = InetAddress.getByName("www.baidu.com");
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
		
		addr = InetAddress.getByName("111.7.177.233");
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
	}
}
