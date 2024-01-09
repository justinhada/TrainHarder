package de.justinharder.trainharder.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.repository.BelastungRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class BelastungJpaRepository extends JpaRepository<Belastung> implements BelastungRepository
{
	public BelastungJpaRepository()
	{
		super(Belastung.class);
	}
}
