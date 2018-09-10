package com.ecjtu.rwx.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ecjtu.rwx.bean.Order;
import com.ecjtu.rwx.bean.User;
import com.ecjtu.rwx.dao.BaseDao;
import com.ecjtu.rwx.dao.UserDao;
import com.ecjtu.rwx.dbutils.PageBean;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User checkUser(String username, String password) {
		User user = null;
		String sql = "select * from user where username=? and password=?";
		try {
			user = super.queryRunner.query(conn, sql, new BeanHandler<User>(User.class), username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean findByLogname(String logname) {
		boolean flag = false;
		String sql = "select * from user where username=?";
		try {
			User query = queryRunner.query(conn, sql, new BeanHandler<User>(User.class), logname);
			if (query != null) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addUser(String username, String password, String phone) {
		boolean flag = false;
		String sql = "INSERT INTO `user` (`username`, `password`,`phone`) VALUES (?,?,?)";
		try {
			int update = queryRunner.update(conn, sql, username, password, phone);
			if (update > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public PageBean<User> findAll(String condiction, int currentPage, int pagesize) {
		List<User> users = null;
		PageBean<User> pagebean = null;
		if (condiction == null) {
			condiction = "";
		}
		String sql="select * from user where username LIKE '%" + condiction + "%' or phone LIKE '%" + condiction + "%'";
		try {
			users = queryRunner.query(conn, sql, new BeanListHandler<User>(User.class));
			pagebean = new PageBean<User>(currentPage, pagesize, users.size());
			pagebean.setData(users);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagebean;
	}

	@Override
	public List<Order> findById(int id) {
		List<Order> orders=null;
		String sql="select * from orders where userid =? ";
		try {
			orders = queryRunner.query(conn, sql, new BeanListHandler<Order>(Order.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}


}
