package com.flyawayproject;

public class AdminFlightResultsDTO {

	Integer flightId;
	Float price;
	String departuretime;
	String arrivaltime;
	Integer rid;
	Integer seats;
	
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	
	public Integer getRouteId() {
		return rid;
	}
	public void setRouteId(Integer rid) {
		this.rid = rid;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
}
