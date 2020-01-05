package de.justinharder.powerlifting.model.repository;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlle();

	List<Kraftwert> ermittleAlleZuBenutzer(final Benutzer benutzer);

	Kraftwert ermittleZuId(final int id);

	void erstelleKraftwert(final Kraftwert kraftwert);
}
