package be.butskri.exceptional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.lang.reflect.Method;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

@RunWith(BlockJUnit4ClassRunner.class)
public class ThrownExceptionFactoryTest {

	private static final String EXCEPTION_MESSAGE = "Exception Message";

	private ThrownExceptionFactory factory;
	@Mock
	private CalledMethodFactory calledMethodFactory;
	@Mock
	private CalledMethod calledMethod;
	private Method method = DummyClass.DUMMY_METHOD;

	@Test
	public void createMaaktThrownExceptionAan() {
		Exception exception = new RuntimeException(EXCEPTION_MESSAGE);
		Object[] args = new Object[] { new Object(), new Object() };

		stub(calledMethodFactory.create(method, args)).toReturn(calledMethod);

		ThrownException thrownException = factory.create(exception, method,
				args);

		assertEquals(calledMethod, thrownException.getCalledMethod());
		assertEquals(EXCEPTION_MESSAGE, thrownException.getMessage());
		assertEquals(ExceptionUtils.getFullStackTrace(exception),
				thrownException.getStackTrace());
		assertEquals("java.lang.RuntimeException", thrownException.getType());
	}

	@Test
	public void createMet1ParameterMaaktThrownExceptionZonderCalledMethodAan() {
		Exception exception = new RuntimeException(EXCEPTION_MESSAGE);

		ThrownException thrownException = factory.create(exception);

		assertNull(thrownException.getCalledMethod());
		assertEquals(EXCEPTION_MESSAGE, thrownException.getMessage());
		assertEquals(ExceptionUtils.getFullStackTrace(exception),
				thrownException.getStackTrace());
		assertEquals("java.lang.RuntimeException", thrownException.getType());

		verifyNoMoreInteractions(calledMethodFactory);
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		factory = new ThrownExceptionFactory();
		factory.setCalledMethodFactory(calledMethodFactory);
	}

}
