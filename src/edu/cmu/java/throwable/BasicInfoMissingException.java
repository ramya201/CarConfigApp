package edu.cmu.java.throwable;

@SuppressWarnings("serial")
public class BasicInfoMissingException extends CustomException {

	public BasicInfoMissingException() {
	}

	public BasicInfoMissingException(String exceptionMsg) {
		super(exceptionMsg);
	}

	@Override
	public void printException() {
		System.out.println("\nError -- " + getExceptionMsg());
		System.out.println("Basic Info such as Make, Model or Base Price is either missing or improperly formatted");
	}

	@Override
	public Object fixException() {
		return null;
	}

}
