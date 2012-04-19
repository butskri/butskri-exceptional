package be.butskri.exceptional;

import java.lang.reflect.Method;

public interface ExceptionalFacade {

	public ThrownException persistException(Exception exception);

	public ThrownException persistException(Exception exception, Method method,
			Object[] args);
}
