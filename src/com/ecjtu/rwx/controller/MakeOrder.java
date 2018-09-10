package com.ecjtu.rwx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.service.MakerOrderService;
import com.ecjtu.rwx.service.Impl.MakeOrderServiceImpl;
@WebServlet("/need/MakeOrder")
public class MakeOrder extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if (method != null) {
			MakerOrderService service = new MakeOrderServiceImpl();
			if (method.equals("checkTicket")) {
				service.check_Ticket_Num(request, response);
			} else if (method.equals("addOrder")) {
				service.addOrder(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
