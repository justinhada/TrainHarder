package de.justinharder.trainharder.persistence;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthentifizierungJpaRepository extends JpaRepository<Authentifizierung>
	implements AuthentifizierungRepository
{
	public AuthentifizierungJpaRepository(final EntityManager entityManager)
	{
		super(entityManager);
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
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
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
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("mail"), mail));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (final NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> ermittleZuBenutzername(final String benutzername)
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("benutzername"), benutzername));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (final NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> ermittleZuResetUuid(final UUID resetUuid)
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			final var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("resetUuid"), resetUuid));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
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
}
