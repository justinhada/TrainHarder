package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class UebungDto implements Serializable
{
	private static final long serialVersionUID = -7055200842034977331L;

	private String primaerschluessel;
	private String name;
	private String uebungsart;
	private String uebungskategorie;
	private BelastungsfaktorDto belastungsfaktor;

	public UebungDto()
	{}

	public UebungDto(
		final String primaerschluessel,
		final String name,
		final String uebungsart,
		final String uebungskategorie,
		final BelastungsfaktorDto belastungsfaktor)
	{
		this.primaerschluessel = primaerschluessel;
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastungsfaktor = belastungsfaktor;
	}

	public UebungDto setPrimaerschluessel(final String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public UebungDto setName(final String name)
	{
		this.name = name;
		return this;
	}

	public UebungDto setUebungsart(final String uebungsart)
	{
		this.uebungsart = uebungsart;
		return this;
	}

	public UebungDto setUebungskategorie(final String uebungskategorie)
	{
		this.uebungskategorie = uebungskategorie;
		return this;
	}

	public UebungDto setBelastungsfaktor(final BelastungsfaktorDto belastungsfaktor)
	{
		this.belastungsfaktor = belastungsfaktor;
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
		UebungDto uebungDto = (UebungDto) o;
		return primaerschluessel.equals(uebungDto.primaerschluessel) &&
			name.equals(uebungDto.name) &&
			uebungsart.equals(uebungDto.uebungsart) &&
			uebungskategorie.equals(uebungDto.uebungskategorie) &&
			belastungsfaktor.equals(uebungDto.belastungsfaktor);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, name, uebungsart, uebungskategorie, belastungsfaktor);
	}
}
