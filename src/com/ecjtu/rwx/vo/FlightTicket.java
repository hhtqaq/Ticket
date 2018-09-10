package com.ecjtu.rwx.vo;

import java.util.List;

import com.ecjtu.rwx.bean.Airticket;
import com.ecjtu.rwx.bean.Flight;
/**
 * 扩展业务类   航班 和机票
 * @author Administrator
 *
 */
public class FlightTicket {

	 	private Flight flight;
	 	private Airticket airticket;
	 	
	 	private List<Airticket> airtickets;
	 	
	 	
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
		public List<Airticket> getAirtickets() {
			return airtickets;
		}
		public void setAirtickets(List<Airticket> airtickets) {
			this.airtickets = airtickets;
		}
		@Override
		public String toString() {
			return "FlightTicket [flight=" + flight + ", airticket=" + airticket + ", airtickets=" + airtickets + "]";
		}
		
}
