package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Authentifizierung;
import de.justinharder.powerlifting.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;
import de.justinharder.powerlifting.model.repository.AuthentifizierungRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JpaAuthentifizierungRepository extends JpaRepository<Authentifizierung>
	implements AuthentifizierungRepository
{
	public JpaAuthentifizierungRepository(final EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public List<Authentifizierung> ermittleAlle()
	{
		return super.ermittleAlle(Authentifizierung.class);
	}

	@Override
	public Authentifizierung ermittleZuId(final int id)
	{
		return super.ermittleZuId(Authentifizierung.class, id);
	}

	@Override
	public Authentifizierung ermittleZuBenutzer(final int benutzerId) throws AuthentifizierungNichtGefundenException
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			final var joinBenutzer = root.join("benutzer");
			criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("id"), benutzerId));
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch (final NoResultException e)
		{
			throw new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung zur BenutzerID \"" + benutzerId + "\" existiert nicht!");
		}
	}

	@Override
	public Authentifizierung ermittleZuMail(final String mail) throws AuthentifizierungNichtGefundenException
	{
		try
		{
			return super.erstelleQuery(Authentifizierung.class, Map.of("mail", mail))
				.getSingleResult();
		}
		catch (final NoResultException e)
		{
			throw new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung zur Mail \"" + mail + "\" existiert nicht!");
		}
	}

	@Override
	@Transactional
	public void erstelleAuthentifizierung(final Authentifizierung authentifizierung)
	{
		super.erstelleEntitaet(authentifizierung);
	}

	@Override
	public Authentifizierung checkLogin(final String benutzername, final String passwort) throws LoginException
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(
				criteriaBuilder.equal(root.get("benutzername"), benutzername),
				criteriaBuilder.equal(root.get("passwort"), passwort));
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		}
		catch (final NoResultException e)
		{
			throw new LoginException("Benutzername oder Passwort falsch!");
		}
	}

	@Override
	public boolean checkMail(final String mail)
	{
		try
		{
			return ermittleZuMail(mail)
				.getMail()
				.equalsIgnoreCase(mail);
		}
		catch (final NoResultException | AuthentifizierungNichtGefundenException e)
		{
			return false;
		}
	}

	@Override
	public boolean checkBenutzername(final String benutzername)
	{
		try
		{
			return super.erstelleQuery(Authentifizierung.class, Map.of("benutzername", benutzername))
				.getSingleResult()
				.getBenutzername()
				.equalsIgnoreCase(benutzername);
		}
		catch (final NoResultException e)
		{
			return false;
		}
	}
}
