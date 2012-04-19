package be.butskri.exceptional;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExceptionalFacadeImplTest {

	private ExceptionalFacadeImpl exceptionalFacade;
	@Mock
	private ThrownExceptionRepository thrownExceptionRepositoryMock;
	@Mock
	private ThrownExceptionFactory thrownExceptionFactoryMock;

	@Test
	public void persistExceptionMet1ParameterDelegeertNaarFactoryEnRepository() {
		Exception exception = new Exception();
		ThrownException expectedThrownException = new ThrownExceptionBuilder().build();
		stub(thrownExceptionFactoryMock.create(exception)).toReturn(expectedThrownException);
		stub(thrownExceptionRepositoryMock.persist(expectedThrownException)).toReturn(expectedThrownException);

		ThrownException thrownException = exceptionalFacade.persistException(exception);
		assertEquals(expectedThrownException, thrownException);
		verify(thrownExceptionRepositoryMock).persist(thrownException);
	}

	@Test
	public void persistExceptionMet3ParametersDelegeertNaarFactoryEnRepository() {
		Exception exception = new Exception();
		Method method = getClass().getMethods()[0];
		Object[] args = new Object[] { new Object(), new Object() };
		ThrownException expectedThrownException = new ThrownExceptionBuilder().build();
		stub(thrownExceptionFactoryMock.create(exception, method, args)).toReturn(expectedThrownException);
		stub(thrownExceptionRepositoryMock.persist(expectedThrownException)).toReturn(expectedThrownException);

		ThrownException thrownException = exceptionalFacade.persistException(exception, method, args);
		assertEquals(expectedThrownException, thrownException);
		verify(thrownExceptionRepositoryMock).persist(thrownException);
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		exceptionalFacade = new ExceptionalFacadeImpl();
		exceptionalFacade.setThrownExceptionFactory(thrownExceptionFactoryMock);
		exceptionalFacade.setThrownExceptionRepository(thrownExceptionRepositoryMock);
	}
}
