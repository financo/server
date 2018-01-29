package com.wzy.servlet;

import com.wzy.server.Request;
import com.wzy.server.Response;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) throws Exception {
		
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		response.println("<html><head><title>返回注册</title>");
		response.println("</head><body>");
		response.println("你的用户名为:" + request.getParameter("name"));
		response.println("</body></html>");
	}

}
