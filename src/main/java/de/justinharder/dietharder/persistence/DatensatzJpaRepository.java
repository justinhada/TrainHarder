package de.justinharder.dietharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.dietharder.domain.model.Datensatz;
import de.justinharder.dietharder.domain.repository.DatensatzRepository;
import lombok.NonNull;

import java.util.List;

public abstract class DatensatzJpaRepository<T extends Datensatz>
	extends JpaRepository<T>
	implements DatensatzRepository<T>
{
	protected DatensatzJpaRepository(Class<T> entitaet)
	{
		super(entitaet);
	}

	@Override
	public List<T> findeAlle(@NonNull ID benutzerId, @NonNull PaginationRequest<?> paginationRequest)
	{
		entityManager.clear();

		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(entitaet);
		var root = criteriaQuery.from(entitaet);
		return entityManager.createQuery(criteriaQuery.select(root)
				.where(criteriaBuilder.equal(root.join("benutzer").get("id"), benutzerId)))
			.setFirstResult((paginationRequest.getPage() - 1) * paginationRequest.getPageSize())
			.setMaxResults(paginationRequest.getPageSize())
			.getResultList();
	}

	@Override
	public Integer zaehleAlle(@NonNull ID benutzerId)
	{
		entityManager.clear();

		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Long.class);
		var root = criteriaQuery.from(entitaet);
		return Math.toIntExact(entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root))
				.where(criteriaBuilder.equal(root.join("benutzer").get("id"), benutzerId)))
			.getSingleResult());
	}
}
