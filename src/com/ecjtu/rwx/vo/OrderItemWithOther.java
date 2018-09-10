package com.ecjtu.rwx.vo;

/**
 * 连接3张表的类
 * @author Administrator
 *
 */
public class OrderItemWithOther {
	/**
	 * SELECT p.`name`,p.idcard,p.type,
	 * a.price,a.seattype,f.aircompany,f.startcity
	,f.endcity,f.starttime,f.endtime,
	 * f.airname,f.airtype,f.startairport,f.endairport
	 */
	//乘客表
	private String name;
	private String idcard;
	private String type;
	//航班表
	private String aircompany;
	private String startcity;
	private String endcity;
	private String starttime;
	private String endtime;
	private String airname;
	private String airtype;
	private String startairport;
	private String endairport;
	//机票表
	private int price;
	private String seattype;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAircompany() {
		return aircompany;
	}
	public void setAircompany(String aircompany) {
		this.aircompany = aircompany;
	}
	public String getStartcity() {
		return startcity;
	}
	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}
	public String getEndcity() {
		return endcity;
	}
	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getAirname() {
		return airname;
	}
	public void setAirname(String airname) {
		this.airname = airname;
	}
	public String getAirtype() {
		return airtype;
	}
	public void setAirtype(String airtype) {
		this.airtype = airtype;
	}
	public String getStartairport() {
		return startairport;
	}
	public void setStartairport(String startairport) {
		this.startairport = startairport;
	}
	public String getEndairport() {
		return endairport;
	}
	public void setEndairport(String endairport) {
		this.endairport = endairport;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSeattype() {
		return seattype;
	}
	public void setSeattype(String seattype) {
		this.seattype = seattype;
	}

	
	
	
}
