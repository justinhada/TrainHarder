package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.List;
import java.util.Optional;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlleZuBenutzer(Primaerschluessel benutzerId);

	Optional<Kraftwert> ermittleZuId(Primaerschluessel id);

	Kraftwert speichereKraftwert(Kraftwert kraftwert);
}
