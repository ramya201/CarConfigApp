package edu.cmu.java.throwable;


import java.util.*;

@SuppressWarnings("serial")
public class InvalidFileTypeException extends CustomException {

	public InvalidFileTypeException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidFileTypeException(String exceptionMsg) {
		super(exceptionMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printException() {
		System.out.println("\nError -- " + getExceptionMsg());
		System.out.println("Only .txt files are acceptable. Please specify new file in .txt format");

	}

	@Override
	public String fixException() {
		System.out.println("Enter File Name:");
		Scanner read = new Scanner(System.in);
		String fileName = read.nextLine();
		return fileName;
		
	}

}
