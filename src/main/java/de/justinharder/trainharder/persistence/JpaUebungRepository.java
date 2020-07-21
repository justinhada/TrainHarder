package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.repository.UebungRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JpaUebungRepository extends JpaRepository<Uebung> implements UebungRepository
{
	public JpaUebungRepository(final EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public List<Uebung> ermittleAlle()
	{
		return super.ermittleAlle(Uebung.class);
	}

	@Override
	public List<Uebung> ermittleAlleZuUebungsart(final Uebungsart uebungsart)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Uebung.class);
		final var root = criteriaQuery.from(Uebung.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uebungsart"), uebungsart));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Uebung> ermittleAlleZuUebungskategorie(final Uebungskategorie uebungskategorie)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Uebung.class);
		final var root = criteriaQuery.from(Uebung.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uebungskategorie"), uebungskategorie));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Uebung> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Uebung.class, id);
	}

	@Override
	@Transactional
	public Uebung speichereUebung(final Uebung uebung)
	{
		return super.speichereEntitaet(Uebung.class, uebung);
	}
}
