package de.justinharder.trainharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.GespeicherteRegistrierung;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class RegistrierungMapping implements Mapping<Registrierung, GespeicherteRegistrierung>
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
