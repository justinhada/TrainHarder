package de.justinharder.trainharder.domain.service.dto.login;

import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GeloeschterLogin extends GeloeschtesDTO<GeloeschterLogin>
{
	public GeloeschterLogin(String id)
	{
		super(id);
	}
}
