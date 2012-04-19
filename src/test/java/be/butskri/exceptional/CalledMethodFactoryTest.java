package be.butskri.exceptional;

import static be.butskri.exceptional.DummyClass.DUMMY_METHOD;
import static be.butskri.exceptional.DummyClass.DUMMY_METHOD_NAME;
import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.google.common.collect.Lists;

@RunWith(BlockJUnit4ClassRunner.class)
public class CalledMethodFactoryTest {

	private CalledMethodFactory factory;
	@Mock
	private UsedParameterFactory usedParameterFactory;

	@Test
	public void createMaaktCalledMethodObjectMetDeclaringClassCalledMethodEnUsedParameters() {
		Object[] args = new Object[] { new Long(0), new String() };
		List<UsedParameter> usedParameterList = Lists
				.newArrayList(mock(UsedParameter.class));

		stub(usedParameterFactory.create(DUMMY_METHOD, args)).toReturn(
				usedParameterList);

		CalledMethod calledMethod = factory.create(DUMMY_METHOD, args);

		assertEquals(DummyClass.class.getName(), calledMethod
				.getDeclaringClass());
		assertEquals(DUMMY_METHOD_NAME, calledMethod.getName());
		assertEquals(usedParameterList, calledMethod.getUsedParameters());
	}

	@Test
	public void createSetOokParameterTypes() {
		Object[] args = new Object[] { new Long(0), new String() };
		List<UsedParameter> usedParameterList = Lists
				.newArrayList(mock(UsedParameter.class));

		stub(usedParameterFactory.create(DUMMY_METHOD, args)).toReturn(
				usedParameterList);

		CalledMethod calledMethod = factory.create(DUMMY_METHOD, args);

		assertThat(calledMethod.getParameterTypes()).containsExactly(
				"java.lang.Comparable", "java.lang.Object");
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		factory = new CalledMethodFactory();
		factory.setUsedParameterFactory(usedParameterFactory);
	}

}
