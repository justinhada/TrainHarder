package de.justinharder.trainharder.domain.services.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntitaetDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = -2922797523319378504L;

	protected String primaerschluessel;

	public abstract EntitaetDto setPrimaerschluessel(@NonNull String primaerschluessel);

	public abstract boolean equals(Object o);

	public abstract int hashCode();
}
