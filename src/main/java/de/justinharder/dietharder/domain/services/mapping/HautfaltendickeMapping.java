package de.justinharder.dietharder.domain.services.mapping;

import de.justinharder.base.domain.services.mapping.Mapping;
import de.justinharder.dietharder.domain.model.Hautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class HautfaltendickeMapping implements Mapping<Hautfaltendicke, GespeicherteHautfaltendicke>
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
}
