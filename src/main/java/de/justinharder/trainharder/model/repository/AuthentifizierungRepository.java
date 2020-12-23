package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface AuthentifizierungRepository
{
	Optional<Authentifizierung> ermittleZuId(@NonNull Primaerschluessel id);

	Optional<Authentifizierung> ermittleZuBenutzer(@NonNull Primaerschluessel benutzerId);

	Optional<Authentifizierung> ermittleZuMail(@NonNull String mail);

	Optional<Authentifizierung> ermittleZuBenutzername(@NonNull String benutzername);

	Optional<Authentifizierung> ermittleZuResetUuid(@NonNull UUID resetUuid);

	Authentifizierung speichereAuthentifizierung(@NonNull Authentifizierung authentifizierung);
}
