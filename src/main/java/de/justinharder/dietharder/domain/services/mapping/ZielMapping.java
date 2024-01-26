package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.pagination.ZielPaginationResponse;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class ZielMapping implements Mapping<Ziel, GespeichertesZiel, ZielPaginationResponse>
{
	@Override
	public GespeichertesZiel mappe(@NonNull Ziel ziel)
	{
		return new GespeichertesZiel(
			ziel.getId().toString(),
			ziel.getDatum().toString(),
			ziel.getKoerpergewicht().toString(),
			ziel.getKoerperfettAnteil().toString());
	}

	@Override
	public ZielPaginationResponse mappe(@NonNull List<Ziel> ziele)
	{
		return new ZielPaginationResponse(
			ziele.size(),
			ziele.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
