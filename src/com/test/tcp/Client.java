package com.test.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.xml.crypto.Data;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("192.168.1.2", 8888);
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		OutputStream out= socket.getOutputStream();
//		bw.write("hello");
		byte[] data = new byte[1024*1024*256]; 
		FileInputStream fis = new FileInputStream(new File("D:\\install\\OS\\ubuntu-17.10-desktop-amd64.iso"));
		int length = fis.read(data);
		out.write(data);
		out.write("hello".getBytes());
//		bw.flush();
		System.out.println(out.toString());
		out.flush();
		out.close();
//		Thread.sleep(5000);
		out = socket.getOutputStream();
		System.out.println(out.toString());
//		bw.write("how are you?");
		out.write("how are you?".getBytes());
//		bw.flush();
		out.flush();
		out.close();
//		bw.close();
		System.out.println("--------------write end---------------");
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String reply;
		while ((reply = br.readLine()).length() > 0) {
			System.out.println(reply);
		}
		br.close();
		System.out.println("--------------end---------------");
	}
}
