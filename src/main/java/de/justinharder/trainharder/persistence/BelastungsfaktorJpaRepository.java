package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.transaction.Transactional;
import java.util.Optional;

@NoArgsConstructor
public class BelastungsfaktorJpaRepository extends JpaRepository<Belastungsfaktor> implements BelastungsfaktorRepository
{
	@Override
	public Optional<Belastungsfaktor> ermittleZuId(@NonNull Primaerschluessel id)
	{
		return super.ermittleZuId(Belastungsfaktor.class, id);
	}

	@Override
	@Transactional
	public Belastungsfaktor speichereBelastungsfaktor(@NonNull Belastungsfaktor belastungsfaktor)
	{
		return super.speichereEntitaet(Belastungsfaktor.class, belastungsfaktor);
	}
}
