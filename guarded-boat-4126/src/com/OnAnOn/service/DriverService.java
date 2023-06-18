package com.OnAnOn.service;

import java.util.Map;

import com.OnAnOn.entities.Driver;
import com.OnAnOn.utility.FileExists;

public class DriverService {
	private Map<Integer, Driver> driverData;

	public DriverService(Map<Integer, Driver> driverData) {
		this.driverData = driverData;
	}
	

	public void addDriver(Driver driver) {
		int driverId = driverData.size() + 1;
        driverData.put(driverId, driver);
        FileExists.saveDriverToFile(driverData);
		System.out.println("Driver added successfully!");
	}

	public void editDriver(int driverId, String newDriverName, String newVehicleType, String newVehicleRegistration) {
		Driver driver = driverData.get(driverId);
		if (driver != null) {
			driver.setDriverName(newDriverName);
			driver.setVehicleType(newVehicleType);
			driver.setVehicleRegistration(newVehicleRegistration);
			System.out.println("Driver details updated successfully!");
		} else {
			System.out.println("Driver not found. Please try again.");
		}
	}

	public void removeDriver(Driver driver) {
		int driverId = -1;
		for (Map.Entry<Integer, Driver> entry : driverData.entrySet()) {
			if (entry.getValue().equals(driver)) {
				driverId = entry.getKey();
				break;
			}
		}
		if (driverId != -1) {
			driverData.remove(driverId);
			System.out.println("Driver removed successfully!");
		} else {
			System.out.println("Driver not found. Please try again.");
		}
	}

	public void displayDrivers() {
		System.out.println("Driver List:");
		for (Map.Entry<Integer, Driver> entry : driverData.entrySet()) {
			int driverId = entry.getKey();
			Driver driver = entry.getValue();
			System.out.println("Driver ID: " + driverId);
			System.out.println(driver);
		}
	}

	public Map<Integer, Driver> getDriverData() {
		return driverData;
	}

	public Driver getDriverByUsername(String username) {
		for (Driver driver : driverData.values()) {
			if (driver.getDriverName().equals(username)) {
				return driver;
			}
		}
		return null;
	}

	public Map<Integer, Driver> getAllDrivers() {
		return driverData;
	}
}
