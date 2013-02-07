package edu.cmu.java.users;

import java.util.*;

import edu.cmu.java.model.*;
import edu.cmu.java.throwable.InvalidKeyException;
import edu.cmu.java.throwable.InvalidOptionException;

import edu.cmu.java.api.*;

public class EndUser implements CanConfigure {

	@Override
	public LinkedHashMap<String, Automotive> getCarOptions(
			AutomotiveOptions autoOpt) {
		autoOpt.printCarOptions();
		return autoOpt.getCarOptions();
	}

	@Override
	public void setCarChoice(AutomotiveOptions autoOpt, String key) throws InvalidKeyException {
		autoOpt.setCarChoice(autoOpt.getCarOption(key));
	}

	@Override
	public void printOptionsAvailable(Automotive car) {
		car.printOptionsAvailable();
	}

	@Override
	public void configure(Automotive car,String optionSetName, String optionName) throws InvalidOptionException {
		car.setOptionChoice(optionSetName, optionName);
	}

	@Override
	public void calculateFinalPrice(Automotive car) {
		car.calculateFinalPrice();
	}

	@Override
	public void printFinalConfig(Automotive car) {
		car.printFinalConfig();
	}

	@Override
	public void printCarOptions(AutomotiveOptions autoOpt) {
		autoOpt.printCarOptions();

	}

}
