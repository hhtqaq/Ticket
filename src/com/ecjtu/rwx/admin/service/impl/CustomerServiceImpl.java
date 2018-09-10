package com.ecjtu.rwx.admin.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecjtu.rwx.admin.service.CustomerService;
import com.ecjtu.rwx.bean.Order;
import com.ecjtu.rwx.bean.User;
import com.ecjtu.rwx.dao.OrderItemDao;
import com.ecjtu.rwx.dao.UserDao;
import com.ecjtu.rwx.dao.impl.OrderItemDaoIlmpl;
import com.ecjtu.rwx.dao.impl.UserDaoImpl;
import com.ecjtu.rwx.dbutils.PageBean;
import com.ecjtu.rwx.vo.OrderItemWithOther;

public class CustomerServiceImpl implements CustomerService {
	
	private UserDao userdao=new UserDaoImpl();
	private OrderItemDao orderItemdao=new OrderItemDaoIlmpl();
	
	@Override
	public void findAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String condition=req.getParameter("condition");
		String page=req.getParameter("currentPage");
		int currentPage=1;
		if(page!=null&&!"".equals(page)){
			currentPage=Integer.parseInt(page);
		}
		PageBean<User> users=userdao.findAll(condition,currentPage,3);
		String msg=null;
		if(users==null){
			msg="没有找到相关的客户，请重新查询";
			req.setAttribute("msg", msg);
		}else {
			req.setAttribute("users", users);
		}
		if(condition==null){
			condition="";
		}
		req.setAttribute("condition", condition);
		req.getRequestDispatcher("/WEB-INF/adminpages/customer/listCustomer.jsp").forward(req, resp);
	}

	/**
	 * 一个用户id对应多个订单  SELECT * from orders where userid=1
	 * 一个订单对应多个订单详情  SELECT * from orderitem where orderid=1
	 * 多个订单详情对应一场票
	 * 
	 */
	@Override
	public void QueryCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		List<Order> orders=null;
		if(id!=null){
			orders=userdao.findById(Integer.parseInt(id));
		}
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("/WEB-INF/adminpages/customer/queryCustomer.jsp").forward(req,resp);
	}

	@Override
	public void ListOrderItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderid=req.getParameter("id");
		List<OrderItemWithOther> orderitems=null;
		if(orderid!=null){
			orderitems=orderItemdao.findItemById(Integer.parseInt(orderid));
		}
		req.setAttribute("orderitems", orderitems);
		req.getRequestDispatcher("/WEB-INF/adminpages/customer/ListOrderItems.jsp").forward(req, resp);
	}
}
