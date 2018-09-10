package com.ecjtu.rwx.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ecjtu.rwx.bean.Admin;
import com.ecjtu.rwx.dao.AdminDao;
import com.ecjtu.rwx.dao.BaseDao;

public class AdminDaoImpl extends BaseDao implements AdminDao{

	@Override
	public Admin doLogin(String username, String password) {
		Admin query=null;
		String sql="select * from admin where username=? and password=?";
		try {
			 query = queryRunner.query(conn, sql, new BeanHandler<Admin>(Admin.class), username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}

}
