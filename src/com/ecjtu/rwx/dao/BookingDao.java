package com.ecjtu.rwx.dao;

import java.util.List;

import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.bean.City;
import com.ecjtu.rwx.bean.Flight;

/**
 * @author Administrator
 *
 */
public interface BookingDao {

	/**
	 * 条件查询
	 * @param startcity    起始城市
	 * @param endcity		终点城市
	 * @param starttime		出发时间
	 * @param aircompany	航空公司
	 * @return  
	 */
	public List<Flight> findAllFlight(String startcity,String endcity,String starttime,String aircompany);

	/**
	 * 查询所有城市列表
	 * @return
	 */
	public List<City> findCity();
	
	/**
	 * 根据航班ID  查询3种票的信息
	 * @param id
	 * @return
	 */
	public List<Airticket> findTicketById(int id);
	/**
	 * 根据机票id 查询机票对象
	 * @param id  ticketid  
	 * @return
	 */
	public Airticket findTicketByTicketId(int id);
	/**
	 * 根据指定机票中的航班的id查询到对应的航班信息  
	 * @param id
	 * @return
	 */
	public Flight findFlightByfid(Integer id);
}
