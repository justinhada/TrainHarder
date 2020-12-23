package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class KraftwertDto extends Dto
{
	private static final long serialVersionUID = -5177169492291346152L;

	private double gewicht;
	private double koerpergewicht;
	private String datum;
	private String wiederholungen;

	public KraftwertDto()
	{}

	public KraftwertDto(
		@NonNull String primaerschluessel,
		double gewicht,
		double koerpergewicht,
		@NonNull String datum,
		@NonNull String wiederholungen)
	{
		super(primaerschluessel);
		this.gewicht = gewicht;
		this.koerpergewicht = koerpergewicht;
		this.datum = datum;
		this.wiederholungen = wiederholungen;
	}

	@Override
	public KraftwertDto setPrimaerschluessel(@NonNull String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public KraftwertDto setGewicht(double gewicht)
	{
		this.gewicht = gewicht;
		return this;
	}

	public KraftwertDto setKoerpergewicht(double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		return this;
	}

	public KraftwertDto setDatum(@NonNull String datum)
	{
		this.datum = datum;
		return this;
	}

	public KraftwertDto setWiederholungen(@NonNull String wiederholungen)
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
		if (!(o instanceof KraftwertDto))
		{
			return false;
		}
		var that = (KraftwertDto) o;
		return Double.compare(that.gewicht, gewicht) == 0 &&
			Double.compare(that.koerpergewicht, koerpergewicht) == 0 &&
			primaerschluessel.equals(that.primaerschluessel) &&
			datum.equals(that.datum) &&
			wiederholungen.equals(that.wiederholungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, gewicht, koerpergewicht, datum, wiederholungen);
	}
}
