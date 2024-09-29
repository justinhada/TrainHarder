package de.justinharder.base.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NeuesDTO<T extends NeuesDTO<T>> extends DTO<T>
{
	@Nullable
	private String id;
}
