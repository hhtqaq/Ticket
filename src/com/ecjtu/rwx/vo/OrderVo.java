package com.ecjtu.rwx.vo;

import java.util.List;

import com.ecjtu.rwx.bean.Order;

public class OrderVo {
	private Order order;
	private List<OrderItemVo> orderitemVos;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItemVo> getOrderitemVos() {
		return orderitemVos;
	}

	public void setOrderitemVos(List<OrderItemVo> orderitemVos) {
		this.orderitemVos = orderitemVos;
	}

}
