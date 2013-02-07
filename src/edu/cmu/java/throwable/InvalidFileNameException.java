package edu.cmu.java.throwable;

import java.util.Scanner;

@SuppressWarnings("serial")
public class InvalidFileNameException extends CustomException {
	

	public InvalidFileNameException() {
		super();	
	}


	public InvalidFileNameException(String exceptionMsg) {
		super(exceptionMsg);
	}

	@Override
	public void printException() {
		System.out.println("\nError -- " + getExceptionMsg());
		System.out.println("Cannot find the file specified.Please enter correct filename.");
	}

	@Override
	public String fixException() {
		System.out.println("Enter File Name");
		Scanner read = new Scanner(System.in);
		String fileName = read.nextLine();
		return fileName;
	}

}
