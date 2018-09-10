package com.ecjtu.rwx.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminService {

	void DoLogin(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException ;

}
