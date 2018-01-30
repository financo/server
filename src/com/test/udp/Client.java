package com.test.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket client = new DatagramSocket(6666);
		String msg = "hello";
		DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, 
				new InetSocketAddress("localhost",8888));
		client.send(packet);
		client.close();
	}
}
