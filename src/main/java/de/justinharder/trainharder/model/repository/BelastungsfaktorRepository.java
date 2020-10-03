package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.Optional;

public interface BelastungsfaktorRepository
{
	Optional<Belastungsfaktor> ermittleZuId(final Primaerschluessel id);

	Belastungsfaktor speichereBelastungsfaktor(final Belastungsfaktor belastungsfaktor);
}
