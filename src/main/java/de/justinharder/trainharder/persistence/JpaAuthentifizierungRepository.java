package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JpaAuthentifizierungRepository extends JpaRepository<Authentifizierung>
	implements AuthentifizierungRepository
{
	private static final long serialVersionUID = -5882682850110144178L;

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
	public Optional<Authentifizierung> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Authentifizierung.class, id);
	}

	@Override
	public Optional<Authentifizierung> ermittleZuBenutzer(final Primaerschluessel benutzerId)
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			final var joinBenutzer = root.join("benutzer");
			criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("primaerschluessel"), benutzerId));
			return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (final NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> ermittleZuMail(final String mail)
	{
		try
		{
			return Optional.ofNullable(super.erstelleQuery(Authentifizierung.class, Map.of("mail", mail))
				.getSingleResult());
		}
		catch (final NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Authentifizierung speichereAuthentifizierung(final Authentifizierung authentifizierung)
	{
		return super.speichereEntitaet(Authentifizierung.class, authentifizierung);
	}

	@Override
	public Optional<Authentifizierung> login(final String benutzername, final String passwort)
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(
				criteriaBuilder.equal(root.get("benutzername"), benutzername),
				criteriaBuilder.equal(root.get("passwort"), passwort));
			return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (final NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public boolean checkMail(final String mail)
	{
		return ermittleZuMail(mail).isPresent();
	}

	@Override
	public boolean checkBenutzername(final String benutzername)
	{
		try
		{
			return Optional
				.ofNullable(super.erstelleQuery(Authentifizierung.class, Map.of("benutzername", benutzername))
					.getSingleResult())
				.isPresent();
		}
		catch (final NoResultException e)
		{
			return false;
		}
	}
}
