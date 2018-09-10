package com.ecjtu.rwx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ecjtu.rwx.bean.Company;
import com.ecjtu.rwx.bean.Flight;
import com.ecjtu.rwx.dao.FlightDao;
import com.ecjtu.rwx.dbutils.DataSourceUtils;

public class FlightDaoImpl implements FlightDao {
	private QueryRunner runner = new QueryRunner();

	/**
	 * 获取指定的座位类型的数量
	 * 
	 * @param flightId
	 * @param setType
	 * @return
	 * @throws SQLException
	 */
	public int getRemainedTicketNum(int flightId, String setType) {
		int num = 0;
		try {
			Connection conn = DataSourceUtils.getConnection();
			String sql = "select " + setType + " from flight where id=? ";
			num = runner.query(conn, sql, new ScalarHandler<>(), flightId);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<Flight>  findAllFlight(String condition) {
		Connection conn;
		List<Flight> flights=null;
		try {
			conn = DataSourceUtils.getConnection();
			String sql = "SELECT * from flight where startcity LIKE '%" + condition + "%' or endcity LIKE '%"
					+ condition + "%'";
			 flights = runner.query(conn, sql, new BeanListHandler<Flight>(Flight.class));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

	@Override
	public Flight findFlightById(int flightid) {
		Flight flight = null;
		Connection conn;
		try {
			conn = DataSourceUtils.getConnection();
			String sql = "select * from flight where id=?";
			flight = runner.query(conn, sql, new BeanHandler<Flight>(Flight.class), flightid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flight;
	}

	
	@Override
	public List<Company> findAllCompany(String condition) {
		List<Company> companys=null;
		Connection conn;
		try {
			conn=DataSourceUtils.getConnection();
			String sql="select * from aircompany where 1=1 and name like '%"+condition+"%'";
			companys=runner.query(conn, sql, new BeanListHandler<Company>(Company.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return companys;
	}

	@Override
	public boolean saveOrupdate(Flight flight) {
		boolean flag=false;
		String sql="";
		String sql2="";//经济舱
		String sql3="";//商务舱
		String sql4="";//头等舱
		Connection conn=null;
		//如果有id
		//开启事务
		try {
			conn=DataSourceUtils.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
			long update=0;
			if(flight.getId()!=null&&!"".equals(flight.getId())){
				sql="UPDATE flight set aircompany=?,endcity=?,startairport=?,endairport=?,"
							+"starttime=?,endtime=?,startcity=?,firstclassnum=?,secondclassnum=?,"
						+"thirdclassnum=?,airtype=?,airname=? where id=?";
				 update= runner.update(conn, sql, flight.getAircompany(),flight.getEndcity(),flight.getStartairport()
						,flight.getEndairport(),flight.getStarttime(),flight.getEndtime(),flight.getStartcity()
						,flight.getFirstclassnum(),flight.getSecondclassnum(),flight.getThirdclassnum()
						,flight.getAirtype(),flight.getAirname(),flight.getId());
				 sql2="update airticket set price=? where seattype='经济舱' and flightid=?";
				 sql3="update airticket set price=? where seattype='商务舱' and flightid=?";
				 sql4="update airticket set price=? where seattype='头等舱' and flightid=?";
				 runner.update(conn,sql2,flight.getThirdprice(),flight.getId());
				 runner.update(conn,sql3,flight.getSecondprice(),flight.getId());
				 runner.update(conn,sql4,flight.getFirstprice(),flight.getId());
				if(update>0){
					flag=true;
				}
				conn.commit();
				
		}else{
			sql="INSERT INTO `flight` (`aircompany`, `endcity`, `startairport`, "
					+ "`endairport`, `starttime`, `endtime`, `startcity`, `firstclassnum`,"
					+ " `secondclassnum`, `thirdclassnum`, `airtype`, `airname`) "
					+ "VALUES (?, ?, ?, ?, "
					+ "?, ?, ?,"
					+ " ?, ?, ?, ?, ?)";
			update =runner.insert(conn, sql,new ScalarHandler<>(), flight.getAircompany(),flight.getEndcity(),flight.getStartairport()
					,flight.getEndairport(),flight.getStarttime(),flight.getEndtime(),flight.getStartcity()
					,flight.getFirstclassnum(),flight.getSecondclassnum(),flight.getThirdclassnum()
					,flight.getAirtype(),flight.getAirname());
			 sql2="INSERT INTO `airticket` (`flightid`, `price`, `seattype`) VALUES (?, ?, ?)";
			 sql3="INSERT INTO `airticket` (`flightid`, `price`, `seattype`) VALUES (?, ?, ?)";
			 sql4="INSERT INTO `airticket` (`flightid`, `price`, `seattype`) VALUES (?, ?, ?)";
			 runner.update(conn, sql2, update,flight.getFirstprice(),"头等舱");
			 runner.update(conn, sql3, update,flight.getSecondprice(),"商务舱");
			 runner.update(conn, sql4, update,flight.getThirdprice(),"经济舱");
			if(update>0){
				flag=true;
			}
			conn.commit();
		}
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return flag;
	}

	public void decreaseTicketNum(int flightId, String setType, Connection conn) throws SQLException {

		String sql = "update flight set " + setType + "=" + setType + "-1 WHERE id=?";
		runner.update(conn, sql, flightId);
	}

	public void increaseTicketNum(int flightId, String setType, int num, Connection conn) throws SQLException {
		String sql = "update flight set " + setType + "=" + setType + "+" + num + "  WHERE id=?";
		runner.update(conn, sql, flightId);
	}

	public Flight getFlightById(int id) {
		Flight flight = null;
		String sql = "select * from flight where id=?";
		try {
			Connection conn = DataSourceUtils.getConnection();
			flight = runner.query(conn, sql, new BeanHandler<Flight>(Flight.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flight;
	}
}
