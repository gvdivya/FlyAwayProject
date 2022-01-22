package com.flyawayproject;

public class AdminBookingDTO {
	
	
	Integer bookingId;
	Integer bflightId;
	
	Integer nopax;
	String bookingdate;
	String email;
	
	
	public Integer getBFlightId() {
		return bflightId;
	}
	public void setBFlightId(Integer bflightId) {
		this.bflightId = bflightId;
	}
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public Integer getNoPax() {
		return nopax;
	}
	public void setNoPax(Integer nopax) {
		this.nopax = nopax;
	}
	
	public String getBookingDate() {
		return bookingdate;
	}
	public void setBookingDate(String bookingdate) {
		this.bookingdate = bookingdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
