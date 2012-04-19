package be.butskri.exceptional;

import java.lang.reflect.Method;

public class DummyClass {
	
	public static final String DUMMY_METHOD_NAME = "dummyMethod";
	public static final Method DUMMY_METHOD = DummyClass.class.getMethods()[0];

	public String dummyMethod(Comparable<?> comparable, Object obj) {
		return null;
	}
}
