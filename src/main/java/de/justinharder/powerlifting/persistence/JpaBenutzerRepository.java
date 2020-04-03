package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JpaBenutzerRepository extends JpaRepository<Benutzer> implements BenutzerRepository
{
	public JpaBenutzerRepository(final EntityManager entityManager)
	{
		super(entityManager);
	}

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
	public List<Benutzer> ermittleAlleZuNachname(final String nachname) throws BenutzerNichtGefundenException
	{
		try
		{
			return super.erstelleQuery(Benutzer.class, Map.of("nachname", nachname))
				.getResultList();
		}
		catch (final NoResultException e)
		{
			throw new BenutzerNichtGefundenException(
				"Es existiert kein Benutzer mit dem Nachnamen \"" + nachname + "\"!");
		}
	}
}
