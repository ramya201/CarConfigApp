package edu.cmu.java.Driver;

import edu.cmu.java.model.*;
import edu.cmu.java.throwable.*;
import edu.cmu.java.users.EndUser;
import edu.cmu.java.utils.FileIO;

public class CarConfigurationTest {

	public static void main(String[] args) {
		Automotive car = null;

		String[] fileNames = { "CarModel1.txt", "CarModel2.txt","CarModel3.txt", "CarModel4.txt", "CarModel5.txt" };
		AutomotiveOptions cars = new AutomotiveOptions();

		for (int i = 0; i < fileNames.length; i++) {
			try {
				FileIO.saveObject(FileIO.readFile(fileNames[i]),"Automotive.dat");
				Object obj = FileIO.retrieveObject("Automotive.dat");
				if (obj instanceof Automotive) {
					car = (Automotive)obj;
				}
				cars.addCarOption(car);

			} catch (CustomException e) {
				fileNames[i] = (String) e.fixException();
				if (fileNames[i] != null) {
					i--;
				}
			}

		}
		EndUser user = new EndUser();
		user.printCarOptions(cars);

		try {
			user.setCarChoice(cars, "Mercedes-Benz-E350 Sedan");
		} catch (InvalidKeyException e) {
			e.fixException();
		}
		user.printOptionsAvailable(cars.getCarChoice());
		try {
			user.configure(cars.getCarChoice(), "Color-Exterior",
					"Pearl Beige Metallic");
			user.configure(cars.getCarChoice(), "Color-Interior", "Ash MB-Tex");
			user.configure(cars.getCarChoice(), "Wheels",
					"18-inch split 5-spoke alloy wheels");
			user.configure(cars.getCarChoice(), "Seats",
					"Split folding rear seats");
			user.configure(cars.getCarChoice(), "Safety Accessories",
					"PARKTRONIC");
		} catch (InvalidOptionException e) {
			e.fixException();
		}
		user.calculateFinalPrice(cars.getCarChoice());
		user.printFinalConfig(cars.getCarChoice());
	}

}
