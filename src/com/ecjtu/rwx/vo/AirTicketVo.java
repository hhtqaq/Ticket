package com.ecjtu.rwx.vo;

import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.bean.Flight;

public class AirTicketVo {
	private Airticket airticket;
	private Flight flight;

	public Airticket getAirticket() {
		return airticket;
	}

	public void setAirticket(Airticket airticket) {
		this.airticket = airticket;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
