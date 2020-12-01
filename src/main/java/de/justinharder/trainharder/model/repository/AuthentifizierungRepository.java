package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.Optional;
import java.util.UUID;

public interface AuthentifizierungRepository
{
	Optional<Authentifizierung> ermittleZuId(Primaerschluessel id);

	Optional<Authentifizierung> ermittleZuBenutzer(Primaerschluessel benutzerId);

	Optional<Authentifizierung> ermittleZuMail(String mail);

	Optional<Authentifizierung> ermittleZuBenutzername(String benutzername);

	Optional<Authentifizierung> ermittleZuResetUuid(UUID resetUuid);

	Authentifizierung speichereAuthentifizierung(Authentifizierung authentifizierung);
}
