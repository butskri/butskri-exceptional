package be.butskri.exceptional;

import java.util.List;

import be.butskri.exceptional.CalledMethod;
import be.butskri.exceptional.UsedParameter;

public class CalledMethodBuilder {

	private static final String NAME = "name";
	private static final String DECLARING_CLASS = "declaringClass";

	private String declaringClass = DECLARING_CLASS;
	private String name = NAME;
	private List<String> parameterTypes;
	private List<UsedParameter> usedParameters;

	public CalledMethod build() {
		return new CalledMethod(declaringClass, name, parameterTypes,
				usedParameters);
	}

}
