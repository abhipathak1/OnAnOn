package com.OnAnOn.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import com.OnAnOn.entities.Driver;
import com.OnAnOn.entities.Passenger;

public class FileExists {

	public static Map<Integer, Driver> DriverFile() {

		Map<Integer, Driver> dFile = null;

		File f = new File("Driver.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {

				dFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(dFile);
				oos.flush();
				oos.close();
				return dFile;

			} else {

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				dFile = (Map<Integer, Driver>) ois.readObject();
				ois.close();
				return dFile;

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return dFile;
	}

	public static Map<Integer, Passenger> PassengerFile() {

		Map<Integer, Passenger> pFile = null;

		File f = new File("Passenger.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {

				pFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(pFile);
				oos.flush();
				oos.close();
				return pFile;

			} else {

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				pFile = (Map<Integer, Passenger>) ois.readObject();
				ois.close();
				return pFile;

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}
		return pFile;

	}

	public static void saveDriverToFile(Map<Integer, Driver> driver) {
		try (FileOutputStream fileOut = new FileOutputStream("Driver.ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			objectOut.writeObject(driver);
			System.out.println("Drivers saved to file: Driver.ser");
		} catch (IOException e) {
			System.out.println("Error saving Driver to file: " + e.getMessage());
		}
	}

	public static void savePassengerToFile(Map<Integer, Passenger> passenger) {
		try (FileOutputStream fileOut = new FileOutputStream("Passenger.ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			objectOut.writeObject(passenger);
			System.out.println("Passenger saved to file: Passenger.ser");
		} catch (IOException e) {
			System.out.println("Error saving Passenger to file: " + e.getMessage());
		}
	}
}
