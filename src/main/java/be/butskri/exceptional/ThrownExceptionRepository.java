package be.butskri.exceptional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.butskri.commons.domain.RepositoryImpl;

public class ThrownExceptionRepository extends RepositoryImpl<ThrownException> {

	@PersistenceContext(unitName = "pu_exceptional")
	private EntityManager entityManager;

	public ThrownExceptionRepository() {
		super(ThrownException.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
