package de.justinharder.powerlifting.persistence;

import java.util.List;

import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;

public class JpaKraftwertRepository extends JpaRepository<Kraftwert> implements KraftwertRepository
{
	@Override
	public List<Kraftwert> ermittleAlle()
	{
		return super.ermittleAlle(Kraftwert.class);
	}

	@Override
	public List<Kraftwert> ermittleAlleZuBenutzer(final int benutzerId)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Kraftwert.class);
		final var root = criteriaQuery.from(Kraftwert.class);
		final var joinBenutzer = root.join("Benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("ID"), benutzerId));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Kraftwert ermittleZuId(final int id)
	{
		return super.ermittleZuId(Kraftwert.class, id);
	}

	@Override
	@Transactional
	public void erstelleKraftwert(final Kraftwert kraftwert)
	{
		super.erstelleEntitaet(kraftwert);
	}
}
