package de.justinharder.trainharder.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Dto implements Serializable
{
	private static final long serialVersionUID = -2922797523319378504L;

	protected String primaerschluessel;

	public Dto setPrimaerschluessel(String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}
}
