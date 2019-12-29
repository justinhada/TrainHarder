package de.justinharder.powerlifting.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JpaBenutzerRepository implements BenutzerRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Benutzer> ermittleAlle()
	{
		final CriteriaQuery<Benutzer> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Benutzer.class);
		criteriaQuery.select(criteriaQuery.from(Benutzer.class));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Benutzer ermittleZuId(final int id)
	{
		return null;
	}

	@Override
	@Transactional
	public void erstelleBenutzer(final Benutzer benutzer)
	{
		entityManager.persist(benutzer);
	}
}
