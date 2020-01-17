package de.justinharder.powerlifting.persistence;

import java.util.List;

import javax.transaction.Transactional;

import de.justinharder.powerlifting.JpaRepository;
import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.repository.BelastungsfaktorRepository;

public class JpaBelastungsfaktorRepository extends JpaRepository<Belastungsfaktor> implements BelastungsfaktorRepository
{
	@Override
	public List<Belastungsfaktor> ermittleAlle()
	{
		return super.ermittleAlle(Belastungsfaktor.class);
	}

	@Override
	public Belastungsfaktor ermittleZuId(final int id)
	{
		return super.ermittleZuId(Belastungsfaktor.class, id);
	}

	@Override
	@Transactional
	public void erstelleBelastungsfaktor(final Belastungsfaktor belastungsfaktor)
	{
		super.erstelleEntitaet(belastungsfaktor);
	}
}
