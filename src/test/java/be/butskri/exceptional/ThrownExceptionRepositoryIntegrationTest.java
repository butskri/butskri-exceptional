package be.butskri.exceptional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.butskri.commons.domain.DomainObject;
import be.butskri.commons.types.Identifier;
import be.butskri.exceptional.ThrownException;
import be.butskri.exceptional.ThrownExceptionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:exceptional-application-config.xml","classpath:be/butskri/exceptional/db/exceptional-db-testcontext.xml","classpath:test-config.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "exceptionalTransactionManager")
@Transactional
public class ThrownExceptionRepositoryIntegrationTest {

	@Resource
	private ThrownExceptionRepository repository;

	@PersistenceContext(unitName = "pu_exceptional")
	private EntityManager entityManager;

	@Resource(name = "exceptionalDataSource")
	private DataSource dataSource;

	@Test
	public void persistSlaatThrownExceptionOp() {
		ThrownException thrownException = new ThrownExceptionBuilder().build();
		thrownException = repository.persist(thrownException);
		entityManager.flush();
		entityManager.clear();

		ThrownException found = repository.lookUpById(new Identifier(
				thrownException.getId()));
		assertNotNull(thrownException.getId());
		assertEquals(thrownException.getId(), found.getId());
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	protected void persistAndClear(DomainObject... domainObjects) {
		for (DomainObject domainObject : domainObjects) {
			entityManager.persist(domainObject);
		}
		entityManager.clear();
	}
}
