package be.butskri.exceptional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import be.butskri.commons.domain.DomainObject;

@Entity(name = "USED_PARAMETER")
public class UsedParameter implements DomainObject {

	@Id
	@GeneratedValue(generator = "UsedParameterSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "UsedParameterSeq", sequenceName = "USED_PARAMETER_SEQ", allocationSize = 1)
	private Long id;
	@Version
	private Long version;

	private int order;
	private String type;
	private String value;

	public UsedParameter() {
	}

	public UsedParameter(int order, String type, String value) {
		this.order = order;
		this.type = type;
		this.value = value;
	}

	@Override
	public Long getId() {
		return id;
	}

	public int getOrder() {
		return order;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	@Override
	public Long getVersion() {
		return version;
	}

}
