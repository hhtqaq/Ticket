package com.ecjtu.rwx.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ecjtu.rwx.bean.Order;
import com.ecjtu.rwx.bean.Orderitem;
import com.ecjtu.rwx.bean.QueryCase;
import com.ecjtu.rwx.vo.AirTicketVo;
import com.ecjtu.rwx.vo.OrderItemVo;
import com.ecjtu.rwx.vo.OrderVo;

public class AllMessageDaoImpl {
	private OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
	private OrderItemDaoIlmpl orderItemDaoIlmpl = new OrderItemDaoIlmpl();
	private AirTicketDaoImpl airTicketDaoImpl = new AirTicketDaoImpl();
	private FlightDaoImpl flightDaoImpl = new FlightDaoImpl();
	private PassengerDaoImpl passengerDaoImpl = new PassengerDaoImpl();

	/**
	 * 查询结合的订单信息
	 * 
	 * @param orderIds
	 *            查询到的orderid的集合
	 * @return
	 */
	public List<OrderVo> getAllMessage(List<Integer> orderIds, int start, int num) {
		List<OrderVo> orderVos = new ArrayList<OrderVo>();
		int end = start + num;
		if (num == -1 || (start + num) > orderIds.size()) {
			end = orderIds.size();
		}
		for (int i = start; i < end; i++) {
			int id = orderIds.get(i);
			OrderVo orderVo = new OrderVo();
			Order order = orderDaoImpl.getOrderById(id);
			orderVo.setOrder(order);
			// 生成orderItemVos列表
			List<OrderItemVo> orderItemVos = new ArrayList<OrderItemVo>();
			for (Orderitem orderitem : orderItemDaoIlmpl.getOrderitemsByOrderId(id)) {
				// 生成orderItemVo
				OrderItemVo orderItemVo = new OrderItemVo();
				orderItemVo.setOrderitem(orderitem);

				// 向orderItemVo中加入airTicketVo
				AirTicketVo airTicketVo = new AirTicketVo();
				airTicketVo.setAirticket(airTicketDaoImpl.getAirticketById(orderitem.getTicketid()));
				airTicketVo.setFlight(flightDaoImpl
						.getFlightById(airTicketDaoImpl.getAirticketById(orderitem.getTicketid()).getFlightid()));
				orderItemVo.setAirticketVo(airTicketVo);

				// 向orderItemVo中加入passenger
				orderItemVo.setPassenger(passengerDaoImpl.getPassengerById(orderitem.getPassengerid()));

				// 将orderItemVo加入列表
				orderItemVos.add(orderItemVo);
			}

			// 将orderItemVo列表加入orderVo中
			orderVo.setOrderitemVos(orderItemVos);

			orderVos.add(orderVo);
		}
		return orderVos;
	}

	/**
	 * 根据订单的id查询相关信息
	 * 
	 * @param id
	 * @return
	 */
	public OrderVo getAllMessageByOrderId(int id) {
		OrderVo orderVo = new OrderVo();
		orderVo.setOrder(orderDaoImpl.getOrderById(id));
		List<OrderItemVo> orderItemVos = new ArrayList<OrderItemVo>();
		for (Orderitem orderitem : orderItemDaoIlmpl.getOrderitemsByOrderId(id)) {
			OrderItemVo orderItemVo = new OrderItemVo();
			orderItemVo.setOrderitem(orderitem);

			// 向orderItemVo中加入airTicketVo
			AirTicketVo airTicketVo = new AirTicketVo();
			airTicketVo.setAirticket(airTicketDaoImpl.getAirticketById(orderitem.getTicketid()));
			airTicketVo.setFlight(flightDaoImpl
					.getFlightById(airTicketDaoImpl.getAirticketById(orderitem.getTicketid()).getFlightid()));
			orderItemVo.setAirticketVo(airTicketVo);

			// 向orderItemVo中加入passenger
			orderItemVo.setPassenger(passengerDaoImpl.getPassengerById(orderitem.getPassengerid()));

			// 将orderItemVo加入列表
			orderItemVos.add(orderItemVo);
		}
		orderVo.setOrderitemVos(orderItemVos);
		return orderVo;
	}

	public static void main(String[] args) {
		AllMessageDaoImpl i = new AllMessageDaoImpl();
		QueryCase cases = new QueryCase();
		cases.setFlight_startTime("2018-7-25 12:15");
		cases.setFlight_endTime("2018-7-25 20:15");
		// cases.setOrder_purchasername("史永龙");
		System.out.println(i.getAllMessageByOrderId(1).getOrder().getPurchasername());
	}
}
