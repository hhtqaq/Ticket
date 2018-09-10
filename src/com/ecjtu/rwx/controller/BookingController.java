package com.ecjtu.rwx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.service.BookingService;
import com.ecjtu.rwx.service.Impl.BookingServiceImpl;

@WebServlet("/booking")
public class BookingController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookingService bookservice = new BookingServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		if ("searchFight".equals(method)) {
			bookservice.searchFight(request, response);
		} else if ("findstartcity".equals(method)) {
			bookservice.findStartCity(request, response);
		} else if ("buyTicket".equals(method)) {
			bookservice.buyTicket(request,response);
		}else if("VerifyCode".equals(method)){
			bookservice.verifyCode(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
