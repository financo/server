package com.wzy.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import com.wzy.util.CloseUtil;

public class Response {
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	private BufferedWriter bw;
	
	private StringBuilder context;
	
	private StringBuilder headInfo;
	
	private int length;
	
	public Response() {
		headInfo = new StringBuilder();
		context = new StringBuilder();
		length = 0;
	}
	
	public Response(OutputStream os) {
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public Response(Socket socket) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			headInfo = null;
			e.printStackTrace();
		}
	}
	
	public Response print(String info) {
		context.append(info);
		length += info.getBytes().length;
		return this;
	}
	
	public Response println(String info) {
		context.append(info).append(CRLF);
		length += (info + CRLF).getBytes().length;
		return this;
	}
	
	private void createHeadInfo(int code) {
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
			case 200:
				headInfo.append("OK");
				break;
			case 404:
				headInfo.append("NOT FOUND");
				break;
			case 500:
				headInfo.append("SERVER ERROR");
				break;
		}
		headInfo.append(CRLF);
		//响应头
		headInfo.append("Server:my Server/0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		//正文长度：字节长度
		headInfo.append("Context-Length:").append(length).append(CRLF);
		//设置响应字符集
		headInfo.append("Content-Type:").append("text/html; charset=utf-8").append(CRLF);
		//正文之前
		headInfo.append(CRLF);
	}
	
	void pushToClient(int code) throws IOException {
		if (null == headInfo) {
			code = 500;
		}
		createHeadInfo(code);
		bw.append(headInfo.toString());
		bw.append(context.toString());
		bw.flush();
	}
	
	public void close() {
		CloseUtil.closeIO(bw);
	}
}
