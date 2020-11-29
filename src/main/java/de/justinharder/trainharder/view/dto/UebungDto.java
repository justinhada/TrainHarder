package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class UebungDto extends Dto
{
	private static final long serialVersionUID = -7055200842034977331L;

	private String name;
	private String uebungsart;
	private String uebungskategorie;
	private BelastungsfaktorDto belastungsfaktor;

	public UebungDto()
	{}

	public UebungDto(
		String primaerschluessel,
		String name,
		String uebungsart,
		String uebungskategorie,
		BelastungsfaktorDto belastungsfaktor)
	{
		super(primaerschluessel);
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastungsfaktor = belastungsfaktor;
	}

	public UebungDto setName(String name)
	{
		this.name = name;
		return this;
	}

	public UebungDto setUebungsart(String uebungsart)
	{
		this.uebungsart = uebungsart;
		return this;
	}

	public UebungDto setUebungskategorie(String uebungskategorie)
	{
		this.uebungskategorie = uebungskategorie;
		return this;
	}

	public UebungDto setBelastungsfaktor(BelastungsfaktorDto belastungsfaktor)
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
		if (!(o instanceof UebungDto))
		{
			return false;
		}
		var that = (UebungDto) o;
		return primaerschluessel.equals(that.primaerschluessel) &&
			name.equals(that.name) &&
			uebungsart.equals(that.uebungsart) &&
			uebungskategorie.equals(that.uebungskategorie) &&
			belastungsfaktor.equals(that.belastungsfaktor);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, name, uebungsart, uebungskategorie, belastungsfaktor);
	}
}
