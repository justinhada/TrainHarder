package de.justinharder.base.domain.services.dto.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.annotation.Nullable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationResponse<T extends GespeichertesDTO<T>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = -3898896883258447926L;
	
	@NonNull
	@JsonProperty(required = true)
	private final Integer count;

	@Setter
	@Nullable
	private String self;

	@Setter
	@Nullable
	private String first;

	@Setter
	@Nullable
	private String prev;

	@Setter
	@Nullable
	private String next;

	@Setter
	@Nullable
	private String last;

	@NonNull
	@JsonProperty(required = true)
	private final List<T> results;
}
