package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
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
	public Optional<Benutzer> ermittleZuAuthentifizierung(final Primaerschluessel authentifizierungId)
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Benutzer.class);
			final var root = criteriaQuery.from(Benutzer.class);
			final var joinAuthentifizierung = root.join("authentifizierung");
			criteriaQuery.select(root).where(
				criteriaBuilder.equal(joinAuthentifizierung.get("primaerschluessel"), authentifizierungId));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (final NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Benutzer speichereBenutzer(final Benutzer benutzer)
	{
		return super.speichereEntitaet(Benutzer.class, benutzer);
	}

	@Override
	public List<Benutzer> ermittleAlleZuNachname(final String nachname)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Benutzer.class);
		final var root = criteriaQuery.from(Benutzer.class);
		criteriaQuery.select(root).where(
			criteriaBuilder.equal(root.get("name").get("nachname"), nachname));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
