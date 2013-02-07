package edu.cmu.java.model;

import java.io.Serializable;
import java.util.*;
import edu.cmu.java.model.OptionSet.Option;
import edu.cmu.java.throwable.*;

@SuppressWarnings("serial")
public class Automotive implements Serializable {
	private String make;
	private String model;
	private ArrayList<OptionSet> configOptions;
	private int basePrice;
	private int finalPrice;

	public Automotive() {
		super();
	}

	public Automotive(String make, String model) {
		super();
		this.make = make;
		this.model = model;
	}

	public Automotive(String make, String model,
			ArrayList<OptionSet> configOptions, int basePrice) {
		super();
		this.make = make;
		this.model = model;
		this.configOptions = configOptions;
		this.basePrice = basePrice;
	}

	public Automotive(String make, String model, int basePrice) {
		super();
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
	}

	public synchronized void addOptionSet(String name, int count) {
		configOptions.add(new OptionSet(name, count));
	}

	public synchronized void addOption(String optionSetName, String name, int price)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			this.getOptionSet(optionSetName).addOption(name, price);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public void calculateFinalPrice() {
		finalPrice = basePrice;
		for (OptionSet optSet : configOptions) {
			try {
				finalPrice = finalPrice
						+ optSet.getOptionPrice(optSet.getOptionChoice());
			} catch (InvalidOptionException e) {
				e.fixException();
			}
		}
	}

	private int findOptionSet(String name) {
		for (OptionSet opt : configOptions) {
			if (opt.getName().equalsIgnoreCase(name)) {
				return configOptions.indexOf(opt);
			}
		}
		return -1;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public ArrayList<OptionSet> getConfigOptions() {
		return configOptions;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	/*
	 * Multiple Options can have the same name. e.g. Side Impact Bags & Power
	 * Moonroof both can have Options Present/Not present. Hence optionSetName
	 * is taken as additional parameter to distinguish.
	 */
	public synchronized Option getOption(String optionSetName, String optionName)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			return this.getOptionSet(optionSetName).getOption(optionName);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public synchronized String getOptionChoice(String optionSetName)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			return this.getOptionSet(optionSetName).getOptionChoice();
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public synchronized int getOptionPrice(String optionSetName, String optionName)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			return this.getOptionSet(optionSetName).getOptionPrice(optionName);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public synchronized OptionSet getOptionSet(String name) throws InvalidOptionException {
		int i = findOptionSet(name);
		if (i != -1)
			return configOptions.get(i);
		else
			throw new InvalidOptionException("Invalid OptionSet/Option");
	}

	public synchronized ListIterator<String> getOptionSetNamesIterator() {
		ArrayList<String> optionSetNames = new ArrayList<String>();
		for (OptionSet os : configOptions) {
			optionSetNames.add(os.getName());
		}
		return optionSetNames.listIterator();
	}

	public synchronized ListIterator<String> getOptionNamesIterator(String optionSetName)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			return this.getOptionSet(optionSetName).getOptionNamesIterator();
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}

	}

	public synchronized Boolean hasOptionSet(String name) {
		int i = findOptionSet(name);
		if (i != -1) {
			return true;
		} else {
			return false;
		}

	}

	public void printFinalConfig() {
		System.out.println("\nYour chosen configuration is:");
		System.out.println("\nMake:" + make);
		System.out.println("Model:" + model);
		System.out.println("Base Price:" + basePrice);
		for (OptionSet optSet : configOptions) {
			System.out.println(optSet.getName() + "=>"
					+ optSet.getOptionChoice());
		}
		System.out.println("Final Price:" + finalPrice);
	}

	public void printOptionsAvailable() {
		System.out.println("\nMake:" + make);
		System.out.println("Model:" + model);
		System.out.println("Base Price:$" + basePrice);
		System.out.println("\nConfiguration Options:");
		for (OptionSet optSet : configOptions) {
			System.out.println(optSet.toString());
		}
		System.out.println("-------xxxxx-------");
	}

	public synchronized void removeOption(String optionSetName, String optionName)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			this.getOptionSet(optionSetName).removeOption(optionName);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public synchronized void removeOptionSet(String name) throws InvalidOptionException {
		int i = this.findOptionSet(name);
		if (i != -1) {
			this.configOptions.remove(i);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public void setBasePrice(int basePrice) {
		if (basePrice >= 0) {
			this.basePrice = basePrice;
		} else {
			System.out.println("Base Price cannot be negative!");
		}

	}

	public void setConfigOptions(ArrayList<OptionSet> configOptions) {
		this.configOptions = configOptions;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public synchronized void setOption(String optionSetName, int index, String optionName,
			int Price) throws InvalidIndexException, InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			try {
				this.getOptionSet(optionSetName).setOption(index, optionName,
						Price);
			} catch (IndexOutOfBoundsException e) {
				throw new InvalidIndexException("Invalid Option Index");
			}

		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public synchronized void setOptionChoice(String optionSetName, String optionName)
			throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			if (this.getOptionSet(optionSetName).hasOption(optionName)) {
				this.getOptionSet(optionSetName).setOptionChoice(optionName);
			}
		} else {
			System.out.println("Hi");
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public void setOptionName(String optionSetName, String oldOptionName,
			String newOptionName) throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			this.getOptionSet(optionSetName).setOptionName(oldOptionName,
					newOptionName);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}

	}

	/*
	 * Multiple Options can have the same name. e.g. Side Impact Bags & Power
	 * Moonroof both can have Options Present/Not present. Hence optionSetName
	 * is taken as additional parameter to distinguish.
	 */
	public synchronized void setOptionPrice(String optionSetName, String optionName,int price) throws InvalidOptionException {
		if (this.hasOptionSet(optionSetName)) {
			this.getOptionSet(optionSetName).setOptionPrice(optionName, price);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}

	}

	public synchronized void setOptionSetName(String oldName, String newName)
			throws InvalidOptionException {
		if (this.hasOptionSet(oldName)) {
			this.getOptionSet(oldName).setName(newName);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public String toString() {
		StringBuffer output = new StringBuffer();
		String newline = "\n";

		output.append(newline + "Make:" + make + newline);
		output.append("Model:" + model + newline);
		output.append("Base Price:$" + basePrice + newline);
		output.append("\nConfiguration Options:" + newline);
		for (OptionSet optSet : configOptions) {
			output.append(optSet.toString() + newline);
		}
		return output.toString();
	}

}
