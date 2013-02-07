package edu.cmu.java.Driver;

import edu.cmu.java.model.Automotive;
import edu.cmu.java.throwable.*;
import edu.cmu.java.utils.*;

public class MultithreadingTest {

	public static void main(String[] args) {
		Automotive car = null;
		try {
			FileIO.saveObject(FileIO.readFile("CarModel1.txt"), "Automotive.dat");
		} catch (CustomException e) {			
			e.fixException();
		}
		Object obj = FileIO.retrieveObject("Automotive.dat");
		if (obj instanceof Automotive) {
			car = (Automotive)obj;
		}
		EditOptions editor = new EditOptions(car);
		Thread t1 = new Thread(editor);
		Thread t2 = new Thread(editor);
		t1.start();
		t2.start();

	}

}
