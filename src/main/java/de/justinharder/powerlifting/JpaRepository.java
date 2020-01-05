package de.justinharder.powerlifting;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.justinharder.powerlifting.model.Entitaet;

public class JpaRepository
{
	@PersistenceContext
	protected EntityManager entityManager;

	protected <T extends Entitaet> List<T> ermittleAlle(final Class<T> clazz)
	{
		final var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
		criteriaQuery.select(criteriaQuery.from(clazz));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	protected <T extends Entitaet> T ermittleZuId(final Class<T> clazz, final int id)
	{
		return entityManager.find(clazz, id);
	}

	protected <T extends Entitaet> void erstelleEntitaet(final T entitaet)
	{
		entityManager.persist(entitaet);
	}
}
