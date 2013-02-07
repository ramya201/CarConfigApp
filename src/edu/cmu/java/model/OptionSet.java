package edu.cmu.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import edu.cmu.java.throwable.InvalidOptionException;

@SuppressWarnings("serial")
public class OptionSet implements Serializable {

	public class Option implements Serializable {

		private String name;
		private int price;

		public Option() {
			super();
		}

		public Option(String name, int price) {
			super();
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String toString() {
			StringBuffer output = new StringBuffer();
			output.append(name + ": $" + price);
			return output.toString();
		}
	}

	private String name;
	private ArrayList<Option> options;
	private String optionChoice;

	public OptionSet() {
		super();
	}

	public OptionSet(String name) {
		super();
		this.name = name;
	}

	public OptionSet(String name, int size) {
		super();
		this.name = name;
		options = new ArrayList<Option>(size);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public String getOptionChoice() {
		return optionChoice;
	}

	public void setOptionChoice(String optionChoice) {
		this.optionChoice = optionChoice;
	}

	public void addOption(String name, int price) {
		options.add(new Option(name, price));
	}

	public Option getOption(String name) throws InvalidOptionException {
		int i = findOption(name);
		if (i != -1) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return options.get(i);
		}
		else
			throw new InvalidOptionException("Invalid OptionSet/Option");
	}

	public ListIterator<String> getOptionNamesIterator() {
		ArrayList<String> optionNames = new ArrayList<String>();
		for (Option o : options) {
			optionNames.add(o.getName());
		}
		return optionNames.listIterator();
	}

	public void setOption(int i, String name, int price) {
		Option opt = new Option(name, price);
		options.set(i, opt);
	}

	private int findOption(String name) {
		for (Option opt : options) {
			if (opt.name.equalsIgnoreCase(name)) {
				return options.indexOf(opt);
			}
		}
		return -1;
	}

	public synchronized Boolean hasOption(String name) {
		int i = findOption(name);
		if (i != -1) {
			return true;
		} else {
			return false;
		}

	}

	public void setOptionName(String oldName, String newName)
			throws InvalidOptionException {
		if (this.hasOption(oldName)) {
			this.getOption(oldName).setName(newName);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public synchronized int getOptionPrice(String name) throws InvalidOptionException {
		int i = findOption(name);
		if (i != -1)
			return options.get(i).getPrice();
		else
			throw new InvalidOptionException("Invalid OptionSet/Option");
	}

	public synchronized void setOptionPrice(String name, int price)
			throws InvalidOptionException {
		int i = findOption(name);
		if (i != -1)
			options.get(i).setPrice(price);
		else
			throw new InvalidOptionException("Invalid OptionSet/Option");
	}

	public synchronized void removeOption(String name) throws InvalidOptionException {
		int i = this.findOption(name);
		if (i != -1) {
			this.options.remove(i);
		} else {
			throw new InvalidOptionException("Invalid OptionSet/Option");
		}
	}

	public String toString() {
		StringBuffer output = new StringBuffer();
		String newline = "\n";
		output.append(this.name + newline);
		for (OptionSet.Option o : this.getOptions()) {
			output.append(o.toString() + newline);
		}
		return output.toString();
	}

}
