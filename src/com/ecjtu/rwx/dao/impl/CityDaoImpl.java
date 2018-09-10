package com.ecjtu.rwx.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ecjtu.rwx.bean.City;
import com.ecjtu.rwx.dao.BaseDao;
import com.ecjtu.rwx.dao.CityDao;

public class CityDaoImpl extends BaseDao implements CityDao{

	@Override
	public List<City> findAllCity(String condition) {
		List<City> citys=null;
		String sql="select * from city where 1=1 and name like '%"+condition+"%'";
		try {
			citys=queryRunner.query(conn, sql, new BeanListHandler<City>(City.class));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return citys;
	}

	@Override
	public void addCity(String name) {
		String sql="insert into city(name) values(?)";
		try {
			queryRunner.update(conn, sql,name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
