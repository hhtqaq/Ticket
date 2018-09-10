package com.ecjtu.rwx.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookingService {

	/**
	 * 查询所有的航班信息
	 * @param response 
	 * @param request 
	 * @return  返回航班和机票信息
	 */
	public void searchFight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public void findStartCity(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 查询某一种（如经济舱）票对应的航班信息  和机票信息
	 * @param request
	 * @param response
	 */
	public void buyTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	/**
	 * 验证码功能
	 * @param request
	 * @param response
	 */
	public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
