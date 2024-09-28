package de.justinharder.dietharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.repository.ZielRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class ZielJpaRepository extends JpaRepository<Ziel> implements ZielRepository
{
	public ZielJpaRepository()
	{
		super(Ziel.class);
	}

	@Override
	public List<Ziel> findeAlle(@NonNull ID benutzerId, @NonNull PaginationRequest<?> paginationRequest)
	{
		return entityManager.createQuery("""
					SELECT ziel
					FROM Ziel ziel
					WHERE ziel.benutzer.id = :benutzerId""",
				Ziel.class)
			.setParameter("benutzerId", benutzerId)
			.setFirstResult((paginationRequest.getPage() - 1) * paginationRequest.getPageSize())
			.setMaxResults(paginationRequest.getPageSize())
			.getResultList();
	}
}
