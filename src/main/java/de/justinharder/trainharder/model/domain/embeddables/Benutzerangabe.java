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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Benutzerangabe implements Serializable
{
	private static final long serialVersionUID = 9094368590669673429L;

	@Column(name = "Kraftlevel")
	@Enumerated(EnumType.STRING)
	private Kraftlevel kraftlevel = Kraftlevel.CLASS_5;
	@Column(name = "Geschlecht")
	@Enumerated(EnumType.STRING)
	private Geschlecht geschlecht;
	@Column(name = "Erfahrung")
	@Enumerated(EnumType.STRING)
	private Erfahrung erfahrung;
	@Column(name = "Ernaehrung")
	@Enumerated(EnumType.STRING)
	private Ernaehrung ernaehrung;
	@Column(name = "Schlafqualitaet")
	@Enumerated(EnumType.STRING)
	private Schlafqualitaet schlafqualitaet;
	@Column(name = "Stress")
	@Enumerated(EnumType.STRING)
	private Stress stress;
	@Column(name = "Doping")
	@Enumerated(EnumType.STRING)
	private Doping doping;
	@Column(name = "Regenerationsfaehigkeit")
	@Enumerated(EnumType.STRING)
	private Regenerationsfaehigkeit regenerationsfaehigkeit;

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
