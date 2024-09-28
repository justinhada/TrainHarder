package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Umfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class UmfaengeMapping implements Mapping<Umfaenge, GespeicherteUmfaenge>
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
}
