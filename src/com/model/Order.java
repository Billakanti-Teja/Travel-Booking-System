package com.model;

//Represents a booking order with details like order ID, the associated route, journey plan, order amount, and status.
public class Order {
	private Integer orderId;
	private Route route;
	private Journey requestedJourneyPlan;
	private Double orderAmount;
	private String orderStatus;
	private Integer noOfPassengers;

	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Journey getRequestedJourneyPlan() {
		return requestedJourneyPlan;
	}

	public void setRequestedJourneyPlan(Journey requestedJourneyPlan) {
		this.requestedJourneyPlan = requestedJourneyPlan;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", route=" + route + ", requestedJourneyPlan=" + requestedJourneyPlan
				+ ", orderAmount=" + orderAmount + ", orderStatus=" + orderStatus + ", noOfPassengers=" + noOfPassengers
				+ "]";
	}

}
