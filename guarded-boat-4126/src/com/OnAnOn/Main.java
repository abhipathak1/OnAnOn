package com.OnAnOn;

import java.util.Map;
import java.util.Scanner;

import com.OnAnOn.entities.Driver;
import com.OnAnOn.entities.Passenger;
import com.OnAnOn.exception.InvalidDetailsException;
import com.OnAnOn.service.CustomerService;
import com.OnAnOn.service.DriverService;
import com.OnAnOn.utility.Admin;
import com.OnAnOn.utility.FileExists;

public class Main {
	private static Scanner scanner = new Scanner(System.in);

	private static CustomerService customerService = new CustomerService(FileExists.PassengerFile());
	private static DriverService driverService = new DriverService(FileExists.DriverFile());

	public static void main(String[] args) {
		displayWelcomeMessage();

		while (true) {
			displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				adminLogin();
				break;
			case 2:
				login();
				break;
			case 3:
				signup();
				break;
			case 4:
				System.out.println("Thank you for using RoamEase. Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void displayWelcomeMessage() {
		System.out.println();
		System.out.println("🙏🏻 Welcome to OnAnOn - Your Personal Ride-Hailing Application 🚗");
	}

	private static void displayMainMenu() {
		String horizontalLine = "+----------------------------+";
		String emptyLine = "|                            |";
		String title = "|  Please select an option:  |";
		String option1 = "|  1. Admin Login            |";
		String option2 = "|  2. Login as a Passenger   |";
		String option3 = "|  3. Signup as a Passenger  |";
		String option4 = "|  4. Exit                   |";
		String prompt = "|  Enter your choice:        |";

		System.out.println(horizontalLine);
		System.out.println(emptyLine);
		System.out.println(title);
		System.out.println(option1);
		System.out.println(option2);
		System.out.println(option3);
		System.out.println(option4);
		System.out.println(prompt);
		System.out.println(emptyLine);
		System.out.println(horizontalLine);
	}

	private static void login() {
		System.out.print("Enter your username: ");
		String username = scanner.nextLine();
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();

		if (customerService.login(username, password)) {
			// Passenger logged in successfully
			System.out.println("Login successful! Welcome, " + username + "!");
			Passenger passenger = customerService.getPassengerByUsername(username);
			processPassengerRequest(passenger);
			// Proceed with passenger functionality
			// Implement passenger-related logic here
		} else {
			System.out.println("Invalid username or password. Please try again.");
		}
	}

	private static void signup() {
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter your last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter your address: ");
		String address = scanner.nextLine();
		System.out.print("Enter your mobile number: ");
		String mobileNo = scanner.nextLine();
		System.out.print("Enter a unique username: ");
		String username = scanner.nextLine();
		System.out.print("Enter a password: ");
		String password = scanner.nextLine();

		try {
			boolean signupSuccess = customerService.signup(firstName, lastName, address, mobileNo, username, password);
			if (signupSuccess) {
				System.out.println("Signup successful! Please proceed to login.");
			}
		} catch (InvalidDetailsException e) {
			System.out.println("Signup failed: " + e.getMessage());
		}
	}

	private static void adminLogin() {
		System.out.print("Enter your username: ");
		String username = scanner.nextLine();
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();

		if (Admin.authenticate(username, password)) {
			System.out.println("Admin login successful!");
			adminMenu();
		} else {
			System.out.println("Invalid username or password. Please try again.");
		}
	}

	private static void adminMenu() {
		while (true) {
			displayAdminMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addDriver();
				break;
			case 2:
				editDriver();
				break;
			case 3:
				removeDriver();
				break;
			case 4:
				showDrivers();
				break;
			case 5:
				showPassengers();
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void showDrivers() {
		Map<Integer, Driver> drivers = driverService.getAllDrivers();
		if (drivers.isEmpty()) {
			System.out.println("No drivers found.");
		} else {
			System.out.println("List of Drivers:");
			for (Driver driver : drivers.values()) {
				System.out.println("                             ");
				System.out.println("Driver Name:          " + driver.getDriverName());
				System.out.println("Vehicle Type:         " + driver.getVehicleType());
				System.out.println("Vehicle Registration: " + driver.getVehicleRegistration());
				System.out.println("-----------------------------");
			}
		}
	}

	private static void displayAdminMenu() {
	    String horizontalLine = "+--------------------------+";
	    String emptyLine = "|                          |";
	    String title = "|      Admin Menu:         |";
	    String option1 = "|  1. Add a Driver         |";
	    String option2 = "|  2. Edit Driver Details  |";
	    String option3 = "|  3. Remove a Driver      |";
	    String option4 = "|  4. Show all Drivers     |";
	    String option5 = "|  5. Show all Passengers  |";
	    String option6 = "|  6. Logout               |";
	    String prompt = "|  Enter your choice:      |";

	    System.out.println(horizontalLine);
	    System.out.println(emptyLine);
	    System.out.println(title);
//	    System.out.println(emptyLine);
	    System.out.println(option1);
	    System.out.println(option2);
	    System.out.println(option3);
	    System.out.println(option4);
	    System.out.println(option5);
	    System.out.println(option6);
//	    System.out.println(emptyLine);
	    System.out.println(prompt);
	    System.out.println(emptyLine);
	    System.out.println(horizontalLine);
	}

	private static void addDriver() {
		System.out.print("Enter driver name: ");
		String driverName = scanner.nextLine();
		System.out.print("Enter vehicle type: ");
		String vehicleType = scanner.nextLine();
		System.out.print("Enter vehicle registration: ");
		String vehicleRegistration = scanner.nextLine();

		Driver driver = new Driver(driverName, vehicleType, vehicleRegistration);
		driverService.addDriver(driver);
//		System.out.println("Driver added successfully!");
	}

	private static void editDriver() {
		System.out.print("Enter the username of the driver you want to edit: ");
		String username = scanner.nextLine();

		Driver driver = driverService.getDriverByUsername(username);
		if (driver != null) {
			System.out.println("Driver found!");
			System.out.println("Current details:");
			System.out.println();
			System.out.println("Driver Name:     " + driver.getDriverName());
			System.out.println("Vehicle Type:    " + driver.getVehicleType());
			System.out.println("Vehicle Registration: " + driver.getVehicleRegistration());

			System.out.print("Enter new driver name (leave blank to keep current): ");
			String newDriverName = scanner.nextLine();
			System.out.print("Enter new vehicle type (leave blank to keep current): ");
			String newVehicleType = scanner.nextLine();
			System.out.print("Enter new vehicle registration (leave blank to keep current): ");
			String newVehicleRegistration = scanner.nextLine();

			if (!newDriverName.isEmpty()) {
				driver.setDriverName(newDriverName);
			}
			if (!newVehicleType.isEmpty()) {
				driver.setVehicleType(newVehicleType);
			}
			if (!newVehicleRegistration.isEmpty()) {
				driver.setVehicleRegistration(newVehicleRegistration);
			}

			System.out.println("Driver details updated successfully!");
		} else {
			System.out.println("Driver not found. Please try again.");
		}
	}

	private static void removeDriver() {
		System.out.print("Enter the username of the driver you want to remove: ");
		String username = scanner.nextLine();

		Driver driver = driverService.getDriverByUsername(username);
		if (driver != null) {
			driverService.removeDriver(driver);
//			System.out.println("Driver removed successfully!");
		} else {
			System.out.println("Driver not found. Please try again.");
		}
	}

	private static void showPassengers() {
		Map<Integer, Passenger> passengers = customerService.getPassengerData();
		if (passengers.isEmpty()) {
			System.out.println("No passengers found.");
		} else {
			System.out.println("List of Passengers:");
			for (Passenger passenger : passengers.values()) {
				System.out.println();
				System.out.println("Passenger Name: " + passenger.getFirstName() + " " + passenger.getLastName());
				System.out.println("Username:       " + passenger.getUsername());
				System.out.println("Address:        " + passenger.getAddress());
				System.out.println("Mobile Number:  " + passenger.getMobileNo());
				System.out.println("-----------------------------");
			}
		}
	}

	private static void processPassengerRequest(Passenger passenger) {
		System.out.println();
		System.out.print("Enter your pickup location: ");
		String pickupLocation = scanner.nextLine();
		System.out.print("Enter your destination: ");
		String destination = scanner.nextLine();
		System.out.print("Enter the distance in kilometers: ");
		double distance = scanner.nextDouble();

		double fare = calculateFare(distance);
		System.out.println("Total fare: Rs" + fare);
		System.out.println();

		System.out.print("Enter 'confirm' to book the ride or 'cancel' to cancel: ");
		String choice = scanner.next();
		scanner.nextLine();

//		if (choice.equalsIgnoreCase("confirm")) {
//			System.out.println("Booking the ride...");
//		} else {
//			System.out.println("Ride booking cancelled.");
//		}

		System.out.println();
		if (choice.equalsIgnoreCase("confirm")) {

			System.out.println("Your ride has been successfully booked. We will notify you through a notification.");
		} else {
			System.out.println("Your ride booking has been cancelled. We apologize for any inconvenience caused.");
		}
		System.out.println();
		System.out.println("Thank-You For Using OnAnOn  (❁´◡`❁)");
	}

	private static double calculateFare(double distance) {
		double baseFare = 25.0;
		double perKilometerCharge = 20.0;

		if (distance <= 0) {
			return 0;
		} else if (distance == 1) {
			return baseFare;
		} else {
			return baseFare + perKilometerCharge * (distance - 1);
		}
	}

}
