package de.justinharder.trainharder.domain.repository;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.repository.Repository;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Benutzername;
import lombok.NonNull;

import java.util.Optional;

public interface BenutzerRepository extends Repository<Benutzer>
{
	Optional<Benutzer> findeMit(@NonNull ID loginId);

	Optional<Benutzer> findeMit(@NonNull Benutzername benutzername);
}
