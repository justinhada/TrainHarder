package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.List;
import java.util.Optional;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlleZuBenutzer(final Primaerschluessel benutzerId);

	Optional<Kraftwert> ermittleZuId(final Primaerschluessel id);

	Kraftwert speichereKraftwert(final Kraftwert kraftwert);
}
