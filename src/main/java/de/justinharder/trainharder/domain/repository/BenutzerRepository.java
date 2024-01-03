package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface BenutzerRepository
{
	List<Benutzer> ermittleAlle();

	Optional<Benutzer> ermittleZuId(@NonNull Primaerschluessel id);

	Optional<Benutzer> ermittleZuAuthentifizierung(@NonNull Primaerschluessel authentifizierungId);

	Benutzer speichereBenutzer(@NonNull Benutzer benutzer);
}
