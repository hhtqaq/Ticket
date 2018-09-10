package com.ecjtu.rwx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现政策选择页面(下订单页面)功能的service
 * 
 * @author skty
 *
 */
public interface MakerOrderService {

	/**
	 * 每次添加乘客之前检查所选票种数量是否足够
	 * 
	 * @return
	 */
	void check_Ticket_Num(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 将提交的用户订单添加到总订单(order表)中
	 * 
	 * @return
	 */
	void addOrder(HttpServletRequest request, HttpServletResponse response);
}
