package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Messung;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class MessungMapping implements Mapping<Messung, GespeicherteMessung>
{
	@Override
	public GespeicherteMessung mappe(@NonNull Messung messung)
	{
		return new GespeicherteMessung(
			messung.getId().toString(),
			messung.getDatum().toString(),
			messung.getKoerpergewicht().toString(),
			messung.getKoerperfettAnteil().toString());
	}

	@Override
	public MessungPaginationResponse mappe(@NonNull List<Messung> messungen)
	{
		return new MessungPaginationResponse(
			messungen.size(),
			messungen.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
