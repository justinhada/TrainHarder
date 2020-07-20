package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;

public class JpaBelastungsfaktorRepository extends JpaRepository<Belastungsfaktor> implements BelastungsfaktorRepository
{
	private static final long serialVersionUID = -8257581034371626633L;

	@Override
	public List<Belastungsfaktor> ermittleAlle()
	{
		return super.ermittleAlle(Belastungsfaktor.class);
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
