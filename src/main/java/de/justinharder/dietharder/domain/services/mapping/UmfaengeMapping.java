package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Umfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.pagination.UmfaengePaginationResponse;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class UmfaengeMapping implements Mapping<Umfaenge, GespeicherteUmfaenge, UmfaengePaginationResponse>
{
	@Override
	public GespeicherteUmfaenge mappe(@NonNull Umfaenge umfaenge)
	{
		return new GespeicherteUmfaenge(
			umfaenge.getId().toString(),
			umfaenge.getDatum().toString(),
			umfaenge.getHalsUmfang().toString(),
			umfaenge.getSchulterUmfang().toString(),
			umfaenge.getBrustRueckenUmfang().toString(),
			umfaenge.getLinkerOberarmUmfang().toString(),
			umfaenge.getRechterOberarmUmfang().toString(),
			umfaenge.getLinkerUnterarmUmfang().toString(),
			umfaenge.getRechterUnterarmUmfang().toString(),
			umfaenge.getBauchUmfang().toString(),
			umfaenge.getHueftUmfang().toString(),
			umfaenge.getLinkerOberschenkelUmfang().toString(),
			umfaenge.getRechterOberschenkelUmfang().toString(),
			umfaenge.getLinkerUnterschenkelUmfang().toString(),
			umfaenge.getRechterUnterschenkelUmfang().toString());
	}

	@Override
	public UmfaengePaginationResponse mappe(@NonNull List<Umfaenge> umfaenge)
	{
		return new UmfaengePaginationResponse(
			umfaenge.size(),
			umfaenge.stream()
				.map(this::mappe)
				.toList())
			.setSelf(null) // TODO: Implementieren, eigene URL
			.setFirst(null) // TODO: Implementieren, erste URL
			.setPrev(null) // TODO: Implementieren, vorherige URL
			.setNext(null) // TODO: Implementieren, nächste URL
			.setLast(null); // TODO: Implementieren, letzte URL
	}
}
