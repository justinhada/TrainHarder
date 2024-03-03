package de.justinharder.trainharder.domain.service.dto.registrierung;

import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GeloeschteRegistrierung extends GeloeschtesDTO<GeloeschteRegistrierung>
{
	public GeloeschteRegistrierung(String id)
	{
		super(id);
	}
}
