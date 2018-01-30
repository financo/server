package com.test.chat.demo02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		Socket client = server.accept();
		while (true) {
			
			DataInputStream dis = new DataInputStream(client.getInputStream());
			String msg = dis.readUTF();
			
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF("server: " + msg);
			dos.flush();
		}
	}
}
