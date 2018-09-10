package com.ecjtu.rwx.dao;

import java.util.List;

import com.ecjtu.rwx.bean.Company;
import com.ecjtu.rwx.bean.Flight;

public interface FlightDao {

	/**
	 * 查询所有并且分页  条件查询
	 * @author hht
	 * @param condition   条件
	 * @return
	 */
	List<Flight> findAllFlight(String condition);

	/**
	 * 根据flightid来回显数据
	 * @author hht
	 * @param parseInt
	 * @return
	 */
	Flight findFlightById(int flightid);

	/**
	 * 查询所有的航空公司
	 * @return
	 * @author hht
	 */
	List<Company> findAllCompany(String condition);

	/**
	 * 根据传递过来的对象 添加或者保存  
	 * 如果 id为空那么就是插入
	 * 如果id不为空那么就是保存
	 * @param flight
	 * @return
	 * @author hht
	 */
	boolean saveOrupdate(Flight flight);

	
}
