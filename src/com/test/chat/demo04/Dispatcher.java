package com.test.chat.demo04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.test.chat.demo03.CloseUtil;

public class Dispatcher implements Runnable {

	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String msg;
	public Map<Dispatcher,String> userLists = new HashMap<Dispatcher,String>();

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
			if ("未知用户".equals(userLists.get(this))) {
				register(msg);
			}
			if (!msg.contains("name@") && !msg.contains("@@")) {
				sendToOthers(msg, userLists.get(this));
			}
			if (msg.contains("@@")) {
				sendToSpecial(msg.substring(msg.indexOf("@@") + 2, msg.length()), msg.substring(0, msg.indexOf("@@")));
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
	
	private void sendMsg(String message) {
		if (message != null) {
			try {
				dos.writeUTF(message);
				dos.flush();
			} catch (IOException e) {
				CloseUtil.closeSocket(client);
			}
		}
	}
	
	//发送私信
	private void sendToSpecial(String message, String to) {
		for (Entry<Dispatcher, String> entry  : userLists.entrySet()) {
			if (null != to && to.equals(entry.getValue())) {
				entry.getKey().sendMsg(userLists.get(this) + "悄悄的对你说： " + message);
			}
		}
	}
	
	//注册进入聊天室
	private void register(String message) {
		if (null != msg && msg.contains("name@")) {
			for (Entry<Dispatcher, String> entry : userLists.entrySet()) {
				if (entry.getKey() == this) {
					userLists.put(entry.getKey(), msg.substring(msg.indexOf("name@") + 5, msg.length()));
				}
			}
			sendToOthers(userLists.get(this) + " 进入聊天室！");
		}
	}

	//发送系统消息
	private void sendToOthers(String message) {
		sendToOthers(message, "server");
	}
	
	//群聊发送给其他人
	private void sendToOthers(String message, String from) {
		for (Entry<Dispatcher, String> entry : userLists.entrySet()) {
			if (entry.getKey() != this) {
				entry.getKey().sendMsg(from + " 说: " + message);
			}
		}
	}

	
	public Map<Dispatcher,String> getLists() {
		return userLists;
	}

	public void setLists(Map<Dispatcher,String> userLists) {
		this.userLists = userLists;
	}

}
