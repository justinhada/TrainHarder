package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class BenutzerJpaRepository extends JpaRepository<Benutzer> implements BenutzerRepository
{
	@Override
	public List<Benutzer> ermittleAlle()
	{
		return super.ermittleAlle(Benutzer.class);
	}

	@Override
	public Optional<Benutzer> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Benutzer.class, id);
	}

	@Override
	public Optional<Benutzer> ermittleZuAuthentifizierung(@NonNull Primaerschluessel authentifizierungId)
	{
		try
		{
			var criteriaBuilder = entityManager.getCriteriaBuilder();
			var criteriaQuery = criteriaBuilder.createQuery(Benutzer.class);
			var root = criteriaQuery.from(Benutzer.class);
			var joinAuthentifizierung = root.join("authentifizierung");
			criteriaQuery.select(root).where(
				criteriaBuilder.equal(joinAuthentifizierung.get("primaerschluessel"), authentifizierungId));
			return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
		}
		catch (NoResultException e)
		{
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Benutzer speichereBenutzer(@NonNull Benutzer benutzer)
	{
		return super.speichereEntitaet(Benutzer.class, benutzer);
	}
}
