package com.test.chat.demo03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Dispatcher implements Runnable {

	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String msg;
	public List<Dispatcher> lists = new ArrayList<Dispatcher>();

	public Dispatcher(Socket client) {
		this.client = client;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			CloseUtil.closeSocket(client);
		}
	}

	@Override
	public void run() {
		while (true) {
			msg = receiveMsg();
			if (null != msg) {
				for (Dispatcher dispatcher : lists) {
					if (dispatcher != this) {
						dispatcher.sendMsg("server: " + msg);
					}
				}
			}
		}
	}

	private String receiveMsg() {
		try {
			return dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void sendMsg(String temp) {
		if (temp != null) {
			try {
				dos.writeUTF(temp);
				dos.flush();
			} catch (IOException e) {
				CloseUtil.closeSocket(client);
			}
		}
	}

	public List<Dispatcher> getLists() {
		return lists;
	}

	public void setLists(List<Dispatcher> lists) {
		this.lists = lists;
	}
	
	
}
