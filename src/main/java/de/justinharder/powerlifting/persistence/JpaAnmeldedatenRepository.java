package de.justinharder.powerlifting.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
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
	public Anmeldedaten ermittleZuBenutzer(final int benutzerId)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Anmeldedaten.class);
		final var root = criteriaQuery.from(Anmeldedaten.class);
		final var joinBenutzer = root.join("Benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("ID"), benutzerId));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
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
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Anmeldedaten.class);
			final var root = criteriaQuery.from(Anmeldedaten.class);
			criteriaQuery.select(root)
				.where(criteriaBuilder.equal(root.get("mail"), mail))
				.where(criteriaBuilder.equal(root.get("passwort"), passwort));
			return entityManager.createQuery(criteriaQuery).getSingleResult();
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
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Anmeldedaten.class);
			final var root = criteriaQuery.from(Anmeldedaten.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("mail"), mail));
			final var anmeldedaten = entityManager.createQuery(criteriaQuery).getSingleResult();
			return anmeldedaten.getMail().equals(mail);
		}
		catch (final NoResultException e)
		{
			return false;
		}
	}
}
