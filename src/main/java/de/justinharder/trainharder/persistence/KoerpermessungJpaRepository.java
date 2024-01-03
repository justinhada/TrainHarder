package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.repository.KoerpermessungRepository;
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
