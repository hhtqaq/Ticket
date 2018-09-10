package com.ecjtu.rwx.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ecjtu.rwx.bean.Airport;
import com.ecjtu.rwx.dao.AirportDao;
import com.ecjtu.rwx.dao.BaseDao;

public class AirportDaoImpl extends BaseDao implements AirportDao{

	@Override
	public List<Airport> findAllAirport() {
		List<Airport> airports=null;
		String sql="select * from airport";
		try {
			airports = queryRunner.query(conn, sql, new BeanListHandler<Airport>(Airport.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return airports;
	}

}
