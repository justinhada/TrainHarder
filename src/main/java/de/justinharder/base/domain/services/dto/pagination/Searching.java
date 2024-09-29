package de.justinharder.base.domain.services.dto.pagination;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.ws.rs.QueryParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Searching<T extends GespeichertesDTO<T>>
{
	@QueryParam("query")
	private String query;
}
