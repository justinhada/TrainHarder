package de.justinharder.powerlifting.persistence;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;

public class JpaKraftwertRepository implements KraftwertRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Kraftwert> ermittleAlle()
	{
		final CriteriaQuery<Kraftwert> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Kraftwert.class);
		criteriaQuery.select(criteriaQuery.from(Kraftwert.class));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Kraftwert> ermittleAlleZuBenutzer(final Benutzer benutzer)
	{
		return ermittleAlle()
			.stream()
			.filter(kraftwert -> kraftwert.getBenutzer().equals(benutzer))
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void erstelleKraftwert(final Kraftwert kraftwert)
	{
		entityManager.persist(kraftwert);
	}
}
