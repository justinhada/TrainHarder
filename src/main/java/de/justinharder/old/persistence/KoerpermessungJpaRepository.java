package de.justinharder.old.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.old.domain.repository.KoerpermessungRepository;
import de.justinharder.old.domain.model.Koerpermessung;
import de.justinharder.base.domain.model.attribute.ID;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class KoerpermessungJpaRepository extends JpaRepository<Koerpermessung> implements KoerpermessungRepository
{
	public KoerpermessungJpaRepository()
	{
		super(Koerpermessung.class);
	}

	@Override
	public List<Koerpermessung> findeAlleMitBenutzer(@NonNull ID benutzerId)
	{
		return entityManager.createQuery("""
					SELECT benutzer.koerpermessungen
					FROM Benutzer benutzer
					WHERE benutzer.id = :benutzerId""",
				Koerpermessung.class)
			.setParameter("benutzerId", benutzerId)
			.getResultList();
	}
}
