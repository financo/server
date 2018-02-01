package com.test.tcp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * BIO为阻塞单向数据传输，输出流关闭之后，输入流才继续往下执行
 * @author wzy
 *
 */
public class Server {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket ss = new ServerSocket(8888);
		Socket socket = ss.accept();
		InputStream is = socket.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String msg;
		int len;
		byte[] data = new byte[1024*1024*256];
		len = is.read(data);
		System.out.println(new String(data, 0, len));
//		while((len = is.read(data)) != -1) {
//			System.out.println(new String(data, 0, len));
//		}
		System.out.println("-----------read end-------------");
		OutputStream out= socket.getOutputStream();
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		out.write("Got it!".getBytes());
//		bw.write("Got it!");
		Thread.sleep(2000);
		out.write("how do you do?".getBytes());
//		bw.write("how do you do?");
		out.flush();
//		bw.flush();
		out.close();
//		bw.close();
//		br.close();
		System.out.println("---------------end------------------");
	}
}
