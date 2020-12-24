package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Entitaet;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@NoArgsConstructor
public abstract class JpaRepository<T extends Entitaet>
{
	@PersistenceContext
	protected EntityManager entityManager;

	public void setEntityManager(@NonNull EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	protected List<T> ermittleAlle(Class<T> clazz)
	{
		var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
		criteriaQuery.select(criteriaQuery.from(clazz));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	protected Optional<T> ermittleZuId(Class<T> clazz, Primaerschluessel id)
	{
		return Optional.ofNullable(entityManager.find(clazz, id));
	}

	protected T speichereEntitaet(Class<T> clazz, T entitaet)
	{
		return ermittleZuId(clazz, entitaet.getPrimaerschluessel())
			.map(ungenutzt -> aktualisiereEntitaet(entitaet))
			.orElseGet(erstelleEntitaet(entitaet));
	}

	private T aktualisiereEntitaet(T entitaet)
	{
		return entityManager.merge(entitaet);
	}

	private Supplier<? extends T> erstelleEntitaet(T entitaet)
	{
		return () ->
		{
			entityManager.persist(entitaet);
			return entitaet;
		};
	}
}
