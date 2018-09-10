package com.ecjtu.rwx.service.Impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.bean.User;
import com.ecjtu.rwx.dao.UserDao;
import com.ecjtu.rwx.dao.impl.UserDaoImpl;
import com.ecjtu.rwx.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userdao=new UserDaoImpl();
	

	@Override
	public void  checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user = userdao.checkUser(username, password);
		String code=request.getParameter("VerifyCode");
		String sessioncode=(String) request.getSession().getAttribute("session_vcode");
		//验证码正确
		if(sessioncode!=null&&code!=null){
			if(code.equalsIgnoreCase(sessioncode)){
				if(user!=null){
					request.getSession().setAttribute("user", user);
					//登陆成功
					out.print("1");
				}else{
					//登录失败
					out.print("0");
				}
			}else{
				out.print("2");
			}
		}
		
	}


	@Override
	public void exit(HttpServletRequest request, HttpServletResponse response) {
			request.getSession().setAttribute("user", null);
			try {
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	@Override
	public void checkLoginName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String logname=request.getParameter("logname");
		boolean flag=userdao.findByLogname(logname);
		if(flag==true){
			out.print("用户名重复");
		}
		
	}


	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String username=request.getParameter("logname");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		
		boolean flag=userdao.addUser(username,password,phone);
		//注册成功
		if(flag==true){
			out.print("1");
		}else{
			out.print("0");
		}
	}


}
