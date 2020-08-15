package de.justinharder.trainharder.model.repository;

import java.util.Optional;
import java.util.UUID;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

public interface AuthentifizierungRepository
{
	Optional<Authentifizierung> ermittleZuId(final Primaerschluessel id);

	Optional<Authentifizierung> ermittleZuBenutzer(final Primaerschluessel benutzerId);

	Optional<Authentifizierung> ermittleZuMail(final String mail);

	Optional<Authentifizierung> ermittleZuBenutzername(final String benutzername);

	Optional<Authentifizierung> ermittleZuResetUuid(final UUID resetUuid);

	Authentifizierung speichereAuthentifizierung(final Authentifizierung authentifizierung);
}
