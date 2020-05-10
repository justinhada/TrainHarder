package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Entitaet;
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

	@Transactional
	protected void erstelleEntitaet(final T entitaet)
	{
		entityManager.persist(entitaet);
	}

	protected TypedQuery<T> erstelleQuery(final Class<T> clazz, final Map<String, Object> bedingungen)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(clazz);
		final var root = criteriaQuery.from(clazz);
		bedingungen.forEach((spalte, wert) -> criteriaQuery.select(root)
			.where(criteriaBuilder.equal(root.get(spalte), wert)));
		return entityManager.createQuery(criteriaQuery);
	}
}
