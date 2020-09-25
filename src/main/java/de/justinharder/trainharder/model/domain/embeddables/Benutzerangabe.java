package de.justinharder.trainharder.model.domain.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
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

	public Benutzerangabe()
	{}

	public Benutzerangabe(
		final Geschlecht geschlecht,
		final Erfahrung erfahrung,
		final Ernaehrung ernaehrung,
		final Schlafqualitaet schlafqualitaet,
		final Stress stress,
		final Doping doping,
		final Regenerationsfaehigkeit regenerationsfaehigkeit)
	{
		this.geschlecht = geschlecht;
		this.erfahrung = erfahrung;
		this.ernaehrung = ernaehrung;
		this.schlafqualitaet = schlafqualitaet;
		this.stress = stress;
		this.doping = doping;
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
	}

	public Benutzerangabe setKraftlevel(final Kraftlevel kraftlevel)
	{
		this.kraftlevel = kraftlevel;
		return this;
	}

	public Benutzerangabe setGeschlecht(final Geschlecht geschlecht)
	{
		this.geschlecht = geschlecht;
		return this;
	}

	public Benutzerangabe setErfahrung(final Erfahrung erfahrung)
	{
		this.erfahrung = erfahrung;
		return this;
	}

	public Benutzerangabe setErnaehrung(final Ernaehrung ernaehrung)
	{
		this.ernaehrung = ernaehrung;
		return this;
	}

	public Benutzerangabe setSchlafqualitaet(final Schlafqualitaet schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
		return this;
	}

	public Benutzerangabe setStress(final Stress stress)
	{
		this.stress = stress;
		return this;
	}

	public Benutzerangabe setDoping(final Doping doping)
	{
		this.doping = doping;
		return this;
	}

	public Benutzerangabe setRegenerationsfaehigkeit(final Regenerationsfaehigkeit regenerationsfaehigkeit)
	{
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
		return this;
	}
}
