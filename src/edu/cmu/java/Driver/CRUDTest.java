package edu.cmu.java.Driver;

import java.util.*;

import edu.cmu.java.model.*;
import edu.cmu.java.throwable.*;
import edu.cmu.java.utils.*;

public class CRUDTest {

	public static void main(String[] args) {
		Automotive car = null;
		boolean error = false;
		String fileName = "CarModel1.txt";
		do {
			try {
				FileIO.saveObject(FileIO.readFile(fileName), "Automotive.dat");
				error = false;
			} catch (CustomException e) {
				error = true;
				fileName = (String) e.fixException();
			}
		} while (error);

		Object obj = FileIO.retrieveObject("Automotive.dat");
		if (obj instanceof Automotive) {
			car = (Automotive)obj;
		}
		car.printOptionsAvailable();
		String newline = "\n";

		//Testing Base Price - Cannot be negative
		car.setBasePrice(-60);
		System.out.println(car.getBasePrice());

		// Positive test - Adding an Option Set & Option
		car.addOptionSet("Wheels", 2);
		try {
			car.addOption("Wheels", "17-inch 8-spoke alloy wheels", 50);
			System.out.println(newline + car.getOptionSet("Wheels").toString());
		} catch (CustomException e) {
			e.fixException();
		}
		
		//Negative Test(Invalid Index) - Set an option
		try {
			car.setOption("Wheels", 4, "18-inch wheels", 20);
		} catch (CustomException e) {
			e.fixException();
		}
		
		//Negative Test(Invalid Option Set) - Set an option
		try {
			car.setOption("SafetyAccessories", 2, "Side Bags", 50);
		} catch (CustomException e) {
			e.fixException();
		}

		ListIterator<String> itOptSet = car.getOptionSetNamesIterator();
		System.out.println(newline);
		while (itOptSet.hasNext()) {
			System.out.println(itOptSet.next());
		}

		//Positive Tests - Set Option Price
		ListIterator<String> it;
		try {
			it = car.getOptionNamesIterator("Color");
			while (it.hasNext()) {
				car.setOptionPrice("Color", it.next(), 90);
			}
		} catch (InvalidOptionException e) {
			e.fixException();
		}

		try {
			// Positive Test - Get an Option Set
			System.out.println(newline + car.getOptionSet("Color").toString());
			
			// Negative Test(Invalid Option Set) - Get an Option Set
			System.out.println(newline
					+ car.getOptionPrice("Wheels", "15-inch wheels"));
		} catch (InvalidOptionException e) {
			e.fixException();
		}

		try {
			System.out.println(newline
					+ car.getOption("Brakes", "ABS").toString());
			
			//Positive Test - Remove an Option
			car.removeOption("Brakes", "ABS");
			System.out.println(newline
					+ car.getOption("Brakes", "ABS").toString());
		} catch (InvalidOptionException e) {
			e.fixException();
		}

		System.out.println(newline + car.hasOptionSet("Brakes"));

		// Positive Test - Remove an Option Set
		try {
			car.removeOptionSet("Brakes");
			car.setOptionSetName("Brakes", "Braking");
		} catch (InvalidOptionException e) {
			e.fixException();
		}

	}

}
