package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
public class AuthentifizierungJpaRepository extends JpaRepository<Authentifizierung>	implements AuthentifizierungRepository
{
	@Override
	public Optional<Authentifizierung> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Authentifizierung.class, id);
	}

	@Override
	public Optional<Authentifizierung> ermittleZuBenutzer(@NonNull Primaerschluessel benutzerId)
	{
		try
		{
			var criteriaBuilder = entityManager.getCriteriaBuilder();
			var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			var root = criteriaQuery.from(Authentifizierung.class);
			var joinBenutzer = root.join("benutzer");
			criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("primaerschluessel"), benutzerId));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> ermittleZuMail(@NonNull String mail)
	{
		try
		{
			var criteriaBuilder = entityManager.getCriteriaBuilder();
			var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("mail"), mail));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> ermittleZuBenutzername(@NonNull String benutzername)
	{
		try
		{
			var criteriaBuilder = entityManager.getCriteriaBuilder();
			var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("benutzername"), benutzername));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Authentifizierung> ermittleZuResetUuid(@NonNull UUID resetUuid)
	{
		try
		{
			var criteriaBuilder = entityManager.getCriteriaBuilder();
			var criteriaQuery = criteriaBuilder.createQuery(Authentifizierung.class);
			var root = criteriaQuery.from(Authentifizierung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("resetUuid"), resetUuid));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Authentifizierung speichereAuthentifizierung(@NonNull Authentifizierung authentifizierung)
	{
		return super.speichereEntitaet(Authentifizierung.class, authentifizierung);
	}
}