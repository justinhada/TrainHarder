package de.justinharder.dietharder.persistence;

import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.repository.ZielRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class ZielJpaRepository extends DatensatzJpaRepository<Ziel> implements ZielRepository
{
	public ZielJpaRepository()
	{
		super(Ziel.class);
	}
}
