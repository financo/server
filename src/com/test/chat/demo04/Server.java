package com.test.chat.demo04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
	
	public static Map<Dispatcher,String> userLists = new HashMap<Dispatcher, String>();
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			Socket client = server.accept();
			Dispatcher dispatcher = new Dispatcher(client);
			userLists.put(dispatcher, "未知用户");
			dispatcher.setLists(userLists);
			new Thread(dispatcher).start();
		}
	}
}
