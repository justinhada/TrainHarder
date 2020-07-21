package de.justinharder.trainharder.model.repository;

import java.util.List;
import java.util.Optional;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlleZuBenutzer(final Primaerschluessel benutzerId);

	Optional<Kraftwert> ermittleZuId(final Primaerschluessel id);

	Kraftwert speichereKraftwert(final Kraftwert kraftwert);
}
