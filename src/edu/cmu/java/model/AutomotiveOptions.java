package edu.cmu.java.model;

import java.io.Serializable;
import java.util.*;

import edu.cmu.java.throwable.InvalidKeyException;

@SuppressWarnings("serial")
public class AutomotiveOptions implements Serializable {
	private LinkedHashMap<String, Automotive> carOptions;
	private Automotive carChoice;

	public AutomotiveOptions() {
		super();
		carOptions = new LinkedHashMap<String, Automotive>();
		carChoice = new Automotive();
	}

	public AutomotiveOptions(LinkedHashMap<String, Automotive> carOptions,
			Automotive carChoice) {
		super();
		this.carOptions = carOptions;
		this.carChoice = carChoice;
	}

	public void setCarOptions(LinkedHashMap<String, Automotive> carOptions) {
		this.carOptions = carOptions;
	}

	public LinkedHashMap<String, Automotive> getCarOptions() {
		return carOptions;
	}

	public Automotive getCarOption(String key) throws InvalidKeyException {
		if (carOptions.containsKey(key)) {
			return carOptions.get(key);
		} else {
			throw new InvalidKeyException("Invalid Key");
		}
	}

	public void addCarOption(Automotive car) {
		this.carOptions.put(car.getMake() + "-" + car.getModel(), car);
	}

	public void removeCarOption(Automotive car) {
		carOptions.remove(car.getMake() + "-" + car.getModel());
	}

	public Automotive getCarChoice() {
		return carChoice;
	}

	public void setCarChoice(Automotive carChoice) {
		this.carChoice = carChoice;
	}

	public void printCarOptions() {
		for (Automotive car : carOptions.values()) {
			car.printOptionsAvailable();
		}
	}

}
