package com.ecjtu.rwx.service.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.dao.impl.OrderDaoImpl;

public class PayServiceImpl {

	private OrderDaoImpl daoImpl = new OrderDaoImpl();

	public void onlinePay(HttpServletRequest request, HttpServletResponse response) {
		doPay(request, response);
	}

	public void bankPay(HttpServletRequest request, HttpServletResponse response) {
		doPay(request, response);
	}

	public void doPay(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		if (orderId != null) {
			daoImpl.changePayStatus(Integer.parseInt(orderId), "1");
			try {
				request.getRequestDispatcher("/WEB-INF/paySuccess.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		}
	}

}
