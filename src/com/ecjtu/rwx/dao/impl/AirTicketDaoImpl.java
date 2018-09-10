package com.ecjtu.rwx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.dbutils.DataSourceUtils;

public class AirTicketDaoImpl {
	private QueryRunner runner = new QueryRunner();

	public Airticket getAirticketById(int id) {
		Airticket airticket = null;
		String sql = "select * from airticket where id=?";
		try {
			Connection conn = DataSourceUtils.getConnection();
			airticket = runner.query(conn, sql, new BeanHandler<Airticket>(Airticket.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return airticket;
	}
}
