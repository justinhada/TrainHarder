package de.justinharder.base.persistence;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.repository.Repository;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
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
	public List<T> findeAlle(@NonNull PaginationRequest<?> paginationRequest)
	{
		entityManager.clear();

		var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entitaet);
		return entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(entitaet)))
			.setFirstResult((paginationRequest.getPage() - 1) * paginationRequest.getPageSize())
			.setMaxResults(paginationRequest.getPageSize())
			.getResultList();
	}

	@Override
	public Integer zaehleAlle()
	{
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Long.class);
		return Math.toIntExact(
			entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entitaet))))
				.getSingleResult());
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
