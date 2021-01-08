package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.setup.TestdatenAnleger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;

@Slf4j
@Testcontainers
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class JpaRepositorySollte
{
	private static final String PERSISTENCE_UNIT_NAME = "TestRepoPU";

	@Container
	private static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>(DockerImageName.parse("mariadb"))
		.withExposedPorts(3306)
		.withDatabaseName("trainharderTest")
		.withUsername("powerlifter")
		.withPassword("passwort");

	private static EntityManager entityManager;

	@BeforeAll
	static void setupClass()
	{
		erzeugeTestdaten();
	}

	@AfterAll
	static void resetClass()
	{
		schliesseEntityManager();
	}

	protected static EntityManager getEntityManager()
	{
		if (entityManager != null)
		{
			return entityManager;
		}

		var props = new HashMap<String, Object>();
		props.put("javax.persistence.jdbc.url", mariaDBContainer.getJdbcUrl());
		props.put("javax.persistence.jdbc.driver", mariaDBContainer.getDriverClassName());
		props.put("javax.persistence.jdbc.user", mariaDBContainer.getUsername());
		props.put("javax.persistence.jdbc.password", mariaDBContainer.getPassword());
		props.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		props.put("hibernate.show_sql", "true");

		entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, props).createEntityManager();
		return entityManager;
	}

	protected static void schliesseEntityManager()
	{
		if (entityManager.getTransaction().isActive())
		{
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManager = null;
	}

	protected static void erzeugeTestdaten()
	{
		getEntityManager();

		var transaction = entityManager.getTransaction();
		transaction.begin();
		new TestdatenAnleger().speichereTestdaten(entityManager, log::info);
		transaction.commit();
	}
}
