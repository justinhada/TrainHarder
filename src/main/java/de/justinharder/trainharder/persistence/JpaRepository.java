package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Entitaet;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@NoArgsConstructor
@AllArgsConstructor
public class JpaRepository<T extends Entitaet>
{
	@Setter
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

	protected T speichereEntitaet(final Class<T> clazz, final T entitaet)
	{
		return ermittleZuId(clazz, entitaet.getPrimaerschluessel())
			.map(ungenutzt -> aktualisiereEntitaet(entitaet))
			.orElseGet(erstelleEntitaet(entitaet));
	}

	private T aktualisiereEntitaet(final T entitaet)
	{
		return entityManager.merge(entitaet);
	}

	private Supplier<? extends T> erstelleEntitaet(final T entitaet)
	{
		return () ->
		{
			entityManager.persist(entitaet);
			return entitaet;
		};
	}
}
