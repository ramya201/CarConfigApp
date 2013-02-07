package edu.cmu.java.throwable;

import java.util.Scanner;

@SuppressWarnings("serial")
public class BlankFileException extends CustomException {

	public BlankFileException() {
		// TODO Auto-generated constructor stub
	}

	public BlankFileException(String exceptionMsg) {
		super(exceptionMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printException() {
		System.out.println("\nError -- " + getExceptionMsg());
		System.out.println("File cannot be blank.Please specify a different file or modify existing file");

	}

	@Override
	public String fixException() {
		System.out.println("\nDo you want to specify a different file? [Enter Y or N]");
		Scanner read = new Scanner(System.in);
		String selection = read.nextLine();
		if (selection.equalsIgnoreCase("Y")) {
			System.out.println("Enter File Name:");
			String fileName = read.nextLine();
			return fileName;
		} else if (selection.equalsIgnoreCase("N")) {
			return null;
		} else {
			System.out.println("Invalid selection");
			return fixException();
		}

	}

}
