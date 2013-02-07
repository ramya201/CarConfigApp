package edu.cmu.java.utils;

import java.io.*;
import java.util.*;

import edu.cmu.java.model.*;
import edu.cmu.java.throwable.*;

public class FileIO {


	public static Automotive readFile(String fileName) throws InvalidFileNameException,InvalidFileFormatException, BlankFileException, InvalidInputException, InvalidFileTypeException, BasicInfoMissingException {
		Automotive car = new Automotive();
		ArrayList<OptionSet> optSetList = new ArrayList<OptionSet>();
		Scanner s = null;
		boolean isBlank = true;

		try {
			FileReader file = new FileReader(fileName);

			checkFileType(fileName);

			BufferedReader buff = new BufferedReader(file);
			s = new Scanner(buff);
			ArrayList<String> lines = new ArrayList<String>();

			while (s.hasNextLine()) {
				isBlank = false;
				String line = s.nextLine();

				if (line.contains(":")) {
					lines.add(line.trim());
				}

			}
			if (isBlank == true) {
				throw new BlankFileException("Blank File");
			} else if (lines.size() == 0) {
				throw new InvalidFileFormatException("Invalid File Format");
			}
			car.setMake(lines.get(0).split(":")[1].trim());
			car.setModel(lines.get(1).split(":")[1].trim());
			car.setBasePrice(Integer.parseInt(lines.get(2).split(":")[1].trim()));

			ListIterator<String> li = lines.listIterator(3);

			while (li.hasNext()) {
				OptionSet os = new OptionSet(li.next().split(":")[0].trim());

				ArrayList<OptionSet.Option> optList = new ArrayList<OptionSet.Option>();
				while (li.hasNext()) {
					String nextLine = li.next();
					if (!nextLine.endsWith(":")) {
						OptionSet.Option o = os.new Option(
								nextLine.split(":")[0].trim(),
								Integer.parseInt(nextLine.split(":")[1].trim()));
						optList.add(o);
					} else {
						li.previous();
						break;
					}
				}
				os.setOptions(optList);
				optSetList.add(os);
			}

		} catch (FileNotFoundException e) {
			throw new InvalidFileNameException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new InvalidInputException(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			throw new BasicInfoMissingException(e.getMessage());
		}
		s.close();
		car.setConfigOptions(optSetList);
		return car;
	}

	public static void saveObject(Object a, String destFile) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destFile));
			out.writeObject(a);
			out.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public static Object retrieveObject(String file) {
		Object obj = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			obj = in.readObject();
			in.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return obj;
	}

	public static void checkFileType(String fileName) throws InvalidFileTypeException {
		String extn = fileName.substring(fileName.indexOf(".")+1);
		if (!extn.equalsIgnoreCase("txt")) {
			throw new InvalidFileTypeException("Invalid File Type");
		}
	}
}
