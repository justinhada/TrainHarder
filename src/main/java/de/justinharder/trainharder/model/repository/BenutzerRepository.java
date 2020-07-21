package de.justinharder.trainharder.model.repository;

import java.util.List;
import java.util.Optional;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

public interface BenutzerRepository
{
	List<Benutzer> ermittleAlle();

	Optional<Benutzer> ermittleZuId(final Primaerschluessel id);

	Optional<Benutzer> ermittleZuAuthentifizierung(final Primaerschluessel authentifizierungId);

	Benutzer speichereBenutzer(final Benutzer benutzer);
}
