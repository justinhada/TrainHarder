package de.justinharder.base.persistence;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class JpaRepository<T extends Entitaet> implements Repository<T>
{
	@PersistenceContext
	protected EntityManager entityManager;

	private final Class<T> entitaet;

	@Override
	public List<T> findeAlle()
	{
		entityManager.clear();
		var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entitaet);
		return entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(entitaet)))
			.getResultList();
	}

	@Override
	public List<T> findeAlle(@NonNull Long page, @NonNull Long pageSize)
	{
		entityManager.clear();
		var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entitaet);
		return entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(entitaet)))
			.setFirstResult(Math.toIntExact((page - 1) * pageSize))
			.setMaxResults(Math.toIntExact(pageSize))
			.getResultList();
	}

	@Override
	public Optional<T> finde(@NonNull ID id)
	{
		return Optional.ofNullable(entityManager.find(entitaet, id));
	}

	@Override
	public void speichere(@NonNull T entitaet)
	{
		entityManager.persist(entitaet);
	}

	@Override
	public void loesche(@NonNull T entitaet)
	{
		entityManager.remove(entitaet);
	}
}
