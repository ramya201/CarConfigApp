package edu.cmu.java.throwable;

@SuppressWarnings("serial")
public abstract class CustomException extends Exception {
	private String exceptionMsg;

	public CustomException() {
		super();
		printException();
	}

	/**
	 * @param errorMsg
	 */
	public CustomException(String exceptionMsg) {
		super();
		this.setExceptionMsg(exceptionMsg);
		printException();
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public abstract void printException();

	public abstract Object fixException();

}
