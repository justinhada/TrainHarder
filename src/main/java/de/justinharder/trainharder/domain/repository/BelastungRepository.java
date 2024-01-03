package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import lombok.NonNull;

import java.util.Optional;

public interface BelastungsfaktorRepository
{
	Optional<Belastung> ermittleZuId(@NonNull ID id);

	Belastung speichereBelastungsfaktor(@NonNull Belastung belastung);
}
