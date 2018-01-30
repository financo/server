package com.test.chat.demo03;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	
	private Socket client;
	private boolean isRunning = true;
	private DataInputStream dis;

	public Receive(Socket client) {
		this.client = client;
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeSocket(client);
		}
	}
	
	@Override
	public void run() {
		receiveMsg();
	}
	
	private void receiveMsg() {
		String msg;
		while (isRunning) {
			try {
				if ((msg = dis.readUTF()) != null) {
					System.out.println(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
