package com.test.chat.demo03;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("192.168.1.2", 8888);
		
		Receive receive = new Receive(client);
		Send send = new Send(client);
		new Thread(receive).start();
		new Thread(send).start();
	}
}
