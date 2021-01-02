package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.Optional;

public interface BelastungsfaktorRepository
{
	Optional<Belastung> ermittleZuId(@NonNull Primaerschluessel id);

	Belastung speichereBelastungsfaktor(@NonNull Belastung belastung);
}
