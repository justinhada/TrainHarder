package de.justinharder.trainharder.domain.repository;

import de.justinharder.base.domain.repository.Repository;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import lombok.NonNull;

import java.util.Optional;

public interface RegistrierungRepository extends Repository<Registrierung>
{
	Optional<Registrierung> findeMit(@NonNull EMailAdresse eMailAdresse);
}
