package com.test.chat.demo03;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil {
	public static <T extends Closeable> void closeIO(T... io) {
		for (Closeable closeable : io) {
			try {
				if (null != closeable) {
					closeable.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeSocket(ServerSocket serverSocket) {
		try {
			if (null != serverSocket) {
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeSocket(Socket socket) {
		try {
			if (null != socket) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeSocket(DatagramSocket datagramSocket) {
		try {
			if (null != datagramSocket) {
				datagramSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
