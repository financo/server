package com.wzy.server;

import java.io.IOException;
import java.net.Socket;

import com.wzy.servlet.Servlet;
import com.wzy.util.CloseUtil;

public class Dispatcher implements Runnable{

	private Socket client;
	private Request request;
	private Response response;
	private int code = 200;
	
	public Dispatcher(Socket client) {
		this.client = client;
		try {
			request = new Request(client.getInputStream());
			response = new Response(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			code = 500;
			return;
		}
	}
	
	@Override
	public void run() {
		try {
			Servlet servlet = WebApp.getServlet(request.getUrl());
			if (null == servlet) {
				this.code = 404;
			}else {
				servlet.service(request, response);
			}
			response.pushToClient(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CloseUtil.closeSocket(client);
	}

}
