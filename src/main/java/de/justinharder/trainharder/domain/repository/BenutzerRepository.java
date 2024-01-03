package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import lombok.NonNull;

import java.util.Optional;

public interface BenutzerRepository extends Repository<Benutzer>
{
	Optional<Benutzer> findeMitAuthentifizierung(@NonNull ID authentifizierungId);
}
