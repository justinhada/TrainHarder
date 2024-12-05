package de.justinharder.base.domain.services.dto.pagination;

import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PaginationRequest<T extends GespeichertesDTO<T>>
{
	@NonNull
	@Positive
	@DefaultValue("1")
	@QueryParam("page")
	private Integer page = 1;

	@NonNull
	@Positive
	@DefaultValue("10")
	@QueryParam("page_size")
	private Integer pageSize = 10;

	@Valid
	@Nullable
	@QueryParam("search_query")
	private String query;

	@Valid
	@Nullable
	@QueryParam("filter")
	private String filter;

	@Valid
	@Nullable
	@QueryParam("order")
	private String order;
}
