package de.justinharder.dietharder.domain.services.dto.hautfaltendicke;

import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GeloeschteHautfaltendicke extends GeloeschtesDTO<GeloeschteHautfaltendicke>
{
	public GeloeschteHautfaltendicke(String id)
	{
		super(id);
	}
}
