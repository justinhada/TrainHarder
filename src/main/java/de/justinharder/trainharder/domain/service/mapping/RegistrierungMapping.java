package de.justinharder.trainharder.domain.service.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GespeicherteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationResponse;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class RegistrierungMapping
	implements Mapping<Registrierung, GespeicherteRegistrierung, RegistrierungPaginationResponse>
{
	@Override
	public GespeicherteRegistrierung mappe(@NonNull Registrierung registrierung)
	{
		return new GespeicherteRegistrierung(
			registrierung.getId().toString(),
			registrierung.getEMailAdresse().getWert());
	}

	@Override
	public RegistrierungPaginationResponse mappe(@NonNull List<Registrierung> registrierungen)
	{
		return new RegistrierungPaginationResponse(
			registrierungen.size(),
			registrierungen.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
