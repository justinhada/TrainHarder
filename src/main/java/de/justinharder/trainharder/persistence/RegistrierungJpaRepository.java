package de.justinharder.trainharder.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.repository.RegistrierungRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class RegistrierungJpaRepository extends JpaRepository<Registrierung> implements RegistrierungRepository
{
	public RegistrierungJpaRepository()
	{
		super(Registrierung.class);
	}
}
