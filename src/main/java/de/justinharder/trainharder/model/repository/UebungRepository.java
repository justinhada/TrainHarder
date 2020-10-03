package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;

import java.util.List;
import java.util.Optional;

public interface UebungRepository
{
	List<Uebung> ermittleAlle();

	List<Uebung> ermittleAlleZuUebungsart(final Uebungsart uebungsart);

	List<Uebung> ermittleAlleZuUebungskategorie(final Uebungskategorie uebungskategorie);

	Optional<Uebung> ermittleZuId(final Primaerschluessel id);

	Uebung speichereUebung(final Uebung uebung);
}
