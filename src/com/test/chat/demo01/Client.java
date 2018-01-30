package com.test.chat.demo01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("192.168.1.3", 8888);
		
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String in = bf.readLine();
			dos.writeUTF(in);
			dos.flush();
			
			String msg = dis.readUTF();
			System.out.println(msg);
		}
		
	}
}
