package edu.cmu.java.api;

import java.util.*;

import edu.cmu.java.model.*;
import edu.cmu.java.model.OptionSet.Option;
import edu.cmu.java.throwable.*;

public interface CanBuild {
	enum AttributeChoice {
		Make, Model, BasePrice, ConfigOptions, OptionSetNamesIterator
	}

	Automotive readFile(String fileName);

	void addOptionSet(Automotive car, String name, int size);

	boolean hasOption(Automotive car, String optionSetName, String optionName);

	boolean hasOptionSet(Automotive car, String optionSetName);

	Object get(Automotive car, AttributeChoice choice);

	Option getOption(Automotive car, String optionSetName, String optionName)
			throws InvalidOptionException;

	OptionSet getOptionSet(Automotive car, String name)
			throws InvalidOptionException;

	int getOptionPrice(Automotive car, String optionSetName, String optionName)
			throws InvalidOptionException;

	ListIterator<String> getOptionNamesIterator(Automotive car,
			String optionSetName) throws InvalidOptionException;

	void setOption(Automotive car, String optionSetName, int index,
			String name, int price) throws InvalidOptionException;

	void setBasePrice(Automotive car, int price);

	void setMake(Automotive car, String name);

	void setModel(Automotive car, String name);

	void setOptionName(Automotive car, String optionSetName,
			String optionOldName, String optionNewName)
					throws InvalidOptionException;

	void setOptionPrice(Automotive car, String optionSetName,
			String optionName, int price) throws InvalidOptionException;

	void setOptionSetName(Automotive car, String optionSetName, String name)
			throws InvalidOptionException;

	void removeOption(Automotive car, String optionSetName, String optionName)
			throws InvalidOptionException;

	void removeOptionSet(Automotive car, String optionSetName)
			throws InvalidOptionException;

	void print(Automotive car);

}
