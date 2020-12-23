package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlleZuBenutzer(@NonNull Primaerschluessel benutzerId);

	Optional<Kraftwert> ermittleZuId(@NonNull Primaerschluessel id);

	Kraftwert speichereKraftwert(@NonNull Kraftwert kraftwert);
}
