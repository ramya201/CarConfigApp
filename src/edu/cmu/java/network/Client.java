package edu.cmu.java.network;

import java.io.*;
import java.net.*;
import java.util.*;

import edu.cmu.java.model.*;
import edu.cmu.java.throwable.*;
import edu.cmu.java.utils.FileIO;

public class Client implements Runnable {
	private Socket clientSock;
	private InetAddress address;
	private int portNo;
	private ObjectOutputStream oos = null;
	private ObjectInputStream iis = null;
	private boolean end = false;

	public Client(InetAddress address, int portNo) {
		super();
		this.address = address;
		this.portNo = portNo;
	}

	public void run() {

		Automotive car = null;

		try {
			clientSock = new Socket(address, portNo);
			oos = new ObjectOutputStream(clientSock.getOutputStream());
			iis = new ObjectInputStream(clientSock.getInputStream());

			while (!end) {
				int selection = Integer.parseInt(readFromConsole("Select-\n1. Upload a car configuration\n2. Configure a car\nPlease enter 1 or 2"));
				if (selection==1){
					
					//Take File Name as input
					String fileName = readFromConsole("Enter file name");
					
					//Read File and create Automotive object
					car = FileIO.readFile(fileName);
					
					//Send serialized Automotive object to Server
					sendOutput(car);
					
					//Read acknowledgment from Server
					System.out.println("\nServer:" + iis.readObject());
					
					//Continue or exit?
					exitPoll();
				}else if (selection == 2){
					
					//Request model options from Server
					sendOutput("Get Options");
					
					//Read available model options sent by Server
					System.out.println("\nServer:Available Models" + iis.readObject());
					
					//Take selected model name as input
					String model = readFromConsole("Enter selected Model Name");
					
					//Send selected model name to server
					sendOutput("Model-" + model);
					
					//Read configuration options for Model selected from Server
					System.out.println("\nServer:");
					Object input = iis.readObject();
					
					if (input instanceof Automotive) {
						car = (Automotive)input;
						System.out.println("Configurable Options");
						car.printOptionsAvailable();
					}
					configureCar(car);
					exitPoll();
				}else {
					System.out.println("Invalid Selection");
					exitPoll();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				clientSock.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void sendOutput(Object obj) {
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void configureCar(Automotive car) {
		System.out.println("Configure the Car-\nPlease enter option choice.");
		Scanner read = new Scanner(System.in);
		ListIterator<String> it = car.getOptionSetNamesIterator();
		while (it.hasNext()) {
			String optionSetName = it.next().toString();
			System.out.println(optionSetName + ":");
			try {
				car.setOptionChoice(optionSetName, read.nextLine());
			} catch (InvalidOptionException e) {
				e.fixException();
			}
		}
		car.calculateFinalPrice();
		System.out.println("\nFinal Configuration-");
		car.printFinalConfig();
	}
	
	public String readFromConsole(String question) {
		System.out.println(question);
		Scanner read = new Scanner(System.in);
		return read.nextLine();
	}
	
	public void exitPoll() {
		String proceed = readFromConsole("Do you want to continue?Enter Y or N");
		if (!proceed.equalsIgnoreCase("Y")) {
			end = true;
			if (!proceed.equalsIgnoreCase("N")) {
				System.out.println("Invalid Selection");
			}
			System.out.println("Exiting..");
		}
	}
}
