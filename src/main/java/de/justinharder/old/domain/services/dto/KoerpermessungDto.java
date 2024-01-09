package de.justinharder.old.domain.services.dto;

import lombok.*;

import java.io.Serial;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class KoerpermessungDto extends EntitaetDto
{
	@Serial
	private static final long serialVersionUID = 46915464001880978L;

	private String datum;
	private KoerpermasseDto koerpermasse;
	private int kalorieneinnahme;
	private int kalorienverbrauch;

	public KoerpermessungDto() {}

	public KoerpermessungDto(@NonNull String id, @NonNull String datum, @NonNull KoerpermasseDto koerpermasse, int kalorieneinnahme, int kalorienverbrauch)
	{
		super(id);
		this.datum = datum;
		this.koerpermasse = koerpermasse;
		this.kalorieneinnahme = kalorieneinnahme;
		this.kalorienverbrauch = kalorienverbrauch;
	}

	@Override
	public KoerpermessungDto setId(@NonNull String id)
	{
		this.id = id;
		return this;
	}

	public KoerpermessungDto setDatum(@NonNull String datum)
	{
		this.datum = datum;
		return this;
	}

	public KoerpermessungDto setKoerpermasse(@NonNull KoerpermasseDto koerpermasse)
	{
		this.koerpermasse = koerpermasse;
		return this;
	}

	public KoerpermessungDto setKalorieneinnahme(int kalorieneinnahme)
	{
		this.kalorieneinnahme = kalorieneinnahme;
		return this;
	}

	public KoerpermessungDto setKalorienverbrauch(int kalorienverbrauch)
	{
		this.kalorienverbrauch = kalorienverbrauch;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof KoerpermessungDto))
		{
			return false;
		}
		var that = (KoerpermessungDto) o;
		return Objects.equals(id, that.id)
			&& kalorieneinnahme == that.kalorieneinnahme
			&& kalorienverbrauch == that.kalorienverbrauch
			&& Objects.equals(datum, that.datum)
			&& Objects.equals(koerpermasse, that.koerpermasse);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, datum, koerpermasse, kalorieneinnahme, kalorienverbrauch);
	}
}
