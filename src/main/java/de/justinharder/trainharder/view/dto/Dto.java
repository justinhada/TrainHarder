package de.justinharder.trainharder.view.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Dto implements Serializable
{
	private static final long serialVersionUID = -2922797523319378504L;

	protected String primaerschluessel;

	public abstract Dto setPrimaerschluessel(@NonNull String primaerschluessel);

	public abstract boolean equals(Object o);

	public abstract int hashCode();
}
