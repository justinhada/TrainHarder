package de.justinharder.base.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NeuesDTO<T extends NeuesDTO<T>> extends DTO<T>
{
	@Nullable
	private String id;
}
