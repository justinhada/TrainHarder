package de.justinharder.old.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EntitaetDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = -2922797523319378504L;

	@NonNull
	protected String id;

	public abstract EntitaetDto setId(@NonNull String id);

	public abstract boolean equals(Object o);

	public abstract int hashCode();
}
