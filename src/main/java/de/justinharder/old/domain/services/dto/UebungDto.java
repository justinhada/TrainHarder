package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class UebungDto extends EntitaetDto
{
	@Serial
	private static final long serialVersionUID = -7055200842034977331L;

	private String name;
	private String uebungsart;
	private String uebungskategorie;
	private BelastungDto belastungsfaktor;

	public UebungDto()
	{}

	public UebungDto(@NonNull String id, @NonNull String name, @NonNull String uebungsart, @NonNull String uebungskategorie, @NonNull BelastungDto belastungsfaktor)
	{
		super(id);
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastungsfaktor = belastungsfaktor;
	}

	@Override
	public UebungDto setId(@NonNull String id)
	{
		this.id = id;
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

	public UebungDto setBelastungsfaktor(@NonNull BelastungDto belastungsfaktor)
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
		var uebungDto = (UebungDto) o;
		return Objects.equals(id, uebungDto.id)
			&& Objects.equals(name, uebungDto.name)
			&& Objects.equals(uebungsart, uebungDto.uebungsart)
			&& Objects.equals(uebungskategorie, uebungDto.uebungskategorie)
			&& Objects.equals(belastungsfaktor, uebungDto.belastungsfaktor);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, uebungsart, uebungskategorie, belastungsfaktor);
	}
}
