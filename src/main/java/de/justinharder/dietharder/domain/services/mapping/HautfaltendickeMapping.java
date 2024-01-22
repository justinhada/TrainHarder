package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Hautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.pagination.HautfaltendickePaginationResponse;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class HautfaltendickeMapping
	implements Mapping<Hautfaltendicke, GespeicherteHautfaltendicke, HautfaltendickePaginationResponse>
{
	@Override
	public GespeicherteHautfaltendicke mappe(@NonNull Hautfaltendicke hautfaltendicke)
	{
		return new GespeicherteHautfaltendicke(
			hautfaltendicke.getId().toString(),
			hautfaltendicke.getDatum().toString(),
			hautfaltendicke.getBrustfalte().toString(),
			hautfaltendicke.getBauchfalte().toString(),
			hautfaltendicke.getBeinfalte().toString(),
			hautfaltendicke.getHueftfalte().toString(),
			hautfaltendicke.getAchselfalte().toString(),
			hautfaltendicke.getTrizepsfalte().toString(),
			hautfaltendicke.getRueckenfalte().toString(),
			hautfaltendicke.getKoerperfettAnteil().toString());
	}

	@Override
	public HautfaltendickePaginationResponse mappe(@NonNull List<Hautfaltendicke> hautfaltendicken)
	{
		return new HautfaltendickePaginationResponse(
			hautfaltendicken.size(),
			hautfaltendicken.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
