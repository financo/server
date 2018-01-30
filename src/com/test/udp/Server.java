package com.test.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	public static void main(String[] args) throws IOException {
		
		DatagramSocket server = new DatagramSocket(8888);
		
		byte[] data = new byte[1024];
		
		DatagramPacket packet = new DatagramPacket(data, data.length);
		
		server.receive(packet);
		
		byte[] myData = packet.getData();
		
		int len = packet.getLength();
		
		System.out.println(new String(myData, 0, len));
		
		server.close();
	}
}
