package be.butskri.exceptional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import be.butskri.commons.domain.DomainObject;

@Entity(name = "thrown_exception")
public class ThrownException implements DomainObject {

	@Id
	@GeneratedValue(generator = "ThrownExceptionSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "ThrownExceptionSeq", sequenceName = "THROWN_EXCEPTION_SEQ", allocationSize = 1)
	private Long id;
	@Version
	private Long version;

	@OneToOne
	@LazyToOne(LazyToOneOption.FALSE)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "FK_CALLED_METHOD")
	private CalledMethod calledMethod;

	@Column(name = "exception_type")
	private String type;
	@Column
	private String message;
	@Column
	private String stackTrace;

	ThrownException() {
	}

	ThrownException(CalledMethod calledMethod, String type, String message,
			String stackTrace) {
		this.calledMethod = calledMethod;
		this.type = type;
		this.message = message;
		this.stackTrace = stackTrace;
	}

	public CalledMethod getCalledMethod() {
		return calledMethod;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public String getType() {
		return type;
	}

	@Override
	public Long getVersion() {
		return version;
	}
}
