package de.justinharder.powerlifting.model.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.justinharder.powerlifting.model.Entitaet;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Benutzer extends Entitaet
{
	private static final long serialVersionUID = 2411974948424821755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String vorname;
	private String nachname;
	private int koerpergewicht;
	private int koerpergroesse;
	private int lebensalter;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Map<Integer, Kraftwert> kraftwerte = new HashMap<>();
	@Enumerated(EnumType.STRING)
	private Kraftlevel kraftlevel;
	@Enumerated(EnumType.STRING)
	private Geschlecht geschlecht;
	@Enumerated(EnumType.STRING)
	private Erfahrung erfahrung;
	@Enumerated(EnumType.STRING)
	private Ernaehrung ernaehrung;
	@Enumerated(EnumType.STRING)
	private Schlafqualitaet schlafqualitaet;
	@Enumerated(EnumType.STRING)
	private Stress stress;
	@Enumerated(EnumType.STRING)
	private Doping doping;
	@Enumerated(EnumType.STRING)
	private Regenerationsfaehigkeit regenerationsfaehigkeit;

	public Benutzer(final String vorname, final String nachname, final int koerpergewicht, final int koerpergroesse,
		final int lebensalter, final Kraftlevel kraftlevel, final Geschlecht geschlecht, final Erfahrung erfahrung,
		final Ernaehrung ernaehrung, final Schlafqualitaet schlafqualitaet, final Stress stress, final Doping doping,
		final Regenerationsfaehigkeit regenerationsfaehigkeit)
	{
		this.vorname = vorname;
		this.nachname = nachname;
		this.koerpergewicht = koerpergewicht;
		this.koerpergroesse = koerpergroesse;
		this.lebensalter = lebensalter;
		this.kraftlevel = kraftlevel;
		this.geschlecht = geschlecht;
		this.erfahrung = erfahrung;
		this.ernaehrung = ernaehrung;
		this.schlafqualitaet = schlafqualitaet;
		this.stress = stress;
		this.doping = doping;
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
	}

	public void fuegeKraftwertHinzu(final Kraftwert kraftwert)
	{
		kraftwerte.put(kraftwert.getId(), kraftwert);
	}

}
