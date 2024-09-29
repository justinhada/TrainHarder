package de.justinharder.dietharder.persistence;

import de.justinharder.dietharder.domain.model.Messung;
import de.justinharder.dietharder.domain.repository.MessungRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class MessungJpaRepository extends DatensatzJpaRepository<Messung> implements MessungRepository
{
	public MessungJpaRepository()
	{
		super(Messung.class);
	}
}
