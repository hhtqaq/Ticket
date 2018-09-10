package com.ecjtu.rwx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.service.Impl.OrderServiceImpl;

@WebServlet("/need/OrderController")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OrderServiceImpl serviceImpl = new OrderServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");
		if (method != null) {
			if (method.equals("showOrders")) {
				serviceImpl.showOrders(request, response);
			} else if (method.equals("showPage")) {
				serviceImpl.showPage(request, response);
			} else if (method.equals("showDetail")) {
				serviceImpl.showDetail(request, response);
			} else if (method.equals("deleteOrder")) {
				serviceImpl.deleteOrder(request, response);
			} else if (method.equals("gotoPay")) {
				serviceImpl.gotoPay(request, response);
			}
		} else {
			serviceImpl.showOrders(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
