package de.justinharder.trainharder.model.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;

public interface UebungRepository extends Serializable
{
	List<Uebung> ermittleAlle();

	List<Uebung> ermittleZuUebungsart(final Uebungsart uebungsart) throws UebungNichtGefundenException;

	List<Uebung> ermittleZuUebungskategorie(final Uebungskategorie uebungskategorie)
		throws UebungNichtGefundenException;

	Optional<Uebung> ermittleZuId(final Primaerschluessel id);

	Uebung speichereUebung(final Uebung uebung);
}
