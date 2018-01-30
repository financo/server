package com.test.inet;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetSocketAddressDemo02 {
	public static void main(String[] args) throws UnknownHostException {
		InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
		System.out.println(addr.getHostName());
		System.out.println(addr.getPort());
		
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());
		
		address = InetAddress.getByName("111.7.177.233");
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());
	}
}
