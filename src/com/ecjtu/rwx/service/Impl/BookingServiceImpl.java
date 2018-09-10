package com.ecjtu.rwx.service.Impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.bean.City;
import com.ecjtu.rwx.bean.Flight;
import com.ecjtu.rwx.dao.BookingDao;
import com.ecjtu.rwx.dao.impl.BookingDaoImpl;
import com.ecjtu.rwx.dbutils.BlankUtils;
import com.ecjtu.rwx.dbutils.Dateutil;
import com.ecjtu.rwx.dbutils.PageBean;
import com.ecjtu.rwx.service.BookingService;
import com.ecjtu.rwx.vo.FlightTicket;
import com.google.gson.Gson;

import cn.itcast.vcode.utils.VerifyCode;

public class BookingServiceImpl implements BookingService {

	private BookingDao bookingdao = new BookingDaoImpl();

	
	@Override
	public void searchFight(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String startcity = request.getParameter("startcity");
		String endcity = request.getParameter("endcity");
		String starttime = request.getParameter("starttime");
		String aircompany = request.getParameter("aircompany");
		String page=request.getParameter("currentPage");
		int currentPage=1;
		if(!"".equals(page)&&page!=null){
			currentPage=Integer.parseInt(page);
		}
		List<Flight> findAll = bookingdao.findAllFlight(startcity, endcity, starttime, aircompany);
		
		List<FlightTicket> flightTickets=new ArrayList<FlightTicket>();
		//设置查询数据
		if (findAll != null) {
			for (Flight flight : findAll) {
				FlightTicket flightTicket=new FlightTicket();
				String[] split = flight.getStarttime().split(" ");
				flight.setStarttime(split[1]);
				String[] split2 = flight.getEndtime().split(" ");
				flight.setEndtime(split2[1]);
				int id = flight.getId();
				List<Airticket> tickets = bookingdao.findTicketById(id);
				for (Airticket airticket : tickets) {
					if(("头等舱").equals(airticket.getSeattype())){
						airticket.setSeatnum(flight.getFirstclassnum());
					}else if("商务舱".equals(airticket.getSeattype())){
						airticket.setSeatnum(flight.getSecondclassnum());
					}else{
						airticket.setSeatnum(flight.getThirdclassnum());
					}
				}
				flightTicket.setFlight(flight);
				flightTicket.setAirtickets(tickets);
				flightTickets.add(flightTicket);
			}
		}
		if (flightTickets.size()==0) {
			request.setAttribute("msg", "暂无符合条件的机票信息，请重新搜索");
		}
		//分页
		PageBean<FlightTicket> bean=new PageBean<FlightTicket>(currentPage,2,flightTickets.size());
		bean.setData(flightTickets);
		request.setAttribute("findAll", bean);
		request.getRequestDispatcher("/listflight.jsp").forward(request, response);
	}

	/**
	 * ajax请求返回json对象
	 */
	@Override
	public void findStartCity(HttpServletRequest request, HttpServletResponse response) {

		List<City> cities = bookingdao.findCity();
		Gson gson = new Gson();
		// 转为json格式
		String json = gson.toJson(cities);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void buyTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightTicket flightTicket=new FlightTicket();
		String ticketid=request.getParameter("ticketid");
		if(!BlankUtils.isblank(ticketid)){
			//查询被选定的机票信息
			Airticket ticket=bookingdao.findTicketByTicketId(Integer.parseInt(ticketid));
			//根据被选定的机票信息查询航班信息
			Flight flight=bookingdao.findFlightByfid(ticket.getFlightid());
			//
			flight.setStarttime(Dateutil.changeDate(flight.getStarttime()));
			flight.setEndtime(Dateutil.changeDate(flight.getEndtime()));
			flightTicket.setAirticket(ticket);
			flightTicket.setFlight(flight);
			request.getSession().setAttribute("flightTicket", flightTicket);
		}
		request.getRequestDispatcher("/WEB-INF/makeOrder.jsp").forward(request, response);
	}

	@Override
	public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VerifyCode vc= new VerifyCode();
        BufferedImage image = vc.getImage();
        request.getSession().setAttribute("session_vcode", vc.getText());
			VerifyCode.output(image, response.getOutputStream());
		
	}
	
	
}
