package de.justinharder.trainharder.domain.service.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.GespeicherterBenutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.pagination.BenutzerPaginationResponse;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class BenutzerMapping implements Mapping<Benutzer, GespeicherterBenutzer, BenutzerPaginationResponse>
{
	@Override
	public GespeicherterBenutzer mappe(@NonNull Benutzer benutzer)
	{
		return new GespeicherterBenutzer(
			benutzer.getId().toString(),
			benutzer.getVorname().getWert(),
			benutzer.getNachname().getWert());
	}

	@Override
	public BenutzerPaginationResponse mappe(@NonNull List<Benutzer> benutzer)
	{
		return new BenutzerPaginationResponse(
			benutzer.size(),
			benutzer.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
