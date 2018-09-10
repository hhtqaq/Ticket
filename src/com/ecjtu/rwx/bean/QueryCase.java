package com.ecjtu.rwx.bean;

/**
 * 封装多个查询条件的实体类(用于订单的查询)
 * 
 * @author skty
 *
 */
public class QueryCase {
	private String userId;
	private String flight_startTime;
	private String flight_endTime;
	private String order_startTime;
	private String order_endTime;
	private String flight_startCity;
	private String flight_endCity;
	private String order_purchasername;
	private String order_ispaid;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFlight_startTime() {
		return flight_startTime;
	}

	public void setFlight_startTime(String flight_startTime) {
		this.flight_startTime = flight_startTime;
	}

	public String getFlight_endTime() {
		return flight_endTime;
	}

	public void setFlight_endTime(String flight_endTime) {
		this.flight_endTime = flight_endTime;
	}

	public String getOrder_startTime() {
		return order_startTime;
	}

	public void setOrder_startTime(String order_startTime) {
		this.order_startTime = order_startTime;
	}

	public String getOrder_endTime() {
		return order_endTime;
	}

	public void setOrder_endTime(String order_endTime) {
		this.order_endTime = order_endTime;
	}

	public String getFlight_startCity() {
		return flight_startCity;
	}

	public void setFlight_startCity(String flight_startCity) {
		this.flight_startCity = flight_startCity;
	}

	public String getFlight_endCity() {
		return flight_endCity;
	}

	public void setFlight_endCity(String flight_endCity) {
		this.flight_endCity = flight_endCity;
	}

	public String getOrder_purchasername() {
		return order_purchasername;
	}

	public void setOrder_purchasername(String order_purchasername) {
		this.order_purchasername = order_purchasername;
	}

	public String getOrder_ispaid() {
		return order_ispaid;
	}

	public void setOrder_ispaid(String order_ispaid) {
		this.order_ispaid = order_ispaid;
	}

}
