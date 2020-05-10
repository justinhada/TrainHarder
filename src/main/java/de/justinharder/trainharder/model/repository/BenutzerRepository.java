package de.justinharder.trainharder.model.repository;

import java.util.List;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;

public interface BenutzerRepository
{
	List<Benutzer> ermittleAlle();

	Benutzer ermittleZuId(final int id);

	void erstelleBenutzer(final Benutzer benutzer);

	List<Benutzer> ermittleAlleZuNachname(final String nachname) throws BenutzerNichtGefundenException;
}
