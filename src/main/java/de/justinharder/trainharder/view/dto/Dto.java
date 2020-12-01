package de.justinharder.trainharder.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Dto implements Serializable
{
	private static final long serialVersionUID = -2922797523319378504L;

	protected String primaerschluessel;

	public abstract Dto setPrimaerschluessel(String primaerschluessel);

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Dto))
		{
			return false;
		}
		var that = (Dto) o;
		return primaerschluessel.equals(that.primaerschluessel);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel);
	}
}

