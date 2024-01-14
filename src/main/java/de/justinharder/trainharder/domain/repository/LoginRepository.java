package de.justinharder.trainharder.domain.repository;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.repository.Repository;
import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import lombok.NonNull;

import java.util.Optional;

public interface LoginRepository extends Repository<Login>
{
	Optional<Login> findeMit(@NonNull ID benutzerId);

	Optional<Login> findeMit(@NonNull EMailAdresse eMailAdresse);

	Optional<Login> findeMit(@NonNull Benutzername benutzername);
}
