package de.justinharder.trainharder.model.repository;

import java.util.Optional;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

public interface BelastungsfaktorRepository
{
	Optional<Belastungsfaktor> ermittleZuId(final Primaerschluessel id);

	Belastungsfaktor speichereBelastungsfaktor(final Belastungsfaktor belastungsfaktor);
}
