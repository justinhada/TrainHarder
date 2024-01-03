package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlleZuBenutzer(@NonNull Primaerschluessel benutzerId);

	Optional<Kraftwert> ermittleZuId(@NonNull Primaerschluessel id);

	Kraftwert speichereKraftwert(@NonNull Kraftwert kraftwert);
}
