package com.ecjtu.rwx.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.admin.service.CustomerService;
import com.ecjtu.rwx.admin.service.impl.CustomerServiceImpl;

@WebServlet("/admin/customer")
public class CustomerController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerservice = new CustomerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method=req.getParameter("method");
		if("listCustomer".equals(method)){
			customerservice.findAllCustomer(req,resp);
		}else if("queryCustomer".equals(method)){
				customerservice.QueryCustomer(req,resp);
		}else if("listOrderItems".equals(method)){
			customerservice.ListOrderItems(req,resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
