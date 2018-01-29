package com.wzy.servlet;

import com.wzy.server.Request;
import com.wzy.server.Response;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) throws Exception {
		String username = request.getParameter("name");
		String passwd = request.getParameter("password");
		if (login(username, passwd)) {
			response.println("登陸成功");
		}else {
			response.println("登陸失敗");
		}
		response.println("<html><head><title>欢迎回来</title>");
		response.println("</head><body>");
		response.println("welcome:").println(request.getParameter("name")).println(" come back!");
		response.println("</body></html>");
	}
	
	public boolean login(String username, String passwd) {
		return "wzy".equals(username) && "123".equals(passwd);
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		
	}

}
