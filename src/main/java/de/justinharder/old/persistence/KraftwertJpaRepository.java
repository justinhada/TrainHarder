package de.justinharder.old.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.old.domain.repository.KraftwertRepository;
import de.justinharder.old.domain.model.Kraftwert;
import de.justinharder.base.domain.model.attribute.ID;
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
