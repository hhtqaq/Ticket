package com.ecjtu.rwx.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.ecjtu.rwx.dbutils.DataSourceUtils;

public class BaseDao {
	protected QueryRunner queryRunner = new QueryRunner();
	protected Connection conn;
	public BaseDao() {
		// TODO Auto-generated constructor stub
		try {
			this.conn = DataSourceUtils.getConnection();
		} catch (SQLException e) {
		}
	}
}
