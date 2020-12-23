package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.Optional;

public interface BelastungsfaktorRepository
{
	Optional<Belastungsfaktor> ermittleZuId(@NonNull Primaerschluessel id);

	Belastungsfaktor speichereBelastungsfaktor(@NonNull Belastungsfaktor belastungsfaktor);
}
