package com.ecjtu.rwx.service.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ecjtu.rwx.bean.Order;
import com.ecjtu.rwx.bean.Orderitem;
import com.ecjtu.rwx.bean.Passenger;
import com.ecjtu.rwx.bean.User;
import com.ecjtu.rwx.dao.OrderItemDao;
import com.ecjtu.rwx.dao.PassengerDao;
import com.ecjtu.rwx.dao.impl.FlightDaoImpl;
import com.ecjtu.rwx.dao.impl.OrderDaoImpl;
import com.ecjtu.rwx.dao.impl.OrderItemDaoIlmpl;
import com.ecjtu.rwx.dao.impl.PassengerDaoImpl;
import com.ecjtu.rwx.dbutils.DataSourceUtils;
import com.ecjtu.rwx.service.MakerOrderService;
import com.ecjtu.rwx.vo.FlightTicket;

public class MakeOrderServiceImpl implements MakerOrderService {
	private FlightDaoImpl fdao = new FlightDaoImpl();
	private PassengerDao pdao = new PassengerDaoImpl();
	private OrderDaoImpl odao = new OrderDaoImpl();
	private OrderItemDao ordao = new OrderItemDaoIlmpl();

	@Override
	public void check_Ticket_Num(HttpServletRequest request, HttpServletResponse response) {
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		String setType = request.getParameter("setType");
		try {
			if (fdao.getRemainedTicketNum(flightId, setType) > 0) {
				response.getWriter().print(true);
			} else {
				response.getWriter().print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOrder(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		String passengerJson = request.getParameter("passengerJson");
		// int fligthId = Integer.parseInt(request.getParameter("fligthId"));
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		// String purchaserJson = request.getParameter("purchaserJson");
		String insurance = request.getParameter("insurance");
		String purchaserName = request.getParameter("purchaserName");
		String purchaserPhone = request.getParameter("purchaserPhone");
		String totalPrice = request.getParameter("totalPrice");
		List<Passenger> passengers = JSON.parseArray(passengerJson, Passenger.class);

		if (passengers != null) {
			try {
				conn = DataSourceUtils.getConnection();
				// 开始事务
				DataSourceUtils.startTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				// 添加订单（order）
				Order order = new Order();
				order.setUserid(((User) (request.getSession().getAttribute("user"))).getId());
				order.setIspaid("0");
				order.setInsurance(insurance);
				order.setPurchasername(purchaserName);
				order.setPurchaserphone(purchaserPhone);
				order.setTotalprice(Float.parseFloat((totalPrice)));
				order.setTime(String.valueOf(new Date().getTime()));
				FlightTicket flightTicket = (FlightTicket) request.getSession().getAttribute("flightTicket");
				int flightId = flightTicket.getFlight().getId();
				String setType = flightTicket.getAirticket().getSeattype();
				String set = null;
				if (setType.equals("头等舱")) {
					set = "firstclassnum";
				} else if (setType.equals("经济舱")) {
					set = "thirdclassnum";
				} else if (setType.equals("商务舱")) {
					set = "secondclassnum";
				}
				int orderId = odao.addOrder(order, conn);
				// 添加乘客
				for (Passenger passenger : passengers) {
					int passengerId = pdao.addPassenger(passenger, conn);
					// 添加订单细则
					Orderitem orderitem = new Orderitem();
					orderitem.setOrderid(orderId);
					orderitem.setPassengerid(passengerId);
					orderitem.setTicketid(ticketId);
					ordao.addOrderItem(orderitem, conn);
					// 减少票数
					fdao.decreaseTicketNum(flightId, set, conn);
				}
				// 提交事务，释放资源
				DataSourceUtils.commitAndReleased();
				request.getSession().setAttribute("passengers", passengers);
				request.getSession().setAttribute("totalPrice", totalPrice);
				request.getSession().setAttribute("insurance", insurance);
				request.setAttribute("orderId", orderId);
				// 转发页面
				request.getRequestDispatcher("/WEB-INF/payPage.jsp").forward(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
				try {
					// 出现异常，事务回滚
					DataSourceUtils.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
