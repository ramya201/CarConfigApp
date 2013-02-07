package edu.cmu.java.throwable;

@SuppressWarnings("serial")
public class InvalidKeyException extends CustomException {

	public InvalidKeyException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidKeyException(String exceptionMsg) {
		super(exceptionMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printException() {
		System.out.println("Error--" + getExceptionMsg());
		System.out.println("Specified car option not available. Please choose correct Make and Model from available options");
	}

	@Override
	public Object fixException() {
		return null;
	}

}
