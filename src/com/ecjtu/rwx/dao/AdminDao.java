package com.ecjtu.rwx.dao;

import com.ecjtu.rwx.bean.Admin;

public interface AdminDao {

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	Admin doLogin(String username, String password);

}
