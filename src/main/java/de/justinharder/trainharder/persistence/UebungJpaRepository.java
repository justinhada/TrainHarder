package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.repository.UebungRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class UebungJpaRepository extends JpaRepository<Uebung> implements UebungRepository
{
	@Override
	public List<Uebung> ermittleAlle()
	{
		return super.ermittleAlle(Uebung.class);
	}

	@Override
	public List<Uebung> ermittleAlleZuUebungsart(@NonNull Uebungsart uebungsart)
	{
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Uebung.class);
		var root = criteriaQuery.from(Uebung.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uebungsart"), uebungsart));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Uebung> ermittleAlleZuUebungskategorie(@NonNull Uebungskategorie uebungskategorie)
	{
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Uebung.class);
		var root = criteriaQuery.from(Uebung.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uebungskategorie"), uebungskategorie));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Uebung> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Uebung.class, id);
	}

	@Override
	@Transactional
	public Uebung speichereUebung(@NonNull Uebung uebung)
	{
		return super.speichereEntitaet(Uebung.class, uebung);
	}
}
