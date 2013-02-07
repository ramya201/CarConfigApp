package edu.cmu.java.throwable;

@SuppressWarnings("serial")
public class InvalidFileFormatException extends CustomException {

	public InvalidFileFormatException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidFileFormatException(String exceptionMsg) {
		super(exceptionMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printException() {
		System.out.println("\nError -- " + getExceptionMsg());
		System.out.println("Please adhere to prescribed input format.Use ':' as field separator in the input file.");

	}

	@Override
	public Object fixException() {
		// TODO Auto-generated method stub
		return null;
	}

}
