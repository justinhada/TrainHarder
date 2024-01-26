package de.justinharder.base.domain.services.dto.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.annotation.Nullable;
import lombok.*;

import java.net.URL;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PaginationResponse<T extends GespeichertesDTO<T>> extends Pagination<T>
{
	@NonNull
	@JsonProperty(value = "count", required = true)
	protected final Integer count;

	@Setter
	@Nullable
	@JsonProperty(value = "self")
	protected URL self;

	@Setter
	@Nullable
	@JsonProperty(value = "first")
	protected URL first;

	@Setter
	@Nullable
	@JsonProperty(value = "prev")
	protected URL prev;

	@Setter
	@Nullable
	@JsonProperty(value = "next")
	protected URL next;

	@Setter
	@Nullable
	@JsonProperty(value = "last")
	protected URL last;

	@NonNull
	@JsonProperty(value = "results", required = true)
	protected final List<T> results;
}
