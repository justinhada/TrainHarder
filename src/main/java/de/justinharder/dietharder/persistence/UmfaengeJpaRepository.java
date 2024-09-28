package de.justinharder.dietharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.dietharder.domain.model.Umfaenge;
import de.justinharder.dietharder.domain.repository.UmfaengeRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class UmfaengeJpaRepository extends JpaRepository<Umfaenge> implements UmfaengeRepository
{
	public UmfaengeJpaRepository()
	{
		super(Umfaenge.class);
	}

	@Override
	public List<Umfaenge> findeAlle(@NonNull ID benutzerId, @NonNull PaginationRequest<?> paginationRequest)
	{
		entityManager.clear();

		return entityManager.createQuery("""
					SELECT umfaenge
					FROM Umfaenge umfaenge
					WHERE umfaenge.benutzer.id = :benutzerId""",
				Umfaenge.class)
			.setParameter("benutzerId", benutzerId)
			.setFirstResult((paginationRequest.getPage() - 1) * paginationRequest.getPageSize())
			.setMaxResults(paginationRequest.getPageSize())
			.getResultList();
	}
}
