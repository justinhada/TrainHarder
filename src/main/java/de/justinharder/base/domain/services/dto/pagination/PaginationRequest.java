package de.justinharder.base.domain.services.dto.pagination;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class PaginationRequest<T extends GespeichertesDTO<T>> extends Pagination<T>
{
	@NonNull
	@Positive
	@DefaultValue("1")
	@QueryParam("page")
	protected Long page;

	@NonNull
	@Positive
	@DefaultValue("10")
	@QueryParam("page_size")
	protected Long pageSize;

	@Valid
	@Nullable
	protected Ordering<T> ordering; // TODO: Wie genau soll das implementiert werden?

	protected String search;
}
