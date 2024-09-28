package de.justinharder.dietharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.dietharder.domain.model.Messung;
import de.justinharder.dietharder.domain.repository.MessungRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class MessungJpaRepository extends JpaRepository<Messung> implements MessungRepository
{
	public MessungJpaRepository()
	{
		super(Messung.class);
	}

	@Override
	public List<Messung> findeAlle(@NonNull ID benutzerId, @NonNull PaginationRequest<?> paginationRequest)
	{
		entityManager.clear();

		return entityManager.createQuery("""
					SELECT messung
					FROM Messung messung
					WHERE messung.benutzer.id = :benutzerId""",
				Messung.class)
			.setParameter("benutzerId", benutzerId)
			.setFirstResult((paginationRequest.getPage() - 1) * paginationRequest.getPageSize())
			.setMaxResults(paginationRequest.getPageSize())
			.getResultList();
	}
}
