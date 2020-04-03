package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.exceptions.AnmeldedatenNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;
import de.justinharder.powerlifting.model.repository.AnmeldedatenRepository;

public class JpaAnmeldedatenRepository extends JpaRepository<Anmeldedaten> implements AnmeldedatenRepository
{
	@Override
	public List<Anmeldedaten> ermittleAlle()
	{
		return super.ermittleAlle(Anmeldedaten.class);
	}

	@Override
	public Anmeldedaten ermittleZuId(final int id)
	{
		return super.ermittleZuId(Anmeldedaten.class, id);
	}

	@Override
	public Anmeldedaten ermittleZuBenutzer(final int benutzerId) throws AnmeldedatenNichtGefundenException
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Anmeldedaten.class);
			final var root = criteriaQuery.from(Anmeldedaten.class);
			final var joinBenutzer = root.join("Benutzer");
			criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("ID"), benutzerId));
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch (final NoResultException e)
		{
			throw new AnmeldedatenNichtGefundenException(
				"Die Anmeldedaten zur BenutzerID \"" + benutzerId + "\" existieren nicht!");
		}
	}

	@Override
	@Transactional
	public void erstelleAnmeldedaten(final Anmeldedaten anmeldedaten)
	{
		super.erstelleEntitaet(anmeldedaten);
	}

	@Override
	public Anmeldedaten checkLogin(final String mail, final String passwort) throws LoginException
	{
		try
		{
			return super.erstelleQuery(Anmeldedaten.class, Map.of("mail", mail, "passwort", passwort))
				.getSingleResult();
		}
		catch (final NoResultException e)
		{
			throw new LoginException("E-Mail-Adresse oder Passwort falsch!");
		}
	}

	@Override
	public boolean checkMail(final String mail)
	{
		try
		{
			return super.erstelleQuery(Anmeldedaten.class, Map.of("mail", mail))
				.getSingleResult()
				.getMail()
				.equals(mail);
		}
		catch (final NoResultException e)
		{
			return false;
		}
	}

	@Override
	public boolean checkBenutzername(final String benutzername)
	{
		try
		{
			return super.erstelleQuery(Anmeldedaten.class, Map.of("benutzername", benutzername))
				.getSingleResult()
				.getMail()
				.equals(benutzername);
		}
		catch (final NoResultException e)
		{
			return false;
		}
	}
}
