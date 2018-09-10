package com.ecjtu.rwx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ecjtu.rwx.bean.Orderitem;
import com.ecjtu.rwx.dao.OrderItemDao;
import com.ecjtu.rwx.dbutils.DataSourceUtils;
import com.ecjtu.rwx.vo.OrderItemWithOther;

public class OrderItemDaoIlmpl implements OrderItemDao {
	private QueryRunner runner = new QueryRunner();

	@Override
	public boolean addOrderItem(Orderitem orderitem, Connection connection) throws SQLException {
		if (orderitem == null || connection == null)
			return false;
		String sql = "insert into orderitem(passengerid,ticketid,orderid) values(?,?,?)";
		if (runner.update(connection, sql, orderitem.getPassengerid(), orderitem.getTicketid(),
				orderitem.getOrderid()) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<OrderItemWithOther> findItemById(int orderid) {
		Connection connection;
		List<OrderItemWithOther> orderItems=null;
		try {
			connection = DataSourceUtils.getConnection();
			String sql="SELECT p.`name`,p.idcard,p.type,a.price,a.seattype,f.aircompany,f.startcity,f.endcity,f.starttime,"
					+ "f.endtime,f.airname,f.airtype,f.startairport,f.endairport"
					+" from orderitem o INNER JOIN passenger p ON o.passengerid=p.id INNER JOIN airticket a on a.id=o.ticketid"
					+" INNER JOIN flight f on f.id=a.flightid where orderid =?";
			orderItems = runner.query(connection, sql, new BeanListHandler<OrderItemWithOther>(OrderItemWithOther.class), orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}
	
	
	
	
	public List<Orderitem> getOrderitemsByOrderId(int orderId) {
		List<Orderitem> orderitems = null;
		String sql = "select * from orderitem where orderid=?";
		try {
			Connection conn = DataSourceUtils.getConnection();
			orderitems = runner.query(conn, sql, new BeanListHandler<Orderitem>(Orderitem.class), orderId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderitems;
	}

	public void delOrderItems(List<Integer> ids, Connection conn) throws SQLException {
		String sql = "delete from orderitem where id=?";
		Integer[][] params = new Integer[ids.size()][];
		for (int i = 0; i < ids.size(); i++) {
			params[i] = new Integer[] { ids.get(i) };
		}
		runner.batch(conn, sql, params);
	}


}
