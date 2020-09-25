package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@NoArgsConstructor
public class BelastungsfaktorJpaRepository extends JpaRepository<Belastungsfaktor> implements BelastungsfaktorRepository
{
	public BelastungsfaktorJpaRepository(EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public Optional<Belastungsfaktor> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Belastungsfaktor.class, id);
	}

	@Override
	@Transactional
	public Belastungsfaktor speichereBelastungsfaktor(final Belastungsfaktor belastungsfaktor)
	{
		return super.speichereEntitaet(Belastungsfaktor.class, belastungsfaktor);
	}
}
