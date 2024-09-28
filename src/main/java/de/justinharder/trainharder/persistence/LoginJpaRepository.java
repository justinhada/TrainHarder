package de.justinharder.trainharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.repository.LoginRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.NoResultException;
import lombok.NonNull;

import java.util.Optional;

@Dependent
public class LoginJpaRepository extends JpaRepository<Login> implements LoginRepository
{
	public LoginJpaRepository()
	{
		super(Login.class);
	}

	@Override
	public Optional<Login> findeMit(@NonNull ID benutzerId)
	{
		entityManager.clear();

		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT login
						FROM Login login
						WHERE login.benutzer.id = :benutzerId""",
					Login.class)
				.setParameter("benutzerId", benutzerId)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Login> findeMit(@NonNull EMailAdresse eMailAdresse)
	{
		entityManager.clear();

		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT login
						FROM Login login
						WHERE login.eMailAdresse = :eMailAdresse""",
					Login.class)
				.setParameter("eMailAdresse", eMailAdresse)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Login> findeMit(@NonNull Benutzername benutzername)
	{
		entityManager.clear();

		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT login
						FROM Login login
						WHERE login.benutzername = :benutzername""",
					Login.class)
				.setParameter("benutzername", benutzername)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}
}
