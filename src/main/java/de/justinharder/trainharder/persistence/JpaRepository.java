package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
