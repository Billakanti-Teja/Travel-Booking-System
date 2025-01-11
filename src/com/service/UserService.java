package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.DataBaseUtil;
import com.model.User;

//Manages user-related operations including new admin registration and user login. It also handles the tracking of invalid login attempts and locks the account after 5 failed attempts.

public class UserService {

	public UserService() {

	}

	public void registerNewAdmin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nNew Admin User Registration");

		System.out.print("Enter first name: ");
		String firstName = scanner.nextLine();

		System.out.print("Enter last name: ");
		String lastName = scanner.nextLine();

		System.out.print("Enter mobile number: ");
		String mobileNumber = scanner.nextLine();

		System.out.print("Enter gender: ");
		String gender = scanner.nextLine();

		System.out.print("Enter email: ");
		String email = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		if (isUserExists(email)) {
			System.out.println("User with this email: " + email + " already exists");
			return;
		}

		String sql = "INSERT INTO users (first_name, last_name, mobile_number, gender, email, password, failed_count, account_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DataBaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, mobileNumber);
			pstmt.setString(4, gender);
			pstmt.setString(5, email);
			pstmt.setString(6, password);
			pstmt.setInt(7, 0); // failed_count
			pstmt.setString(8, "Active"); // account_status
			pstmt.executeUpdate();
			System.out.println("Registration successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User login() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nUser Login");

		System.out.print("Enter email: ");
		String email = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		String sql = "SELECT * FROM users WHERE email = ?";
		try (Connection conn = DataBaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int failedCount = rs.getInt("failed_count");
				if (failedCount >= 5) {
					System.out.println("User account is locked due to multiple invalid login attempts.");
					return null;
				}
				if (rs.getString("password").equals(password)) {
					System.out.println("\nLogin Successful");
					resetFailedAttempts(email);
					return new User(rs.getString("first_name"), rs.getString("last_name"),
							rs.getString("mobile_number"), rs.getString("gender"), email, password, 0,
							rs.getString("account_status"));
				} else {
					incrementFailedAttempts(email, failedCount);
					System.out.println("\nInvalid Credentials. Attempt: " + (failedCount + 1) + " for email: " + email);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("No user found with email: " + email);
		return null;
	}

	private void resetFailedAttempts(String email) {
		String sql = "UPDATE users SET failed_count = 0 WHERE email = ?";
		try (Connection conn = DataBaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void incrementFailedAttempts(String email, int failedCount) {
		String sql = "UPDATE users SET failed_count = ? WHERE email = ?";
		try (Connection conn = DataBaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, failedCount + 1);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean isUserExists(String email) {
		String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
		try (Connection conn = DataBaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
