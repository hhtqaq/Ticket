package com.ecjtu.rwx.bean;

public class Airticket {
    private Integer id;

    private Integer flightid;

    private Integer price;

    private String seattype;
    
    private Integer discontedprice;
    //业务需要座位数 表中可以没有
    private Integer seatnum;
    
    
	public Integer getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(Integer seatnum) {
		this.seatnum = seatnum;
	}


	public Integer getDiscontedprice() {
		return discontedprice;
	}

	public void setDiscontedprice(Integer discontedprice) {
		this.discontedprice = discontedprice;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getFlightid() {
		return flightid;
	}

	public void setFlightid(Integer flightid) {
		this.flightid = flightid;
	}

	public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeattype() {
        return seattype;
    }

    public void setSeattype(String seattype) {
        this.seattype = seattype == null ? null : seattype.trim();
    }

	@Override
	public String toString() {
		return "Airticket [id=" + id + ", flightid=" + flightid + ", price=" + price + ", seattype=" + seattype
				+ ", discontedprice=" + discontedprice + "]";
	}
    
}