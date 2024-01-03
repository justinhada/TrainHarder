package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface AuthentifizierungRepository extends Repository<Authentifizierung>
{
	Optional<Authentifizierung> findeMitBenutzer(@NonNull ID benutzerId);

	Optional<Authentifizierung> findeMitMail(@NonNull String mail);

	Optional<Authentifizierung> findeMitBenutzername(@NonNull String benutzername);

	Optional<Authentifizierung> findeMitResetUuid(@NonNull UUID resetUuid);
}
