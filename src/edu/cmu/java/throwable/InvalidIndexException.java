package edu.cmu.java.throwable;

@SuppressWarnings("serial")
public class InvalidIndexException extends CustomException {

	public InvalidIndexException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidIndexException(String exceptionMsg) {
		super(exceptionMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printException() {
		System.out.println("\nError -- " + getExceptionMsg());
	}

	@Override
	public Object fixException() {
		// TODO Auto-generated method stub
		return null;
	}

}
