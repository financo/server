package com.wzy.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.wzy.util.CloseUtil;

/**
 * 添加servlet，使用servlet处理请求并给出响应
 * 添加Dispatcher，分发请求
 * @author wzy
 *
 */
public class Server {
	private ServerSocket server;
	public static final String BLANK = " ";
	public static final String CRLF = "\r\n";
	
	private boolean isShutdown = false;
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	/*
	 * 启动方法
	 */
	public void start() {
		start(8888);
	}
	
	public void start(int port) {
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
	}
	
	/*
	 * 接收请求
	 */
	public void receive() {
		try {
			while (!isShutdown) {
				new Thread(new Dispatcher(server.accept())).start();
			}
 		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
		
	}
	
	/*
	 * 停止方法
	 */
	public void stop() {
		isShutdown = true;
		CloseUtil.closeSocket(server);
	}
}
