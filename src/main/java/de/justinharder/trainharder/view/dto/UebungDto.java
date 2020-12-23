package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
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
		@NonNull String primaerschluessel,
		@NonNull String name,
		@NonNull String uebungsart,
		@NonNull String uebungskategorie,
		@NonNull BelastungsfaktorDto belastungsfaktor)
	{
		super(primaerschluessel);
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastungsfaktor = belastungsfaktor;
	}

	@Override
	public UebungDto setPrimaerschluessel(@NonNull String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public UebungDto setName(@NonNull String name)
	{
		this.name = name;
		return this;
	}

	public UebungDto setUebungsart(@NonNull String uebungsart)
	{
		this.uebungsart = uebungsart;
		return this;
	}

	public UebungDto setUebungskategorie(@NonNull String uebungskategorie)
	{
		this.uebungskategorie = uebungskategorie;
		return this;
	}

	public UebungDto setBelastungsfaktor(@NonNull BelastungsfaktorDto belastungsfaktor)
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
