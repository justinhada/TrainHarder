package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class KraftwertDto extends EntitaetDto
{
	private static final long serialVersionUID = -5177169492291346152L;

	private String gewicht;
	private String koerpergewicht;
	private String datum;
	private String wiederholungen;

	public KraftwertDto()
	{}

	public KraftwertDto(@NonNull String primaerschluessel, @NonNull String gewicht, @NonNull String koerpergewicht, @NonNull String datum, @NonNull String wiederholungen)
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

	public KraftwertDto setGewicht(@NonNull String gewicht)
	{
		this.gewicht = gewicht;
		return this;
	}

	public KraftwertDto setKoerpergewicht(@NonNull String koerpergewicht)
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
		return Objects.equals(primaerschluessel, that.primaerschluessel)
			&& Objects.equals(gewicht, that.gewicht)
			&& Objects.equals(koerpergewicht, that.koerpergewicht)
			&& Objects.equals(datum, that.datum)
			&& Objects.equals(wiederholungen, that.wiederholungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, gewicht, koerpergewicht, datum, wiederholungen);
	}
}