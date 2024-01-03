package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.repository.KraftwertRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class KraftwertJpaRepository extends JpaRepository<Kraftwert> implements KraftwertRepository
{
	public KraftwertJpaRepository()
	{
		super(Kraftwert.class);
	}

	@Override
	public List<Kraftwert> findeAlleMitBenutzer(@NonNull ID benutzerId)
	{
		return entityManager.createQuery("""
					SELECT kraftwert
					FROM Kraftwert kraftwert
					WHERE kraftwert.benutzer.id = :benutzerId""",
				Kraftwert.class)
			.setParameter("benutzerId", benutzerId)
			.getResultList();
	}
}
