package be.butskri.exceptional;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;

import be.butskri.commons.domain.DomainObject;

@Entity(name = "called_method")
public class CalledMethod implements DomainObject {

	@Id
	@GeneratedValue(generator = "CalledMethodSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CalledMethodSeq", sequenceName = "CALLED_METHOD_SEQ", allocationSize = 1)
	private Long id;
	@Version
	private Long version;

	@Column(name = "declaring_class")
	private String declaringClass;
	private String name;
	@Column(name = "parameter_types")
	private String parameterTypes;
	@OneToMany(targetEntity = UsedParameter.class)
	@JoinColumn(name = "fk_called_method", nullable = false)
	@Cascade( { org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<UsedParameter> usedParameters;

	CalledMethod() {
	}

	CalledMethod(String declaringClass, String name,
			List<String> parameterTypes, List<UsedParameter> usedParameters) {
		this.declaringClass = declaringClass;
		this.name = name;
		setParameterTypes(parameterTypes);
		this.usedParameters = usedParameters;
	}

	public String getDeclaringClass() {
		return declaringClass;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getParameterTypes() {
		return Arrays.asList(StringUtils.split(parameterTypes, ","));
	}

	public List<UsedParameter> getUsedParameters() {
		return usedParameters;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	private void setParameterTypes(List<String> parameterTypes) {
		this.parameterTypes = StringUtils.join(parameterTypes, ",");
	}

}
