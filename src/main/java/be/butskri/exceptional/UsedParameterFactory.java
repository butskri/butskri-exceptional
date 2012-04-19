package be.butskri.exceptional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import be.butskri.commons.util.ObjectUtils;

public class UsedParameterFactory {

	private ObjectUtils objectUtils;

	public void setToStringUtil(ObjectUtils objectUtils) {
		this.objectUtils = objectUtils;
	}

	public List<UsedParameter> create(Method method, Object[] args) {
		List<UsedParameter> result = new ArrayList<UsedParameter>();

		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				result.add(createUsedParameter(i, method.getParameterTypes()[i],
						args[i]));
			}
		}
		return result;
	}

	private UsedParameter createUsedParameter(int order, Class<?> clazz,
			Object objectValue) {
		UsedParameter result = new UsedParameter(order, clazz.getName(),
				objectUtils.convertToString(objectValue));
		return result;
	}

}
