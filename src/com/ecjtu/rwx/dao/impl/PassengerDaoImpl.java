package com.ecjtu.rwx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ecjtu.rwx.bean.Passenger;
import com.ecjtu.rwx.dao.PassengerDao;
import com.ecjtu.rwx.dbutils.DataSourceUtils;

public class PassengerDaoImpl implements PassengerDao {

	private QueryRunner runner = new QueryRunner();

	@Override
	public int addPassenger(Passenger passenger, Connection connection) throws SQLException {
		if (passenger == null || connection == null)
			return 0;
		String sql = "insert into passenger(name,idcard,phone,type) values(?,?,?,?)";

		long id = runner.insert(connection, sql, new ScalarHandler<>(), passenger.getName(), passenger.getIdcard(),
				passenger.getPhone(), passenger.getType());
		return (int) id;
	}

	
	public Passenger getPassengerById(int id) {
		Passenger passenger = null;
		String sql = "select * from passenger where id=? ";
		try {
			Connection conn = DataSourceUtils.getConnection();
			passenger = runner.query(conn, sql, new BeanHandler<Passenger>(Passenger.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passenger;
	}

	public void delPassengers(List<Integer> ids, Connection conn) throws SQLException {
		String sql = "delete from passenger where id=?";
		Integer[][] params = new Integer[ids.size()][];
		for (int i = 0; i < ids.size(); i++) {
			params[i] = new Integer[] { ids.get(i) };
		}
		runner.batch(conn, sql, params);
	}
}
