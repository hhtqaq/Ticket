package com.ecjtu.rwx.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

	/**
	 * 登录验证
	 * @param response 
	 * @param request 
	 * @return
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public void exit(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 注册的时候判断用户名有没有重复
	 * @param request
	 * @param response
	 */
	public void checkLoginName(HttpServletRequest request, HttpServletResponse response) throws IOException;

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
