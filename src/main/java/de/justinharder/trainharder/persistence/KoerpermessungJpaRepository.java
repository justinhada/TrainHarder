package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.KoerpermessungRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class KoerpermessungJpaRepository extends JpaRepository<Koerpermessung> implements KoerpermessungRepository
{
	@Override
	public List<Koerpermessung> ermittleAlleZuBenutzer(@NonNull Primaerschluessel benutzerId)
	{
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Koerpermessung.class);
		var root = criteriaQuery.from(Koerpermessung.class);
		var joinBenutzer = root.join("benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("primaerschluessel"), benutzerId));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Koerpermessung> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Koerpermessung.class, id);
	}

	@Override
	@Transactional
	public Koerpermessung speichereKoerpermessung(@NonNull Koerpermessung koerpermessung)
	{
		return super.speichereEntitaet(Koerpermessung.class, koerpermessung);
	}
}
