package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JpaKraftwertRepository extends JpaRepository<Kraftwert> implements KraftwertRepository
{
	public JpaKraftwertRepository(final EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public List<Kraftwert> ermittleAlleZuBenutzer(final Primaerschluessel benutzerId)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Kraftwert.class);
		final var root = criteriaQuery.from(Kraftwert.class);
		final var joinBenutzer = root.join("Benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("ID"), benutzerId));
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
