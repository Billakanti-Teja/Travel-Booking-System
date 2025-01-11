package com.model;
//Represents an admin user with properties like name, email, password, mobile number, gender, failed login count, and account status.
public class User {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String gender;
    private String email;
    private String password;
    private int failedCount;
    private String accountStatus;

    public User(String firstName, String lastName, String mobileNumber, String gender, String email, String password, int failedCount, String accountStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.failedCount = failedCount;
        this.accountStatus = accountStatus;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber
				+ ", gender=" + gender + ", email=" + email + ", password=" + password + ", failedCount=" + failedCount
				+ ", accountStatus=" + accountStatus + "]";
	}

    
}
