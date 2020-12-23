package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class KraftwertJpaRepository extends JpaRepository<Kraftwert> implements KraftwertRepository
{
	@Override
	public List<Kraftwert> ermittleAlleZuBenutzer(@NonNull Primaerschluessel benutzerId)
	{
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Kraftwert.class);
		var root = criteriaQuery.from(Kraftwert.class);
		var joinBenutzer = root.join("benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("primaerschluessel"), benutzerId));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Kraftwert> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Kraftwert.class, id);
	}

	@Override
	@Transactional
	public Kraftwert speichereKraftwert(@NonNull Kraftwert kraftwert)
	{
		return super.speichereEntitaet(Kraftwert.class, kraftwert);
	}
}
