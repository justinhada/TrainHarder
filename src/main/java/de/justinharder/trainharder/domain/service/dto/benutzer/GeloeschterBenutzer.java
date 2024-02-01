package de.justinharder.trainharder.domain.service.dto.benutzer;

import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GeloeschterBenutzer extends GeloeschtesDTO<GeloeschterBenutzer>
{
	public GeloeschterBenutzer(String id)
	{
		super(id);
	}
}
