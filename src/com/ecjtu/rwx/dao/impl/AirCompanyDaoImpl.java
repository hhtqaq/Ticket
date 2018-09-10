package com.ecjtu.rwx.dao.impl;

import java.sql.SQLException;

import com.ecjtu.rwx.dao.AirCompanyDao;
import com.ecjtu.rwx.dao.BaseDao;

public class AirCompanyDaoImpl extends BaseDao implements AirCompanyDao{

	@Override
	public void addCompany(String companyname) {
		String sql="insert into aircompany(name) values(?)";
		try {
			queryRunner.update(conn, sql,companyname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
