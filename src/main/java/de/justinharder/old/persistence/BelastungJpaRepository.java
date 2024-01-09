package de.justinharder.old.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.old.domain.repository.BelastungRepository;
import de.justinharder.old.domain.model.Belastung;
import jakarta.enterprise.context.Dependent;

@Dependent
public class BelastungJpaRepository extends JpaRepository<Belastung> implements BelastungRepository
{
	public BelastungJpaRepository()
	{
		super(Belastung.class);
	}
}
