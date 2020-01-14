package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import de.justinharder.powerlifting.JpaRepository;
import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;

public class JpaKraftwertRepository extends JpaRepository<Kraftwert> implements KraftwertRepository
{
	@Override
	public List<Kraftwert> ermittleAlle()
	{
		return super.ermittleAlle(Kraftwert.class);
	}

	@Override
	public List<Kraftwert> ermittleAlleZuBenutzer(final Benutzer benutzer)
	{
		return ermittleAlle()
			.stream()
			.filter(kraftwert -> kraftwert.getBenutzer().equals(benutzer))
			.collect(Collectors.toList());
	}

	@Override
	public Kraftwert ermittleZuId(final int id)
	{
		return super.ermittleZuId(Kraftwert.class, id);
	}

	@Override
	@Transactional
	public void erstelleKraftwert(final Kraftwert kraftwert)
	{
		super.erstelleEntitaet(kraftwert);
	}
}
