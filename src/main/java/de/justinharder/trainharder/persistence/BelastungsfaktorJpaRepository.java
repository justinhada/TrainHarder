package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@NoArgsConstructor
public class BelastungsfaktorJpaRepository extends JpaRepository<Belastung> implements BelastungsfaktorRepository
{
	@Override
	public Optional<Belastung> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Belastung.class, id);
	}

	@Override
	@Transactional
	public Belastung speichereBelastungsfaktor(@NonNull Belastung belastung)
	{
		return super.speichereEntitaet(Belastung.class, belastung);
	}
}
