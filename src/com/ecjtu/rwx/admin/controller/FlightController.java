package com.ecjtu.rwx.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.admin.service.FlightService;
import com.ecjtu.rwx.admin.service.impl.FlightServiceImpl;

@WebServlet("/admin/flight")
public class FlightController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FlightService flightService=new FlightServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String method=req.getParameter("method");
			if("listFlight".equals(method)){
				flightService.listAllFlight(req,resp);
			}else if("addOrEditFlightUI".equals(method)){
				flightService.AddOrEditFlightUI(req,resp);
			}else if("findAllCompany".equals(method)){
				flightService.FindAllCompany(req,resp);
			}else if("findAllAirport".equals(method)){
				flightService.findAllAirport(req,resp);
			}else if("addOrupdateFlight".equals(method)){
				flightService.AddOrEditFlight(req, resp);
			}else if("listCity".equals(method)){
				flightService.findAllCity(req,resp);
			}else if("listAirCompany".equals(method)){
				flightService.ListAirCompany(req,resp);
			}else if("addCity".equals(method)){
				flightService.AddCity(req,resp);
			}else if("addCompany".equals(method)){
				flightService.AddCompany(req,resp);
			}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
