package com.ecjtu.rwx.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CustomerService {

	/**
	 * 查询所有的客户信息
	 * @param req
	 * @param resp
	 */
	void findAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	/**
	 * 查询选中用户信息 包括订单信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	void QueryCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	/**
	 *根据订单的id查询出订单详情
	 * @param req
	 * @param resp
	 */
	void ListOrderItems(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException;

}
