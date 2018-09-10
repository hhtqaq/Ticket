package com.ecjtu.rwx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.service.Impl.PayServiceImpl;

@WebServlet("/need/PayController")
public class PayController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PayServiceImpl payServiceImpl = new PayServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payMethod = request.getParameter("payMethod");
		if (payMethod != null) {
			if (payMethod.equals("online")) {
				payServiceImpl.onlinePay(request, response);
			} else if (payMethod.equals("bank")) {
				payServiceImpl.bankPay(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
