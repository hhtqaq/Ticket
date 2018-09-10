package com.ecjtu.rwx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.service.UserService;
import com.ecjtu.rwx.service.Impl.UserServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService=new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("dologin".equals(method)){
			userService.checkLogin(request,response);
		}else if("exit".equals(method)){
			userService.exit(request,response);
		}else if("checkLogname".equals(method)){
			userService.checkLoginName(request,response);
		}else if("register".equals(method)){
			
			userService.register(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
