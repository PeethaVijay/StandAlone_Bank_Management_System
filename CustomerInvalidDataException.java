package org.bank.exception;

public class CustomerInvalidDataException extends RuntimeException {

	private String exceptionmsg;

	public CustomerInvalidDataException() {
		super();
	}

	public CustomerInvalidDataException(String exceptionmsg) {
		super();
		this.exceptionmsg = exceptionmsg;
	}

	public String getExceptionmsg() {
		return exceptionmsg;
	}

	public void setExceptionmsg(String exceptionmsg) {
		this.exceptionmsg = exceptionmsg;
	}

	@Override
	public String toString() {
		return "CustomerInvalidDataException [exceptionmsg=" + exceptionmsg + "]";
	}
	
}
