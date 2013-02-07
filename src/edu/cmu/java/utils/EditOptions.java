package edu.cmu.java.utils;

import java.util.Scanner;

import edu.cmu.java.model.Automotive;
import edu.cmu.java.throwable.InvalidOptionException;

public class EditOptions implements Runnable {
	static Automotive car;

	public EditOptions(Automotive car) {
		super();
		EditOptions.car = car;
	}

	@Override
	public void run() {
		{
			int classSelection = Integer.parseInt(readFromConsole("Edit properties of-\n1.Automotive\n2.OptionSet\n3.Options\nPlease enter 1 or 2 or 3 as per choice"));
			switch (classSelection) {
			case 1:
				// Edit Make,Model,Base Price - To be coded later
				break;
			case 2:	
				// Edit OptionSet
				int functionSelection = Integer.parseInt(readFromConsole("Select-\n1.Add\n2.Update\n3.Delete"));
				switch (functionSelection) {
				case 1:
					//Add Option Set - To be coded later
					break;
				case 2:
					//Update Option Set
					String oldName = readFromConsole("Enter existing OptionSet Name");
					String newName = readFromConsole("Enter new OptionSet Name");
					setOptionSetName(oldName, newName);
					break;
				case 3:
					// Delete Option Set
					String optionSetName = readFromConsole("Enter existing OptionSet Name");
					removeOptionSet(optionSetName);
					break;
				}
				break;
			case 3:	
				// Edit Options
				int optFunctionSelection = Integer.parseInt(readFromConsole("Select-\n1.Add\n2.Update\n3.Delete"));				
				switch (optFunctionSelection) {
				case 1:
					//Add Options - To be coded later
					break;
				case 2:
					// Update Options
					String optionSetName=readFromConsole("Enter OptionSet Name") ;		
					String optionName = readFromConsole("Enter Option Name");	
					int optionSelection = Integer.parseInt(readFromConsole("Update-\n1.Option Name2.Option Price"));
					switch (optionSelection) {
					case 1:						
						String newOptionName = readFromConsole("Enter new name");						
						setOptionName(optionSetName,optionName,newOptionName);
						break;
					case 2:
						int price = Integer.parseInt(readFromConsole("Enter Price"));					
						setOptionPrice(optionSetName,optionName,price);
						break;
					}
					break;
				case 3:
					// Delete Options
					String optSetName = readFromConsole("Enter OptionSet Name") ;		
					String optName = readFromConsole("Enter Option Name");	
					removeOption(optSetName, optName);
					break;
				}
			}			
			System.out.println("\n"+ Thread.currentThread().getName() + " Exiting..!!\n");
		}
	}

	public synchronized void setOptionSetName(String oldName, String newName){
		try {
			car.setOptionSetName(oldName, newName);
			System.out.println(Thread.currentThread().getName()+ ":\nOption Set " + oldName + " updated successfully to " + newName + "\n");
		} catch (InvalidOptionException e) {
			System.out.print("-By " + Thread.currentThread().getName());
			e.fixException();
		}
	}

	public void setOptionName(String optionSetName, String oldName, String newName){
		try {
			car.setOptionName(optionSetName, oldName, newName);
			System.out.println(Thread.currentThread().getName()+ ":\nOption " + oldName + " updated successfully to " + newName + "\n");
		} catch (InvalidOptionException e) {
			System.out.print("- By " + Thread.currentThread().getName());
			e.fixException();
		}
	}

	public void setOptionPrice(String optionSetName, String optionName, int price){
		try {
			car.setOptionPrice(optionSetName, optionName, price);
			System.out.println(Thread.currentThread().getName()+ ":\nOption Price " + optionName + " updated successfully to $" + price + "\n");
		} catch (InvalidOptionException e) {
			System.out.print("- By " + Thread.currentThread().getName());
			e.fixException();
		}
	}
	public synchronized void removeOptionSet(String optionSetName){
		try {
			car.removeOptionSet(optionSetName);
			System.out.println(Thread.currentThread().getName()+ ":\nOption Set " + optionSetName + " deleted successfully" + "\n");
		} catch (InvalidOptionException e) {
			System.out.print("- By" + Thread.currentThread().getName());
			e.fixException();
		}
	}
	public void removeOption(String optionSetName,String optionName){
		try {
			car.removeOption(optionSetName,optionName);
			System.out.println(Thread.currentThread().getName()+ ":\nOption " + optionName + " deleted successfully" + "\n");
		} catch (InvalidOptionException e) {
			System.out.print("-By " + Thread.currentThread().getName());
			e.fixException();
		}
	}
	public synchronized String readFromConsole(String question) {
		String threadIndication = "\n" + Thread.currentThread().getName() + "\n";
		System.out.println(threadIndication + question);
		Scanner read = new Scanner(System.in);
		return read.nextLine();
	}

}
