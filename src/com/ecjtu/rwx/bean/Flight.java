package com.ecjtu.rwx.bean;

public class Flight {
    private Integer id;
    private String aircompany;
    private String airname;
    private String endcity;

    private String startairport;

    private String endairport;

    private String starttime;

    private String endtime;

    private String startcity;

    private Integer firstclassnum;

    //为了业务连接查询添加了两个字段
    private float firstprice;
    private Integer secondclassnum;
    private float secondprice;
    private Integer thirdclassnum;
    private float thirdprice;
    //添加了一个机型
    private String airtype;
    
    
    

	public float getFirstprice() {
		return firstprice;
	}

	public void setFirstprice(float firstprice) {
		this.firstprice = firstprice;
	}

	public float getSecondprice() {
		return secondprice;
	}

	public void setSecondprice(float secondprice) {
		this.secondprice = secondprice;
	}

	public float getThirdprice() {
		return thirdprice;
	}

	public void setThirdprice(float thirdprice) {
		this.thirdprice = thirdprice;
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
       this.id=id;
    }

    public String getAircompany() {
        return aircompany;
    }

    public void setAircompany(String aircompany) {
        this.aircompany = aircompany == null ? null : aircompany.trim();
    }

    public String getEndcity() {
        return endcity;
    }

    public void setEndcity(String endcity) {
        this.endcity = endcity == null ? null : endcity.trim();
    }

    public String getStartairport() {
        return startairport;
    }

    public void setStartairport(String startairport) {
        this.startairport = startairport == null ? null : startairport.trim();
    }

    public String getEndairport() {
        return endairport;
    }

    public void setEndairport(String endairport) {
        this.endairport = endairport == null ? null : endairport.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public String getStartcity() {
        return startcity;
    }

    public void setStartcity(String startcity) {
        this.startcity = startcity == null ? null : startcity.trim();
    }

    public Integer getFirstclassnum() {
        return firstclassnum;
    }

    public void setFirstclassnum(Integer firstclassnum) {
        this.firstclassnum = firstclassnum;
    }

    public Integer getSecondclassnum() {
        return secondclassnum;
    }

    public void setSecondclassnum(Integer secondclassnum) {
        this.secondclassnum = secondclassnum;
    }

    public Integer getThirdclassnum() {
        return thirdclassnum;
    }

    public void setThirdclassnum(Integer thirdclassnum) {
        this.thirdclassnum = thirdclassnum;
    }

	@Override
	public String toString() {
		return "Flight [id=" + id + ", aircompany=" + aircompany + ", endcity=" + endcity + ", startairport="
				+ startairport + ", endairport=" + endairport + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", startcity=" + startcity + ", firstclassnum=" + firstclassnum + ", secondclassnum=" + secondclassnum
				+ ", thirdclassnum=" + thirdclassnum + "]";
	}
}