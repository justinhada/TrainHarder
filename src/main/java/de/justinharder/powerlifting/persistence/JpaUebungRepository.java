package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import de.justinharder.powerlifting.model.repository.UebungRepository;

public class JpaUebungRepository extends JpaRepository<Uebung> implements UebungRepository
{
	@Override
	public List<Uebung> ermittleAlle()
	{
		return super.ermittleAlle(Uebung.class);
	}

	@Override
	public List<Uebung> ermittleZuUebungsart(final Uebungsart uebungsart)
	{
		return ermittleAlle()
			.stream()
			.filter(uebung -> uebung.getUebungsart().equals(uebungsart))
			.collect(Collectors.toList());
	}

	@Override
	public List<Uebung> ermittleZuUebungskategorie(final Uebungskategorie uebungskategorie)
	{
		return ermittleAlle()
			.stream()
			.filter(uebung -> uebung.getUebungskategorie().equals(uebungskategorie))
			.collect(Collectors.toList());
	}

	@Override
	public Uebung ermittleZuId(final int id)
	{
		return super.ermittleZuId(Uebung.class, id);
	}

	@Override
	@Transactional
	public void erstelleUebung(final Uebung uebung)
	{
		super.erstelleEntitaet(uebung);
	}
}
