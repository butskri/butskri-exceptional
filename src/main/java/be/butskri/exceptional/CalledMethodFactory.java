package be.butskri.exceptional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CalledMethodFactory {

	private UsedParameterFactory usedParameterFactory;

	public CalledMethod create(Method method, Object[] args) {
		List<UsedParameter> usedParameters = usedParameterFactory.create(
				method, args);
		CalledMethod result = new CalledMethod(method.getDeclaringClass()
				.getName(), method.getName(), getParameterTypes(method),
				usedParameters);
		return result;
	}

	private List<String> getParameterTypes(Method method) {
		List<String> result = new ArrayList<String>();
		for (Class<?> parameterType : method.getParameterTypes()) {
			result.add(parameterType.getName());
		}
		return result;
	}

	public void setUsedParameterFactory(
			UsedParameterFactory usedParameterFactory) {
		this.usedParameterFactory = usedParameterFactory;
	}

}
