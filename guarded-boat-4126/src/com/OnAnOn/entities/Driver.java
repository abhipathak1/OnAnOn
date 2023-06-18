package com.OnAnOn.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Driver implements Serializable{
	private String driverName;
	private String vehicleType;
	private String vehicleRegistration;
	private boolean availabilityStatus;
	private List<String> routes;

	public Driver(String driverName, String vehicleType, String vehicleRegistration) {
		this.driverName = driverName;
		this.vehicleType = vehicleType;
		this.vehicleRegistration = vehicleRegistration;
		this.availabilityStatus = true;
		this.routes = new ArrayList<>();
	}

	// Getters and Setters

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	public boolean isAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public List<String> getRoutes() {
		return routes;
	}

	public void setRoutes(List<String> routes) {
		this.routes = routes;
	}

	// Methods

	public void addRoute(String route) {
		routes.add(route);
	}

	public void editRoute(int index, String newRoute) {
		routes.set(index, newRoute);
	}

	public void removeRoute(int index) {
		routes.remove(index);
	}

	@Override
	public String toString() {
		return "Driver{" + "driverName='" + driverName + '\'' + ", vehicleType='" + vehicleType + '\''
				+ ", vehicleRegistration='" + vehicleRegistration + '\'' + ", availabilityStatus=" + availabilityStatus
				+ ", routes=" + routes + '}';
	}
}
