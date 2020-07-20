package de.justinharder.trainharder.model.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

public interface BelastungsfaktorRepository extends Serializable
{
	List<Belastungsfaktor> ermittleAlle();

	Optional<Belastungsfaktor> ermittleZuId(final Primaerschluessel id);

	Belastungsfaktor speichereBelastungsfaktor(final Belastungsfaktor belastungsfaktor);
}
