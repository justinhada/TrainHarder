package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.List;
import java.util.Optional;

public interface BenutzerRepository
{
	List<Benutzer> ermittleAlle();

	Optional<Benutzer> ermittleZuId(Primaerschluessel id);

	Optional<Benutzer> ermittleZuAuthentifizierung(Primaerschluessel authentifizierungId);

	Benutzer speichereBenutzer(Benutzer benutzer);
}
