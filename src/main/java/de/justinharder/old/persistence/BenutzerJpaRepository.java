package de.justinharder.old.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.old.domain.model.Benutzer;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.NoResultException;
import lombok.NonNull;

import java.util.Optional;

@Dependent
public class BenutzerJpaRepository extends JpaRepository<Benutzer> implements BenutzerRepository
{
	public BenutzerJpaRepository()
	{
		super(Benutzer.class);
	}

	@Override
	public Optional<Benutzer> findeMitAuthentifizierung(@NonNull ID authentifizierungId)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT benutzer
						FROM Benutzer benutzer
						WHERE benutzer.authentifizierung.id = :authentifizierungId""",
					Benutzer.class)
				.setParameter("authentifizierungId", authentifizierungId)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}
}
