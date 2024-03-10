package de.justinharder.trainharder.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.repository.RegistrierungRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.NoResultException;
import lombok.NonNull;

import java.util.Optional;

@Dependent
public class RegistrierungJpaRepository extends JpaRepository<Registrierung> implements RegistrierungRepository
{
	public RegistrierungJpaRepository()
	{
		super(Registrierung.class);
	}

	@Override
	public Optional<Registrierung> findeMit(@NonNull EMailAdresse eMailAdresse)
	{
		try
		{
			return Optional.of(entityManager.createQuery("""
						SELECT registrierung
						FROM Registrierung registrierung
						WHERE registrierung.eMailAdresse = :eMailAdresse""",
					Registrierung.class)
				.setParameter("eMailAdresse", eMailAdresse)
				.getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}
}
