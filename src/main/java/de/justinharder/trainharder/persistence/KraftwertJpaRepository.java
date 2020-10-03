package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class KraftwertJpaRepository extends JpaRepository<Kraftwert> implements KraftwertRepository
{
	public KraftwertJpaRepository(final EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public List<Kraftwert> ermittleAlleZuBenutzer(final Primaerschluessel benutzerId)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Kraftwert.class);
		final var root = criteriaQuery.from(Kraftwert.class);
		final var joinBenutzer = root.join("benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("primaerschluessel"), benutzerId));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Kraftwert> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Kraftwert.class, id);
	}

	@Override
	@Transactional
	public Kraftwert speichereKraftwert(final Kraftwert kraftwert)
	{
		return super.speichereEntitaet(Kraftwert.class, kraftwert);
	}
}
