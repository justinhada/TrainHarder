package de.justinharder.old.domain.repository;

import de.justinharder.base.domain.repository.Repository;
import de.justinharder.old.domain.model.Benutzer;
import de.justinharder.base.domain.model.attribute.ID;
import lombok.NonNull;

import java.util.Optional;

public interface BenutzerRepository extends Repository<Benutzer>
{
	Optional<Benutzer> findeMitAuthentifizierung(@NonNull ID authentifizierungId);
}
