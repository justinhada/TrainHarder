package de.justinharder.base.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GeloeschtesDTO<T extends GeloeschtesDTO<T>> extends DTO<T>
{
	@NonNull
	@JsonProperty(value = "id", required = true)
	private final String id;
}
