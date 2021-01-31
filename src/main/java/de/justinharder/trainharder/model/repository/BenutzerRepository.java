package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
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