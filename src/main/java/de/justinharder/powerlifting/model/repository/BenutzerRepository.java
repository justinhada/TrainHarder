package de.justinharder.powerlifting.model.repository;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Benutzer;

public interface BenutzerRepository
{
	List<Benutzer> ermittleAlle();

	Benutzer ermittleZuId(final int id);
}
