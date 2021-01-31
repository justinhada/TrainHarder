package de.justinharder.trainharder.model.domain.embeddables;

import de.justinharder.trainharder.model.domain.enums.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@Embeddable
public class Benutzerangabe implements Serializable
{
	private static final long serialVersionUID = 9094368590669673429L;

	@Enumerated(EnumType.STRING)
	@Column(name = "Kraftlevel")
	private Kraftlevel kraftlevel = Kraftlevel.CLASS_5;
	@Enumerated(EnumType.STRING)
	@Column(name = "Geschlecht")
	private Geschlecht geschlecht;
	@Enumerated(EnumType.STRING)
	@Column(name = "Erfahrung")
	private Erfahrung erfahrung;
	@Enumerated(EnumType.STRING)
	@Column(name = "Ernaehrung")
	private Ernaehrung ernaehrung;
	@Enumerated(EnumType.STRING)
	@Column(name = "Schlafqualitaet")
	private Schlafqualitaet schlafqualitaet;
	@Enumerated(EnumType.STRING)
	@Column(name = "Stress")
	private Stress stress;
	@Enumerated(EnumType.STRING)
	@Column(name = "Doping")
	private Doping doping;
	@Enumerated(EnumType.STRING)
	@Column(name = "Regenerationsfaehigkeit")
	private Regenerationsfaehigkeit regenerationsfaehigkeit;

	public Benutzerangabe() {}

	public Benutzerangabe(
		@NonNull Geschlecht geschlecht,
		@NonNull Erfahrung erfahrung,
		@NonNull Ernaehrung ernaehrung,
		@NonNull Schlafqualitaet schlafqualitaet,
		@NonNull Stress stress,
		@NonNull Doping doping,
		@NonNull Regenerationsfaehigkeit regenerationsfaehigkeit)
	{
		this.geschlecht = geschlecht;
		this.erfahrung = erfahrung;
		this.ernaehrung = ernaehrung;
		this.schlafqualitaet = schlafqualitaet;
		this.stress = stress;
		this.doping = doping;
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
	}

	public Benutzerangabe setKraftlevel(@NonNull Kraftlevel kraftlevel)
	{
		this.kraftlevel = kraftlevel;
		return this;
	}

	public Benutzerangabe setGeschlecht(@NonNull Geschlecht geschlecht)
	{
		this.geschlecht = geschlecht;
		return this;
	}

	public Benutzerangabe setErfahrung(@NonNull Erfahrung erfahrung)
	{
		this.erfahrung = erfahrung;
		return this;
	}

	public Benutzerangabe setErnaehrung(@NonNull Ernaehrung ernaehrung)
	{
		this.ernaehrung = ernaehrung;
		return this;
	}

	public Benutzerangabe setSchlafqualitaet(@NonNull Schlafqualitaet schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
		return this;
	}

	public Benutzerangabe setStress(@NonNull Stress stress)
	{
		this.stress = stress;
		return this;
	}

	public Benutzerangabe setDoping(@NonNull Doping doping)
	{
		this.doping = doping;
		return this;
	}

	public Benutzerangabe setRegenerationsfaehigkeit(@NonNull Regenerationsfaehigkeit regenerationsfaehigkeit)
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
		if (!(o instanceof Benutzerangabe))
		{
			return false;
		}
		var that = (Benutzerangabe) o;
		return kraftlevel == that.kraftlevel
			&& geschlecht == that.geschlecht
			&& erfahrung == that.erfahrung
			&& ernaehrung == that.ernaehrung
			&& schlafqualitaet == that.schlafqualitaet
			&& stress == that.stress
			&& doping == that.doping
			&& regenerationsfaehigkeit == that.regenerationsfaehigkeit;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(kraftlevel, geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit);
	}
}
