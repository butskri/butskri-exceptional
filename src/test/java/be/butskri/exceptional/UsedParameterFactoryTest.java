package be.butskri.exceptional;

import static be.butskri.exceptional.DummyClass.*;
import static org.mockito.Mockito.*;

import java.util.List;

import junit.framework.TestCase;

import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.util.ObjectUtils;
import be.butskri.exceptional.UsedParameter;
import be.butskri.exceptional.UsedParameterFactory;

public class UsedParameterFactoryTest extends TestCase{

	private static final String STRING1 = "STRING1";
	private static final String STRING2 = "STRING2";
	
	private UsedParameterFactory factory;
	@Mock private ObjectUtils objectUtils;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockitoAnnotations.initMocks(this);
		
		factory = new UsedParameterFactory();
		factory.setToStringUtil(objectUtils);
	}
	
	public void testCreate() {
		Object obj1 = new Integer(15);
		Object obj2 = "dit is mijn test string";
		Object[] args = new Object[] { obj1, obj2 };
		
		stub(objectUtils.convertToString(obj1)).toReturn(STRING1);
		stub(objectUtils.convertToString(obj2)).toReturn(STRING2);
		
		List<UsedParameter> usedParameters = factory.create(DUMMY_METHOD, args);
		assertEquals(0, usedParameters.get(0).getOrder());
		assertEquals(Comparable.class.getName(), usedParameters.get(0).getType());
		assertEquals(STRING1, usedParameters.get(0).getValue());
		
		assertEquals(1, usedParameters.get(1).getOrder());
		assertEquals(Object.class.getName(), usedParameters.get(1).getType());
		assertEquals(STRING2, usedParameters.get(1).getValue());
	}
	
	public void testCreateWithNullArgsReturnsEmptyUsedParameterList() {
		List<UsedParameter> usedParameters = factory.create(DUMMY_METHOD, null);
		assertEquals(0, usedParameters.size());
	}
}
