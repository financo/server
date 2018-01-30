package com.test.chat.demo03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	public static List<Dispatcher> lists = new ArrayList<Dispatcher>();
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			Socket client = server.accept();
			Dispatcher dispatcher = new Dispatcher(client);
			lists.add(dispatcher);
			dispatcher.setLists(lists);
			new Thread(dispatcher).start();
		}
	}
}
