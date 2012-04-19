package be.butskri.exceptional;

import be.butskri.exceptional.CalledMethod;
import be.butskri.exceptional.ThrownException;

public class ThrownExceptionBuilder {

	private static final String TYPE = "type";
	private static final String MESSAGE = "message";
	private static final String STACKTRACE = "stacktrace";
	private String stacktrace = STACKTRACE;
	private String message = MESSAGE;
	private String type = TYPE;
	private CalledMethod calledMethod = new CalledMethodBuilder().build();

	public ThrownExceptionBuilder() {
	}

	public ThrownException build() {
		return new ThrownException(calledMethod, type, message, stacktrace);
	}
}
