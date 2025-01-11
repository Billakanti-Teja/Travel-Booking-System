package com.service;
//Manages journey-related operations like planning a journey, generating bills, and rescheduling journeys. It leverages the Route and Order classes to facilitate these operations.

import com.model.Route;
import com.model.Order;
import com.DataBaseUtil;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JourneyService {
	public void planJourney() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("\nPlan Journey");

	    System.out.print("Enter source: ");
	    String source = scanner.nextLine();

	    System.out.print("Enter destination: ");
	    String destination = scanner.nextLine();

	    System.out.print("Enter journey date (YYYY-MM-DD): ");
	    String journeyDateString = scanner.nextLine();
	    LocalDate journeyDate = LocalDate.parse(journeyDateString);

	    System.out.print("Enter number of passengers: ");
	    int passengers = scanner.nextInt();

	    List<Route> routes = getAvailableRoutes(source, destination, journeyDate, passengers);
	    if (routes.isEmpty()) {
	        System.out.println("No available routes for the given journey details.");
	        return;
	    }

	    // Display available routes and let the user select one
	    System.out.println("Available routes:");
	    for (int i = 0; i < routes.size(); i++) {
	        Route route = routes.get(i);
	        double price = route.getTicketPricePerPassenger();
	        if (journeyDate.getDayOfWeek() == DayOfWeek.SATURDAY || journeyDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
	            price += 200;  // Surge price by 200 INR on weekends
	        }
	        System.out.printf("%d. %s to %s on %s - Price: %.2f INR\n",
	                i + 1, route.getSource(), route.getDestination(), route.getJourneyDate(), price);
	    }

	    System.out.print("Select a route (enter the number): ");
	    int routeIndex = scanner.nextInt() - 1;
	    Route selectedRoute = routes.get(routeIndex);

	    // Calculate the total price considering the surge on weekends
	    double totalPrice = selectedRoute.getTicketPricePerPassenger() * passengers;
	    if (journeyDate.getDayOfWeek() == DayOfWeek.SATURDAY || journeyDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
	        totalPrice += 200 * passengers;  // Surge price by 200 INR per passenger on weekends
	    }

	    // Create and save the order
	    Order order = new Order();
	    order.setRoute(selectedRoute);
	    order.setOrderAmount(totalPrice);
	    order.setNoOfPassengers(passengers);
	    order.setOrderStatus("created");
	    saveOrder(order);

	    System.out.println("Journey booked successfully. Order details:");
	    System.out.println(order);
	}

    public void reScheduleJourney() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nReschedule Journey");

        System.out.print("Enter your Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the new journey date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        LocalDate newJourneyDate = LocalDate.parse(newDate, DateTimeFormatter.ISO_LOCAL_DATE);

        // Find a route with the new journey date
        String findRouteSql = "SELECT route_id FROM routes WHERE journey_date = ? LIMIT 1";
        int newRouteId = -1;
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(findRouteSql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(newJourneyDate));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                newRouteId = rs.getInt("route_id");
            } else {
                System.out.println("No routes found for the new journey date.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Update the order with the new route ID
        String updateSql = "UPDATE orders SET route_id = ? WHERE order_id = ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setInt(1, newRouteId);
            pstmt.setInt(2, orderId);
            int updatedRows = pstmt.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Journey rescheduled successfully.");
            } else {
                System.out.println("Failed to reschedule the journey.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private List<Route> getAvailableRoutes(String source, String destination, LocalDate journeyDate, int passengers) {
        List<Route> availableRoutes = new ArrayList<>();
        String sql = "SELECT * FROM routes WHERE source = ? AND destination = ? AND journey_date = ? AND no_of_seats_available >= ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, source);
            pstmt.setString(2, destination);
            pstmt.setDate(3, Date.valueOf(journeyDate));
            pstmt.setInt(4, passengers);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Route route = new Route();
                route.setRouteId(rs.getInt("route_id"));
                route.setSource(rs.getString("source"));
                route.setDestination(rs.getString("destination"));
                route.setJourneyDate(rs.getDate("journey_date").toLocalDate());
                route.setTicketPricePerPassenger(rs.getDouble("ticket_price_per_passenger"));
                route.setNoOfSeatsAvailable(rs.getInt("no_of_seats_available"));
                availableRoutes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableRoutes;
    }

    private void saveOrder(Order order) {
        String sql = "INSERT INTO orders (route_id, order_amount, no_of_passengers, order_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getRoute().getRouteId());
            pstmt.setDouble(2, order.getOrderAmount());
            pstmt.setInt(3, order.getNoOfPassengers());
            pstmt.setString(4, order.getOrderStatus());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
