package de.justinharder.powerlifting.persistence;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;

public class JpaBenutzerRepository implements BenutzerRepository
{
	@Override
	public List<Benutzer> ermittleAlle()
	{
		return null;
	}

	@Override
	public Benutzer ermittleZuId(final int id)
	{
		return null;
	}
}
