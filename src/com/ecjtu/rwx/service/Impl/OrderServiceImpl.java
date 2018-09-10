package com.ecjtu.rwx.service.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ecjtu.rwx.bean.Passenger;
import com.ecjtu.rwx.bean.QueryCase;
import com.ecjtu.rwx.bean.User;
import com.ecjtu.rwx.dao.impl.AllMessageDaoImpl;
import com.ecjtu.rwx.dao.impl.FlightDaoImpl;
import com.ecjtu.rwx.dao.impl.OrderDaoImpl;
import com.ecjtu.rwx.dao.impl.OrderItemDaoIlmpl;
import com.ecjtu.rwx.dao.impl.PassengerDaoImpl;
import com.ecjtu.rwx.dbutils.DataSourceUtils;
import com.ecjtu.rwx.vo.FlightTicket;
import com.ecjtu.rwx.vo.OrderItemVo;
import com.ecjtu.rwx.vo.OrderVo;

public class OrderServiceImpl {

	private AllMessageDaoImpl daoImpl = new AllMessageDaoImpl();
	private OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
	private OrderItemDaoIlmpl itemDaoIlmpl = new OrderItemDaoIlmpl();
	private PassengerDaoImpl passengerDaoImpl = new PassengerDaoImpl();
	private AllMessageDaoImpl allMessageDaoImpl = new AllMessageDaoImpl();
	private FlightDaoImpl flightDaoImpl = new FlightDaoImpl();

	public void showOrders(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {

			QueryCase cases = new QueryCase();
			cases.setUserId(user.getId().toString());
			try {
				List<OrderVo> orderVos = daoImpl.getAllMessage(orderDaoImpl.getOrderIdByCase(cases), 0, -1);
				request.setAttribute("orderVos", orderVos);
				String json = JSON.toJSONString(orderVos);
				System.out.println(json);
				request.getRequestDispatcher("/WEB-INF/showOrder.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void showPage(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			QueryCase cases = new QueryCase();
			cases.setUserId(user.getId().toString());
			cases.setOrder_startTime(
					request.getParameter("order_startTime") == "" ? null : request.getParameter("order_startTime"));
			cases.setOrder_endTime(
					request.getParameter("order_endTime") == "" ? null : request.getParameter("order_endTime"));
			cases.setOrder_ispaid(
					request.getParameter("order_ispaid") == "" ? null : request.getParameter("order_ispaid"));
			cases.setOrder_purchasername(request.getParameter("order_purchasername") == "" ? null
					: request.getParameter("order_purchasername"));
			cases.setFlight_startCity(
					request.getParameter("flight_startCity") == "" ? null : request.getParameter("flight_startCity"));
			cases.setFlight_endCity(
					request.getParameter("flight_endCity") == "" ? null : request.getParameter("flight_endCity"));
			cases.setFlight_startTime(
					request.getParameter("flight_startTime") == "" ? null : request.getParameter("flight_startTime"));
			cases.setFlight_endTime(
					request.getParameter("flight_endTime") == "" ? null : request.getParameter("flight_endTime"));
			try {
				List<OrderVo> orderVos = daoImpl.getAllMessage(orderDaoImpl.getOrderIdByCase(cases), 0, -1);
				request.setAttribute("orderVos", orderVos);
				request.setAttribute("cases", cases);
				// 跳到订单页面
				request.getRequestDispatcher("/WEB-INF/showOrder.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void showDetail(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		if (orderId != null) {
			OrderVo OrderVo = daoImpl.getAllMessageByOrderId(Integer.parseInt(orderId));
			request.setAttribute("orderVo", OrderVo);
			try {
				// 跳转到订单详情页面
				request.getRequestDispatcher("/WEB-INF/orderDetail.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void gotoPay(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		if (orderId != null) {
			OrderVo orderVos = allMessageDaoImpl.getAllMessageByOrderId(Integer.parseInt(orderId));
			FlightTicket flightTicket = new FlightTicket();
			flightTicket.setAirticket(orderVos.getOrderitemVos().get(0).getAirticketVo().getAirticket());
			flightTicket.setFlight(orderVos.getOrderitemVos().get(0).getAirticketVo().getFlight());
			List<Passenger> passengers = new ArrayList<Passenger>();
			for (OrderItemVo vo : orderVos.getOrderitemVos()) {
				passengers.add(vo.getPassenger());
			}
			request.setAttribute("flightTicket", flightTicket);
			request.setAttribute("passengers", passengers);
			request.setAttribute("totalPrice", orderVos.getOrder().getTotalprice());
			request.setAttribute("insurance", orderVos.getOrder().getInsurance());
			request.setAttribute("orderId", orderVos.getOrder().getId());
			try {
				// 转发到支付页面
				request.getRequestDispatcher("/WEB-INF/payPage.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		if (orderId != null) {
			OrderVo orderVos = allMessageDaoImpl.getAllMessageByOrderId(Integer.parseInt(orderId));
			List<OrderItemVo> orderitemVos = orderVos.getOrderitemVos();
			List<Integer> passengerIds = new ArrayList<Integer>();
			List<Integer> orderItemIds = new ArrayList<Integer>();

			String setType = orderitemVos.get(0).getAirticketVo().getAirticket().getSeattype();
			String set = null;
			if (setType.equals("头等舱")) {
				set = "firstclassnum";
			} else if (setType.equals("经济舱")) {
				set = "thirdclassnum";
			} else if (setType.equals("商务舱")) {
				set = "secondclassnum";
			}
			int flightId = orderitemVos.get(0).getAirticketVo().getAirticket().getFlightid();

			for (OrderItemVo orderitemVo : orderitemVos) {
				passengerIds.add(orderitemVo.getOrderitem().getPassengerid());
				orderItemIds.add(orderitemVo.getOrderitem().getId());
			}

			Connection conn = null;
			try {
				conn = DataSourceUtils.getConnection();
				// 开启事务
				DataSourceUtils.startTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				// 删除orderItem表中的数据
				itemDaoIlmpl.delOrderItems(orderItemIds, conn);

				// 删除passenger表中的数据
				passengerDaoImpl.delPassengers(passengerIds, conn);

				// 删除order表中的数据
				orderDaoImpl.delOrderById(Integer.parseInt(orderId), conn);

				// 将机票数量归还
				flightDaoImpl.increaseTicketNum(flightId, set, passengerIds.size(), conn);

				// 提交事务
				DataSourceUtils.commitAndReleased();
				response.getWriter().print(true);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				try {
					// 事务失败，回滚事务
					DataSourceUtils.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		try {
			// 删除失败，返回false
			response.getWriter().print(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
