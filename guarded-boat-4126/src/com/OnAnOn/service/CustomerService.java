package com.OnAnOn.service;

import java.util.Map;

import com.OnAnOn.entities.Passenger;
import com.OnAnOn.exception.InvalidDetailsException;
import com.OnAnOn.utility.FileExists;

public class CustomerService {
    private Map<Integer, Passenger> passengerData;

    public CustomerService(Map<Integer, Passenger> passengerData) {
        this.passengerData = passengerData;
    }

    public boolean signup(String firstName, String lastName, String address, String mobileNo, String username,
                          String password) throws InvalidDetailsException {
        validateUsername(username);

        int passengerId = passengerData.size() + 1;
        Passenger passenger = new Passenger(firstName, lastName, address, mobileNo, username, password);
        passengerData.put(passengerId, passenger);
        FileExists.savePassengerToFile(passengerData);
        System.out.println("Passenger signed up successfully!");

        return true;
    }

    public boolean login(String username, String password) {
        for (Passenger passenger : passengerData.values()) {
            if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void validateUsername(String username) throws InvalidDetailsException {
        for (Passenger passenger : passengerData.values()) {
            if (passenger.getUsername().equals(username)) {
                throw new InvalidDetailsException("Username already exists. Please choose a different username.");
            }
        }
    }

    public Map<Integer, Passenger> getPassengerData() {
        return passengerData;
    }

    public Passenger getPassengerByUsername(String username) {
        for (Passenger passenger : passengerData.values()) {
            if (passenger.getUsername().equals(username)) {
                return passenger;
            }
        }
        return null;
    }
}
