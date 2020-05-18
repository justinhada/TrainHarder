package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Entitaet;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
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

	protected Optional<T> ermittleZuId(final Class<T> clazz, final Primaerschluessel id)
	{
		return Optional.ofNullable(entityManager.find(clazz, id));
	}

	@Transactional
	protected T speichereEntitaet(final Class<T> clazz, final T entitaet)
	{
		if (ermittleZuId(clazz, entitaet.getPrimaerschluessel()).isPresent())
		{
			return entityManager.merge(entitaet);
		}
		entityManager.persist(entitaet);
		return entitaet;
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
