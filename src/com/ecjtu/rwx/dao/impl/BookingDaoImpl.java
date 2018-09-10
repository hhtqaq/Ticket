package com.ecjtu.rwx.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.bean.City;
import com.ecjtu.rwx.bean.Flight;
import com.ecjtu.rwx.dao.BaseDao;
import com.ecjtu.rwx.dao.BookingDao;

public class BookingDaoImpl extends BaseDao implements BookingDao{

	


	@Override
	public List<City> findCity() {
		List<City> cities=null;
		String sql="select * from city";
		try {
			cities=this.queryRunner.query(conn, sql, new BeanListHandler<City>(City.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}


	@Override
	public List<Flight> findAllFlight(String startcity, String endcity, String starttime, String aircompany) {
		StringBuffer sqlbuffer=new StringBuffer();
		List<Flight> query =null;
		sqlbuffer.append("SELECT * from flight where 1=1");
		if(!"".equals(startcity)&&startcity!=null){
			sqlbuffer.append(" and startcity='"+ startcity+"'");
		}
		if(!"".equals(endcity)&&endcity!=null){
			sqlbuffer.append(" and endcity='"+ endcity+"'");
		}
		if(!"".equals(starttime)&&starttime!=null){
			sqlbuffer.append(" and starttime like '%"+ starttime+"%'");
		}
		if(!"".equals(aircompany)&&aircompany!=null){
			sqlbuffer.append(" and aircompany='"+ aircompany+"'");
		}
		try {
			query = queryRunner.query(conn, sqlbuffer.toString(), new BeanListHandler<Flight>(Flight.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}


	@Override
	public List<Airticket> findTicketById(int id) {
		List<Airticket> tickets=null;
		String sql="SELECT * from airticket where flightid=?";
		try {
			tickets = queryRunner.query(conn, sql, new BeanListHandler<Airticket>(Airticket.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickets;
	}


	@Override
	public Airticket findTicketByTicketId(int id) {
		Airticket ticket=null;
		String sql="SELECT * from airticket where id=?";
		try {
			ticket = queryRunner.query(conn, sql, new BeanHandler<Airticket>(Airticket.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticket;
	}


	@Override
	public Flight findFlightByfid(Integer id) {
		Flight flight=null;
		String sql="SELECT * from flight where id=?";
		try {
			flight=queryRunner.query(conn, sql, new BeanHandler<Flight>(Flight.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flight;
	}
	

}
