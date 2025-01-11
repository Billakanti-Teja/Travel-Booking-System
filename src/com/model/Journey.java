package com.model;
//Represents the details of a journey plan, including the journey date and the number of passengers.
import java.time.LocalDate;

public class Journey {
    private LocalDate journeyDate;
    private int numberOfPassengers;

    public Journey(LocalDate journeyDate, int numberOfPassengers) {
        this.journeyDate = journeyDate;
        this.numberOfPassengers = numberOfPassengers;
    }

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	@Override
	public String toString() {
		return "Journey [journeyDate=" + journeyDate + ", numberOfPassengers=" + numberOfPassengers + "]";
	}

}