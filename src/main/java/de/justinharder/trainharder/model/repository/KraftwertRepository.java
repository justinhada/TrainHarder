package de.justinharder.trainharder.model.repository;

import java.util.List;

import de.justinharder.trainharder.model.domain.Kraftwert;

public interface KraftwertRepository
{
	List<Kraftwert> ermittleAlle();

	List<Kraftwert> ermittleAlleZuBenutzer(final int benutzerId);

	Kraftwert ermittleZuId(final int id);

	void erstelleKraftwert(final Kraftwert kraftwert);
}
