package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.repository.UebungRepository;

public class JpaUebungRepository extends JpaRepository<Uebung> implements UebungRepository
{
	@Override
	public List<Uebung> ermittleAlle()
	{
		return super.ermittleAlle(Uebung.class);
	}

	@Override
	public List<Uebung> ermittleZuUebungsart(final Uebungsart uebungsart) throws UebungNichtGefundenException
	{
		try
		{
			return super.erstelleQuery(Uebung.class, Map.of("uebungsart", uebungsart))
				.getResultList();
		}
		catch (final NoResultException e)
		{
			throw new UebungNichtGefundenException(
				"Es konnten keine Übungen zur Übungsart \"" + uebungsart.name() + "\" gefunden werden!");
		}
	}

	@Override
	public List<Uebung> ermittleZuUebungskategorie(final Uebungskategorie uebungskategorie)
		throws UebungNichtGefundenException
	{
		try
		{
			return super.erstelleQuery(Uebung.class, Map.of("uebungskategorie", uebungskategorie))
				.getResultList();
		}
		catch (final NoResultException e)
		{
			throw new UebungNichtGefundenException(
				"Es konnten keine Übungen zur Übungsart \"" + uebungskategorie.name() + "\" gefunden werden!");
		}
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
