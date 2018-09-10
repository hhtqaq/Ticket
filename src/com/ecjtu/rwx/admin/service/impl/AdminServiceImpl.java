package com.ecjtu.rwx.admin.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.admin.service.AdminService;
import com.ecjtu.rwx.bean.Admin;
import com.ecjtu.rwx.dao.AdminDao;
import com.ecjtu.rwx.dao.impl.AdminDaoImpl;

public class AdminServiceImpl implements AdminService{

	protected AdminDao adminDao=new AdminDaoImpl();
	@Override
	public void DoLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password=req.getParameter("password");
		Admin admin=adminDao.doLogin(username,password);
		//登陆成功
		if(admin!=null){
			req.getSession().setAttribute("admin", admin);
			req.getRequestDispatcher("/WEB-INF/adminpages/frame.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "用户名和密码不一致");
			req.getRequestDispatcher("/WEB-INF/adminpages/login.jsp").forward(req, resp);
		}
	}

	
}
