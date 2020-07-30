package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.KoerpermessungRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class KoerpermessungJpaRepository extends JpaRepository<Koerpermessung> implements KoerpermessungRepository
{
	public KoerpermessungJpaRepository(final EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public List<Koerpermessung> ermittleAlleZuBenutzer(final Primaerschluessel benutzerId)
	{
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Koerpermessung.class);
		final var root = criteriaQuery.from(Koerpermessung.class);
		final var joinBenutzer = root.join("benutzer");
		criteriaQuery.select(root).where(criteriaBuilder.equal(joinBenutzer.get("primaerschluessel"), benutzerId));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Optional<Koerpermessung> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Koerpermessung.class, id);
	}

	@Override
	@Transactional
	public Koerpermessung speichereKoerpermessung(final Koerpermessung koerpermessung)
	{
		return super.speichereEntitaet(Koerpermessung.class, koerpermessung);
	}
}
