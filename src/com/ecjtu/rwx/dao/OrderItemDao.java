package com.ecjtu.rwx.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ecjtu.rwx.bean.Orderitem;
import com.ecjtu.rwx.vo.OrderItemWithOther;

/**
 * 操作orderItem表的dao
 * 
 * @author skty
 *
 */
public interface OrderItemDao {
	/**
	 * 新增订单细则
	 * 
	 * @param orderitem
	 *            订单细则对象
	 * @param connection
	 *            数据库链接对象（用于事务控制）
	 * @return
	 * @throws SQLException
	 */
	boolean addOrderItem(Orderitem orderitem, Connection connection) throws SQLException;

	/**
	 * 根据订单id查询订单详情
	 * @param orderid
	 * @return
	 * @author hht
	 */
	List<OrderItemWithOther> findItemById(int orderid);
}
