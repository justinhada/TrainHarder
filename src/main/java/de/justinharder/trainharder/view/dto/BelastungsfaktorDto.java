package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class BelastungsfaktorDto extends Dto
{
	private static final long serialVersionUID = -2050880743329267381L;

	private GrunduebungBelastungDto grunduebungBelastung;
	private OberkoerperBelastungDto oberkoerperBelastung;
	private UnterkoerperBelastungDto unterkoerperBelastung;

	public BelastungsfaktorDto()
	{}

	public BelastungsfaktorDto(
		@NonNull String primaerschluessel,
		@NonNull GrunduebungBelastungDto grunduebungBelastung,
		@NonNull OberkoerperBelastungDto oberkoerperBelastung,
		@NonNull UnterkoerperBelastungDto unterkoerperBelastung)
	{
		super(primaerschluessel);
		this.grunduebungBelastung = grunduebungBelastung;
		this.oberkoerperBelastung = oberkoerperBelastung;
		this.unterkoerperBelastung = unterkoerperBelastung;
	}

	@Override
	public BelastungsfaktorDto setPrimaerschluessel(@NonNull String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public BelastungsfaktorDto setGrunduebungBelastung(@NonNull GrunduebungBelastungDto grunduebungBelastung)
	{
		this.grunduebungBelastung = grunduebungBelastung;
		return this;
	}

	public BelastungsfaktorDto setOberkoerperBelastung(@NonNull OberkoerperBelastungDto oberkoerperBelastung)
	{
		this.oberkoerperBelastung = oberkoerperBelastung;
		return this;
	}

	public BelastungsfaktorDto setUnterkoerperBelastung(@NonNull UnterkoerperBelastungDto unterkoerperBelastung)
	{
		this.unterkoerperBelastung = unterkoerperBelastung;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof BelastungsfaktorDto))
		{
			return false;
		}
		var that = (BelastungsfaktorDto) o;
		return Objects.equals(primaerschluessel, that.primaerschluessel)
			&& Objects.equals(grunduebungBelastung, that.grunduebungBelastung)
			&& Objects.equals(oberkoerperBelastung, that.oberkoerperBelastung)
			&& Objects.equals(unterkoerperBelastung, that.unterkoerperBelastung);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, grunduebungBelastung, oberkoerperBelastung, unterkoerperBelastung);
	}
}
