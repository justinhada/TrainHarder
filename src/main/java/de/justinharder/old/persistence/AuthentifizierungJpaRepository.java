package de.justinharder.old.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.old.domain.repository.AuthentifizierungRepository;
import de.justinharder.old.domain.model.Authentifizierung;
import de.justinharder.base.domain.model.attribute.ID;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.NoResultException;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

@Dependent
public class AuthentifizierungJpaRepository extends JpaRepository<Authentifizierung>
	implements AuthentifizierungRepository
{
	public AuthentifizierungJpaRepository()
	{
		super(Authentifizierung.class);
	}

	@Override
	public Optional<Authentifizierung> findeMitBenutzer(@NonNull ID benutzerId)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT benutzer.authentifizierung
						FROM Benutzer benutzer
						WHERE benutzer.id = :benutzerId""",
					Authentifizierung.class)
				.setParameter("benutzerId", benutzerId)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> findeMitMail(@NonNull String mail)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT authentifizierung
						FROM Authentifizierung authentifizierung
						WHERE authentifizierung.mail = :mail""",
					Authentifizierung.class)
				.setParameter("mail", mail)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> findeMitBenutzername(@NonNull String benutzername)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT authentifizierung
						FROM Authentifizierung authentifizierung
						WHERE authentifizierung.benutzername = :benutzername""",
					Authentifizierung.class)
				.setParameter("benutzername", benutzername)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> findeMitResetUuid(@NonNull UUID resetUuid)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT authentifizierung
						FROM Authentifizierung authentifizierung
						WHERE authentifizierung.resetUuid = :resetUuid""",
					Authentifizierung.class)
				.setParameter("resetUuid", resetUuid)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}
}
