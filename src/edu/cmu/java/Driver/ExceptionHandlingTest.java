package edu.cmu.java.Driver;

import edu.cmu.java.model.*;
import edu.cmu.java.utils.*;
import edu.cmu.java.throwable.*;

public class ExceptionHandlingTest {

	public static void main(String[] args) {
		Automotive car = null;
		String[] fileNames = { "ClassDiagram.jpg", "CarModel6.txt",
				"CarModel7.txt", "CarModel8.txt", "CarModel9.txt" };

		for (int i = 0; i < fileNames.length; i++) {
			try {
				FileIO.saveObject(FileIO.readFile(fileNames[i]),
						"Automotive.dat");
				Object obj = FileIO.retrieveObject("Automotive.dat");
				if (obj instanceof Automotive) {
					car = (Automotive)obj;
				}
				System.out.println(car.toString());
			} catch (CustomException e) {
				fileNames[i] = (String) e.fixException();
				if (fileNames[i] != null) {
					i--;
				}
			}

		}

	}
}
