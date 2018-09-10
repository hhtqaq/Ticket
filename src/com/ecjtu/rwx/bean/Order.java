package com.ecjtu.rwx.bean;

public class Order {
	private Integer id;

	private Integer userid;

	private String purchasername;

	private String purchaserphone;

	private Float totalprice;

	private String insurance;

	private String time;

	private String ispaid;

	public String getIspaid() {
		return ispaid;
	}

	public void setIspaid(String ispaid) {
		this.ispaid = ispaid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPurchaserphone() {
		return purchaserphone;
	}

	public void setPurchaserphone(String purchaserphone) {
		this.purchaserphone = purchaserphone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPurchasername() {
		return purchasername;
	}

	public void setPurchasername(String purchaser) {
		this.purchasername = purchaser.trim();
	}

	public Float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance == null ? null : insurance.trim();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time == null ? null : time.trim();
	}
}