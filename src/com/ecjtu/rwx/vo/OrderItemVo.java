package com.ecjtu.rwx.vo;

import com.ecjtu.rwx.bean.Orderitem;
import com.ecjtu.rwx.bean.Passenger;

public class OrderItemVo {
	private Orderitem orderitem;
	private Passenger passenger;
	private AirTicketVo airticketVo;

	public Orderitem getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(Orderitem orderitem) {
		this.orderitem = orderitem;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public AirTicketVo getAirticketVo() {
		return airticketVo;
	}

	public void setAirticketVo(AirTicketVo airticketVo) {
		this.airticketVo = airticketVo;
	}

}
