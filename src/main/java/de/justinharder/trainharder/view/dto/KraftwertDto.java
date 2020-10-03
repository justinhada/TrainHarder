package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class KraftwertDto implements Serializable
{
	private static final long serialVersionUID = -5177169492291346152L;

	private String primaerschluessel;
	private int maximum;
	private double koerpergewicht;
	private String datum;
	private String wiederholungen;

	public KraftwertDto()
	{}

	public KraftwertDto(
		final String primaerschluessel,
		final int maximum,
		final double koerpergewicht,
		final String datum,
		final String wiederholungen)
	{
		this.primaerschluessel = primaerschluessel;
		this.maximum = maximum;
		this.koerpergewicht = koerpergewicht;
		this.datum = datum;
		this.wiederholungen = wiederholungen;
	}

	public KraftwertDto setPrimaerschluessel(final String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public KraftwertDto setMaximum(final int maximum)
	{
		this.maximum = maximum;
		return this;
	}

	public KraftwertDto setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		return this;
	}

	public KraftwertDto setDatum(final String datum)
	{
		this.datum = datum;
		return this;
	}

	public KraftwertDto setWiederholungen(final String wiederholungen)
	{
		this.wiederholungen = wiederholungen;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		KraftwertDto that = (KraftwertDto) o;
		return maximum == that.maximum &&
			Double.compare(that.koerpergewicht, koerpergewicht) == 0 &&
			primaerschluessel.equals(that.primaerschluessel) &&
			datum.equals(that.datum) &&
			wiederholungen.equals(that.wiederholungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, maximum, koerpergewicht, datum, wiederholungen);
	}
}
