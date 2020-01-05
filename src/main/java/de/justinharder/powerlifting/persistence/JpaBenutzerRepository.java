package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import de.justinharder.powerlifting.JpaRepository;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;

public class JpaBenutzerRepository extends JpaRepository implements BenutzerRepository
{
	@Override
	public List<Benutzer> ermittleAlle()
	{
		return super.ermittleAlle(Benutzer.class);
	}

	@Override
	public Benutzer ermittleZuId(final int id)
	{
		return super.ermittleZuId(Benutzer.class, id);
	}

	@Override
	@Transactional
	public void erstelleBenutzer(final Benutzer benutzer)
	{
		super.erstelleEntitaet(benutzer);
	}

	@Override
	public List<Benutzer> ermittleAlleZuNachname(final String nachname)
	{
		return super.ermittleAlle(Benutzer.class)
			.stream()
			.filter(benutzer -> benutzer.getNachname().equals(nachname))
			.collect(Collectors.toList());
	}
}
