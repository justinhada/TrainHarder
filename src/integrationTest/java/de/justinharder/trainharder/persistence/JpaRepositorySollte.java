package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.setup.TestdatenAnleger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaRepositorySollte
{
	private static final String PERSISTENCE_UNIT_NAME = "TestRepoPU";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setupClass()
	{
		erzeugeTestdaten();
	}

	@AfterClass
	public static void resetClass()
	{
		schliesseEntityMananger();
	}

	protected static EntityManager erzeugeEntityManager()
	{
		if (entityManager == null)
		{
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

	protected static void schliesseEntityMananger()
	{
		if (entityManager.getTransaction().isActive())
		{
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
		entityManager = null;
		entityManagerFactory = null;
	}

	protected static void erzeugeTestdaten()
	{
		erzeugeEntityManager();

		var transaction = entityManager.getTransaction();
		transaction.begin();
		new TestdatenAnleger().speichereTestdaten(entityManager, System.out::println);
		transaction.commit();
	}
}
