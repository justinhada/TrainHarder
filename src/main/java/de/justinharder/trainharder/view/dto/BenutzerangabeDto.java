package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class BenutzerangabeDto implements Serializable
{
	private static final long serialVersionUID = 8370877486946722552L;

	private String kraftlevel = "CLASS_5";
	private String geschlecht;
	private String erfahrung;
	private String ernaehrung;
	private String schlafqualitaet;
	private String stress;
	private String doping;
	private String regenerationsfaehigkeit;

	public BenutzerangabeDto()
	{}

	public BenutzerangabeDto(
		@NonNull String geschlecht,
		@NonNull String erfahrung,
		@NonNull String ernaehrung,
		@NonNull String schlafqualitaet,
		@NonNull String stress,
		@NonNull String doping,
		@NonNull String regenerationsfaehigkeit)
	{
		this.geschlecht = geschlecht;
		this.erfahrung = erfahrung;
		this.ernaehrung = ernaehrung;
		this.schlafqualitaet = schlafqualitaet;
		this.stress = stress;
		this.doping = doping;
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
	}

	public BenutzerangabeDto setKraftlevel(@NonNull String kraftlevel)
	{
		this.kraftlevel = kraftlevel;
		return this;
	}

	public BenutzerangabeDto setGeschlecht(@NonNull String geschlecht)
	{
		this.geschlecht = geschlecht;
		return this;
	}

	public BenutzerangabeDto setErfahrung(@NonNull String erfahrung)
	{
		this.erfahrung = erfahrung;
		return this;
	}

	public BenutzerangabeDto setErnaehrung(@NonNull String ernaehrung)
	{
		this.ernaehrung = ernaehrung;
		return this;
	}

	public BenutzerangabeDto setSchlafqualitaet(@NonNull String schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
		return this;
	}

	public BenutzerangabeDto setStress(@NonNull String stress)
	{
		this.stress = stress;
		return this;
	}

	public BenutzerangabeDto setDoping(@NonNull String doping)
	{
		this.doping = doping;
		return this;
	}

	public BenutzerangabeDto setRegenerationsfaehigkeit(@NonNull String regenerationsfaehigkeit)
	{
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof BenutzerangabeDto))
		{
			return false;
		}
		var that = (BenutzerangabeDto) o;
		return Objects.equals(kraftlevel, that.kraftlevel)
			&& Objects.equals(geschlecht, that.geschlecht)
			&& Objects.equals(erfahrung, that.erfahrung)
			&& Objects.equals(ernaehrung, that.ernaehrung)
			&& Objects.equals(schlafqualitaet, that.schlafqualitaet)
			&& Objects.equals(stress, that.stress)
			&& Objects.equals(doping, that.doping)
			&& Objects.equals(regenerationsfaehigkeit, that.regenerationsfaehigkeit);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(kraftlevel, geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit);
	}
}