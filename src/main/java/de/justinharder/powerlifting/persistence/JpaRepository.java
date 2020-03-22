package de.justinharder.powerlifting.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.justinharder.powerlifting.model.domain.Entitaet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JpaRepository<T extends Entitaet>
{
	@PersistenceContext
	protected EntityManager entityManager;

	protected List<T> ermittleAlle(final Class<T> clazz)
	{
		final var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
		criteriaQuery.select(criteriaQuery.from(clazz));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	protected T ermittleZuId(final Class<T> clazz, final int id)
	{
		return entityManager.find(clazz, id);
	}

	protected void erstelleEntitaet(final T entitaet)
	{
		entityManager.persist(entitaet);
	}
}
