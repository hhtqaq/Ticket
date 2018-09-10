package com.ecjtu.rwx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ecjtu.rwx.bean.Order;
import com.ecjtu.rwx.bean.QueryCase;
import com.ecjtu.rwx.dbutils.DataSourceUtils;

public class OrderDaoImpl {
	private QueryRunner runner = new QueryRunner();

	public int addOrder(Order order, Connection connection) throws SQLException {
		if (order == null || connection == null)
			return 0;
		String sql = "INSERT INTO orders ( userid,purchasername, purchaserphone, totalprice, insurance, time,ispaid) values (?,?,?,?,?,?,?)";

		long id = runner.insert(connection, sql, new ScalarHandler<>(), order.getUserid(), order.getPurchasername(),
				order.getPurchaserphone(), order.getTotalprice(), order.getInsurance(), order.getTime(),
				order.getIspaid());
		return (int) id;
	}

	public Order getOrderById(int id) {
		Order orders = null;
		String sql = "select * from orders where id=?";
		try {
			Connection conn = DataSourceUtils.getConnection();
			orders = runner.query(conn, sql, new BeanHandler<Order>(Order.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public void delOrderById(int id, Connection conn) throws SQLException {
		String sql = "delete from orders where id=?";
		runner.update(conn, sql, id);
	}

	public void changePayStatus(int id, String status) {
		String sql = "update orders set ispaid =? where id=?";
		try {
			Connection conn = DataSourceUtils.getConnection();
			runner.update(conn, sql, status, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private String getOrderIdByFlightStartEnd_time(String startTime, String endTime) {
		if (startTime == null && endTime == null) {
			return null;
		}
		String sql = null;

		if (startTime != null && endTime != null) {
			sql = "SELECT orders.id from orders WHERE orders.id IN ( SELECT orderitem.orderid from orderitem WHERE  orderitem.ticketid in( SELECT airticket.id FROM airticket WHERE  airticket.flightid in( SELECT flight.id FROM flight WHERE flight.starttime   >= str_to_date( ?,'%Y-%m-%d %H:%i:%s') AND flight.starttime  <= str_to_date(?,'%Y-%m-%d %H:%i:%s')  )  ))";

		}
		if (startTime == null) {
			sql = "SELECT orders.id from orders WHERE orders.id IN ( SELECT orderitem.orderid from orderitem WHERE  orderitem.ticketid in( SELECT airticket.id FROM airticket WHERE  airticket.flightid in( SELECT flight.id FROM flight WHERE  flight.starttime  <= str_to_date( ?,'%Y-%m-%d %H:%i:%s')  )  ))";
		}
		if (endTime == null) {
			sql = "SELECT orders.id from orders WHERE orders.id IN ( SELECT orderitem.orderid from orderitem WHERE  orderitem.ticketid in( SELECT airticket.id FROM airticket WHERE  airticket.flightid in( SELECT flight.id FROM flight WHERE  flight.starttime  >= str_to_date( ?,'%Y-%m-%d %H:%i:%s')  )  ))";
		}
		return sql;
	}

	private String getOrderIdByOrderTime(String startTime, String endTime) {
		if (startTime == null && endTime == null) {
			return null;
		}
		String sql = null;

		if (startTime != null && endTime != null) {
			sql = "SELECT orders.id from orders WHERE orders.time >= ? AND orders.time <= ? ";
		}
		if (startTime == null) {
			sql = "SELECT orders.id from orders WHERE  orders.time <= ?";
		}
		if (endTime == null) {
			sql = "SELECT orders.id from orders WHERE orders.time >= ?";
		}
		return sql;
	}

	private String getOrderIdByFlightStartEnd_city(String startCity, String endCity) {
		if (startCity == null && endCity == null) {
			return null;
		}
		String sql = null;

		if (startCity != null && endCity != null) {
			sql = "SELECT orders.id FROM orders WHERE 	orders.id IN (SELECT orderitem.orderid FROM orderitem WHERE orderitem.ticketid IN (SELECT airticket.id 	FROM airticket WHERE airticket.flightid IN (SELECT flight.id FROM flight WHERE flight.startcity = ? AND flight.endcity = ?)))";
		}
		if (startCity == null) {
			sql = "SELECT orders.id FROM orders WHERE 	orders.id IN (SELECT orderitem.orderid FROM orderitem WHERE orderitem.ticketid IN (SELECT airticket.id 	FROM airticket WHERE airticket.flightid IN (SELECT flight.id FROM flight WHERE  flight.endcity = ? )))";
		}
		if (endCity == null) {
			sql = "SELECT orders.id FROM orders WHERE 	orders.id IN (SELECT orderitem.orderid FROM orderitem WHERE orderitem.ticketid IN (SELECT airticket.id 	FROM airticket WHERE airticket.flightid IN (SELECT flight.id FROM flight WHERE flight.startcity = ?)))";
		}
		return sql;

	}

	private String getOrderIdByPurchasername(String name) {

		if (name == null) {
			return null;
		}
		return "SELECT orders.id from orders WHERE orders.purchasername=?";
	}

	private String getOrderIdByIspaid(String isPaid) {
		if (isPaid == null) {
			return null;
		}
		return "SELECT orders.id from orders WHERE orders.ispaid =?";
	}

	private List<String> setCases(QueryCase cases) {

		List<String> c = new ArrayList<String>();
		if (cases.getUserId() != null) {
			c.add(cases.getUserId());
		}
		if (cases.getOrder_startTime() != null) {
			c.add(cases.getOrder_startTime());
		}
		if (cases.getOrder_endTime() != null) {
			c.add(cases.getOrder_endTime());
		}
		if (cases.getFlight_startTime() != null) {
			c.add(cases.getFlight_startTime());
		}
		if (cases.getFlight_endTime() != null) {
			c.add(cases.getFlight_endTime());
		}
		if (cases.getFlight_startCity() != null) {
			c.add(cases.getFlight_startCity());
		}
		if (cases.getFlight_endCity() != null) {
			c.add(cases.getFlight_endCity());
		}
		if (cases.getOrder_purchasername() != null) {
			c.add(cases.getOrder_purchasername());
		}
		if (cases.getOrder_ispaid() != null) {
			c.add(cases.getOrder_ispaid());
		}
		return c;
	}

	public List<Integer> getOrderIdByCase(QueryCase cases) throws SQLException {
		List<Integer> orderIds = null;
		Connection conn = DataSourceUtils.getConnection();
		String[] sqls = new String[5];
		sqls[0] = getOrderIdByOrderTime(cases.getOrder_startTime(), cases.getOrder_endTime());
		sqls[1] = getOrderIdByFlightStartEnd_time(cases.getFlight_startTime(), cases.getFlight_endTime());
		sqls[2] = getOrderIdByFlightStartEnd_city(cases.getFlight_startCity(), cases.getFlight_endCity());
		sqls[3] = getOrderIdByPurchasername(cases.getOrder_purchasername());
		sqls[4] = getOrderIdByIspaid(cases.getOrder_ispaid());
		StringBuilder sql = new StringBuilder();
		if (cases.getUserId() == null) {
			sql.append("SELECT orders.id FROM orders WHERE 1=1 ");
		} else {
			sql.append("SELECT orders.id FROM orders WHERE orders.userid = ? ");
		}
		for (String string : sqls) {
			if (string != null) {
				sql.append("AND orders.id IN ( ").append(string).append(" )");
			}
		}

		orderIds = runner.query(conn, sql.toString(), new ColumnListHandler<Integer>(), setCases(cases).toArray());
		return orderIds;
	}

	public static void main(String[] args) throws SQLException {
		OrderDaoImpl i = new OrderDaoImpl();
		QueryCase cases = new QueryCase();
		// cases.setOrder_startTime("1532678554678");
		// cases.setOrder_endTime("1532680738423");
		// cases.setFlight_startCity("深圳");
		// cases.setFlight_endCity("上海");
		// cases.setFlight_startTime("2018-7-25 12:15");
		// cases.setFlight_endTime("2018-7-25 20:15");
		// cases.setOrder_ispaid("0");
		// cases.setOrder_purchasername("史永龙");
		// i.setCases(cases);
		// cases.setUserId("1");

		System.out.println(i.getOrderIdByCase(cases));

	}
}
