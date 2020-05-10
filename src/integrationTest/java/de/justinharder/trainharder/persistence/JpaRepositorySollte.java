package de.justinharder.trainharder.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.justinharder.trainharder.setup.TestdatenAnleger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JpaRepositorySollte
{
	private static final String PERSISTENCE_UNIT_NAME = "TestRepoPU";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static final EntityManager erzeugeEntityManager()
	{
		if (entityManager == null)
		{
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

	public static final void schliesseEntityMananger()
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

	public static void erzeugeTestdaten()
	{
		erzeugeEntityManager();

		final var transaction = entityManager.getTransaction();
		transaction.begin();
		new TestdatenAnleger().speichereTestdaten(entityManager, System.out::println);
		transaction.commit();
	}
}
