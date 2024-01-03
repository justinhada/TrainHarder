package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.repository.BelastungsfaktorRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@Dependent
@NoArgsConstructor
public class BelastungsfaktorJpaRepository extends JpaRepository<Belastung> implements BelastungsfaktorRepository
{
	@Override
	public Optional<Belastung> ermittleZuId(@NonNull ID id)
	{
		return super.finde(Belastung.class, id);
	}

	@Override
	@Transactional
	public Belastung speichereBelastungsfaktor(@NonNull Belastung belastung)
	{
		return super.speichere(Belastung.class, belastung);
	}
}
