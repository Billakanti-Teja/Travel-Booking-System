package com.model;
//Represents a travel route with details like source, destination, travel date, fare, and available seats.
import java.time.LocalDate;

public class Route {
    private Integer routeId;
    private String source;
    private String destination;
    private LocalDate journeyDate;
    private double ticketPricePerPassenger;
    private Integer noOfSeatsAvailable;

    public Route(Integer routeId, String source, String destination, LocalDate journeyDate, double ticketPrice, Integer noOfSeatsAvailable) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.journeyDate = journeyDate;
        this.ticketPricePerPassenger = ticketPrice;
        this.noOfSeatsAvailable = noOfSeatsAvailable;
    }

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public double getTicketPricePerPassenger() {
		return ticketPricePerPassenger;
	}

	public void setTicketPricePerPassenger(double ticketPricePerPassenger) {
		this.ticketPricePerPassenger = ticketPricePerPassenger;
	}

	public Integer getNoOfSeatsAvailable() {
		return noOfSeatsAvailable;
	}

	public void setNoOfSeatsAvailable(Integer noOfSeatsAvailable) {
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", source=" + source + ", destination=" + destination + ", journeyDate="
				+ journeyDate + ", ticketPricePerPassenger=" + ticketPricePerPassenger + ", noOfSeatsAvailable="
				+ noOfSeatsAvailable + "]";
	}
	public Route() {
		
	}

}
