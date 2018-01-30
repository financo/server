package com.test.chat.demo03;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {

	private Socket client;
	private boolean isRunning = true;
	private DataOutputStream dos;
	private BufferedReader br;

	public Send(Socket client) {
		this.client = client;
		try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeSocket(client);
		}
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run() {
		String msg;
		while (isRunning) {
			msg = getConsoleMsg();
			sendMsg(msg);
		}
	}

	private String getConsoleMsg() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void sendMsg(String msg) {
		if (msg != null) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeSocket(client);
			}
			msg = null;
		}
	}
}
