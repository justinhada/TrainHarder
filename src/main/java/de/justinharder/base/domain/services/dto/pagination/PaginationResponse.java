package de.justinharder.base.domain.services.dto.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationResponse<T extends GespeichertesDTO<T>>
{
	@NonNull
	@JsonProperty(required = true)
	protected final Integer count;

	@Setter
	@Nullable
	protected String self;

	@Setter
	@Nullable
	protected String first;

	@Setter
	@Nullable
	protected String prev;

	@Setter
	@Nullable
	protected String next;

	@Setter
	@Nullable
	protected String last;

	@NonNull
	@JsonProperty(required = true)
	protected final List<T> results;
}
