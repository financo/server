package com.test.chat.demo04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("192.168.1.2", 8888);
		System.out.println("请输入您的昵称！");
		String name =new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		Receive receive = new Receive(client);
		Send send = new Send(client, name);
		new Thread(receive).start();
		new Thread(send).start();
	}
}
