package de.justinharder.powerlifting.model.repository;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;

public interface UebungRepository
{
	List<Uebung> ermittleAlle();

	List<Uebung> ermittleZuUebungsart(final Uebungsart uebungsart);

	List<Uebung> ermittleZuUebungskategorie(final Uebungskategorie uebungskategorie);

	Uebung ermittleZuId(final int id);

	void erstelleUebung(final Uebung uebung);
}
