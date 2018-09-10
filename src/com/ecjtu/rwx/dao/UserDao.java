package com.ecjtu.rwx.dao;

import java.util.List;

import com.ecjtu.rwx.bean.Order;
import com.ecjtu.rwx.bean.User;
import com.ecjtu.rwx.dbutils.PageBean;

public interface UserDao {

	/**
	 * 根据用户名密码查询
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkUser(String username,String password);

	/**
	 * 根据用户名查找
	 * @param logname
	 * @return
	 */
	public boolean findByLogname(String logname);

	/**
	 * 添加用户
	 * @param username  用户名
	 * @param password	密码	
	 * @param phone		联系电话
	 * @return
	 */
	public boolean addUser(String username, String password, String phone);
	
	/**
	 * 根据用户名 或者 电话的号码查询
	 * @param condition
	 * @return
	 */
	public PageBean<User> findAll(String condition,int currentPage,int pagesize);

	/**
	 * 查询id查询客户信息
	 * @param id
	 * @return
	 */
	public List<Order> findById(int id);

	
}
