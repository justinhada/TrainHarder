package de.justinharder.dietharder.domain.services.dto.ziel;

import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GeloeschtesZiel extends GeloeschtesDTO<GeloeschtesZiel>
{
	public GeloeschtesZiel(String id)
	{
		super(id);
	}
}
