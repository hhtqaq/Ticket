package com.ecjtu.rwx.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FlightService {

	/**查询所有的航班
	 * @author hht
	 * @param resp 
	 * @param req 
	 */
	void listAllFlight(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;

	/**
	 * 根据是否传递航班的id 来指定添加还是修改
	 * @author hht
	 * @param req
	 * @param resp
	 */
	void AddOrEditFlight(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	/**
	 * 添加或者编辑前的操作
	 * @author hht
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	void AddOrEditFlightUI(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;

	/**
	 * ajax查询所有的航空公司  
	 * 转换为json格式
	 * 显示到下拉框
	 * @author hht
	 * @param req
	 * @param resp
	 */
	void FindAllCompany(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * ajax查询所有的机场
	 * 转换为json格式
	 * 显示到下拉框
	 * @param req
	 * @param resp
	 */
	void findAllAirport(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 查询所有的城市信息
	 * @param req
	 * @param resp
	 * @author hht
	 */
	void findAllCity(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 查询所有的航空公司
	 * @param req
	 * @param resp
	 */
	void ListAirCompany(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 添加城市
	 * @param req
	 * @param resp
	 */
	void AddCity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	/**
	 * 添加航空公司
	 * @param req
	 * @param resp
	 */
	void AddCompany(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;


	
	
}
