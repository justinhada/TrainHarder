package de.justinharder.trainharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
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
	public Optional<Benutzer> findeMit(@NonNull ID loginId)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT login.benutzer
						FROM Login login
						WHERE login.id = :loginId""",
					Benutzer.class)
				.setParameter("loginId", loginId)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Benutzer> findeMit(@NonNull Benutzername benutzername)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT login.benutzer
						FROM Login login
						WHERE login.benutzername = :benutzername""",
					Benutzer.class)
				.setParameter("benutzername", benutzername)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}
}
