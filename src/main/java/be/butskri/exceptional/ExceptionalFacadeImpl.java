package be.butskri.exceptional;

import java.lang.reflect.Method;

public class ExceptionalFacadeImpl implements ExceptionalFacade {

	private ThrownExceptionFactory thrownExceptionFactory;
	private ThrownExceptionRepository thrownExceptionRepository;

	@Override
	public ThrownException persistException(Exception exception) {
		ThrownException thrownException = thrownExceptionFactory.create(exception);
		return thrownExceptionRepository.persist(thrownException);
	}

	@Override
	public ThrownException persistException(Exception exception, Method method, Object[] args) {
		ThrownException thrownException = thrownExceptionFactory.create(exception, method, args);
		return thrownExceptionRepository.persist(thrownException);
	}

	public void setThrownExceptionFactory(ThrownExceptionFactory thrownExceptionFactory) {
		this.thrownExceptionFactory = thrownExceptionFactory;
	}

	public void setThrownExceptionRepository(ThrownExceptionRepository thrownExceptionRepository) {
		this.thrownExceptionRepository = thrownExceptionRepository;
	}

}
