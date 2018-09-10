package com.ecjtu.rwx.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.ecjtu.rwx.bean.Passenger;

/**
 * 负责乘客表的各种数据库操作
 * 
 * @author skty
 *
 */
public interface PassengerDao {

	/**
	 * 新增乘客
	 * 
	 * @param passenger
	 *            乘客对象
	 * @param connection
	 *            数据库连接对象,用于事务控制
	 * @return
	 */
	int addPassenger(Passenger passenger, Connection connection) throws SQLException;

}
