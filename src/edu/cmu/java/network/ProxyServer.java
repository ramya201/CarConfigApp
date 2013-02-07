package edu.cmu.java.network;

import java.net.*;
import java.io.*;

import edu.cmu.java.model.*;
import edu.cmu.java.throwable.InvalidKeyException;
import edu.cmu.java.utils.FileIO;

public class ProxyServer implements Runnable {

	private Socket proxySock;
	private static AutomotiveOptions cars = new AutomotiveOptions();

	public ProxyServer(Socket proxySock) {
		this.proxySock = proxySock;
		System.out.println("ProxyServer " + Thread.currentThread().getName() + " is handling client..");

		// Read serialized Model options
		Object obj = FileIO.retrieveObject("AutomotiveOptions.dat");
		if (obj instanceof AutomotiveOptions) {
			cars = (AutomotiveOptions)obj;
		}
	}

	public void run() {	

		try {
			ObjectOutputStream oos = new ObjectOutputStream(proxySock.getOutputStream());
			ObjectInputStream iis = new ObjectInputStream(proxySock.getInputStream());				
			while (true) {		
				oos.writeObject(processInput(iis.readObject()));
				oos.flush();
			}
		}
		catch (Exception e){
			//Serialize existing Model options
			FileIO.saveObject(cars, "AutomotiveOptions.dat");

			System.out.println(Thread.currentThread().getName() + "Exiting...");
			System.exit(1);
		}finally {
			try {
				proxySock.close();
			} catch (IOException e) {					
				e.printStackTrace();
			}				
		}
	}

	public Object processInput(Object obj) {
		System.out.println("ProxyServer received object:\n" + obj.toString());
		Object output = null;

		if (obj instanceof String) {
			if (obj.equals("Get Options")) {
				output = cars.getCarOptions().keySet().toString();
			}else if (obj.toString().startsWith("Model-")) {
				try {
					output = cars.getCarOption(obj.toString().substring(6));
				} catch (InvalidKeyException e) {
					e.fixException();
				}
			}
		}else if (obj instanceof Automotive) {
			cars.addCarOption((Automotive)obj);
			output = ("Car Model saved successfully\n\nDetails:"+ obj.toString());			
		}
		return output; 
	}
}
