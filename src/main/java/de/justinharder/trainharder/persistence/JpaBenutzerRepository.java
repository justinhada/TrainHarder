package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JpaBenutzerRepository extends JpaRepository<Benutzer> implements BenutzerRepository
{
	private static final long serialVersionUID = 3832865610872106637L;

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
	public Optional<Benutzer> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Benutzer.class, id);
	}

	@Override
	@Transactional
	public Benutzer speichereBenutzer(final Benutzer benutzer)
	{
		return super.speichereEntitaet(Benutzer.class, benutzer);
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
