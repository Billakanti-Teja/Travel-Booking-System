# Travel Booking System

Welcome to the ABC Travels Booking System! This is a **menu-driven console application** designed to automate booking operations for a new Travels and Logistics company. The application is implemented using **Core Java** and the **Collections API** (Majorly List and Map APIs), with database integration.
One of the main reason behind building this application (for now only backend), is to get a robust understanding of core java and all java fundamentals.

## Features

1. **Dynamic Menu System**
   - Upon logging in, the application displays the company logo loaded from a local file.
   - Dynamic menu-driven options for seamless user interaction.

2. **New Admin User Registration**
   - Capture essential user details:
     - First Name
     - Last Name
     - Mobile Number
     - Gender
     - Email ID
     - Password
     - Failed Login Count (Backend)
     - Account Status (Backend)

3. **Account Locking**
   - Accounts are locked automatically after **five invalid login attempts**.

4. **Journey Planning**
   - Users can:
     - Select source, destination, travel date, and number of passengers.
     - Validate travel details (date, source, destination, and vacancies).
     - Generate a bill based on:
       - Fare per passenger.
       - **Weekend surcharge** (+200 on base fare).
       - GST calculation.
     - Confirm bookings and return to the main menu.

5. **Rescheduling**
   - Users can edit travel dates for confirmed bookings.

6. **Error Handling**
   - Comprehensive exception handling for runtime errors.
   - User-friendly error messages.

7. **Java 8 Features**
   - Utilize **LocalDate** for date-related validations and operations.

8. **System Logging**
   - Detailed system logs for better application understanding and troubleshooting.

## Requirements

- **Java 8 or higher**
- Local storage for:
  - Company logo file.
  - User and booking data.

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/<username>/abc-travels-booking-system.git
   ```
2. Navigate to the project directory:
   ```bash
   cd abc-travels-booking-system
   ```
3. Compile the project:
   ```bash
   javac -d bin src/*.java
   ```
4. Run the application:
   ```bash
   java -cp bin Main
   ```

## How to Use

1. **Login**: View the company logo upon starting the application.
2. **Menu Options**:
   - Register a new admin user.
   - Lock an account after invalid login attempts.
   - Plan and book a journey with options to validate and confirm travel details.
   - Reschedule travel dates for confirmed bookings.
   - Return to the main menu after completing actions.

## Code Quality Guidelines

- Reuse methods to avoid redundancy.
- Follow proper naming conventions and maintain modularity.
- Use **sysouts (System.out.println)** for clear, user-friendly logs.
- Incorporate **Java 8 features** for modern and efficient implementations.

## Additional Features

- Can add more validations and business rules as needed.
- Extend the application for future enhancements like payment integration or customer feedback.
