package be.butskri.exceptional;

import java.lang.reflect.Method;

import org.apache.commons.lang.exception.ExceptionUtils;

public class ThrownExceptionFactory {

	private CalledMethodFactory calledMethodFactory;

	public ThrownException create(Exception exception) {
		return create(exception, null, null);
	}

	public ThrownException create(Exception exception, Method method,
			Object[] args) {
		ThrownException result = new ThrownException(
				calledMethod(method, args), getType(exception), exception
						.getMessage(), ExceptionUtils
						.getFullStackTrace(exception));

		return result;
	}

	public void setCalledMethodFactory(CalledMethodFactory calledMethodFactory) {
		this.calledMethodFactory = calledMethodFactory;
	}

	private CalledMethod calledMethod(Method method, Object[] args) {
		if (method == null) {
			return null;
		}
		return calledMethodFactory.create(method, args);
	}

	private String getType(Exception exception) {
		return exception.getClass().getName();
	}

}
