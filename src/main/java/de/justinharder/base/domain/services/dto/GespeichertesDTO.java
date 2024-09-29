package de.justinharder.base.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GespeichertesDTO<T extends GespeichertesDTO<T>> extends DTO<T>
{
	@NonNull
	@JsonProperty(value = "id", required = true)
	private final String id;
}
