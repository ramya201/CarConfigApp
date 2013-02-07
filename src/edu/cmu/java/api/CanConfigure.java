package edu.cmu.java.api;

import java.util.*;
import edu.cmu.java.model.*;
import edu.cmu.java.throwable.InvalidKeyException;
import edu.cmu.java.throwable.InvalidOptionException;

public interface CanConfigure {

	LinkedHashMap<String, Automotive> getCarOptions(AutomotiveOptions autoOpt);

	void printCarOptions(AutomotiveOptions autoOpt);

	void setCarChoice(AutomotiveOptions autoOpt, String key)
			throws InvalidKeyException;

	void printOptionsAvailable(Automotive car);

	void configure(Automotive car, String optionSetName, String optionName)
			throws InvalidOptionException;

	void printFinalConfig(Automotive car);

	void calculateFinalPrice(Automotive car);
}
