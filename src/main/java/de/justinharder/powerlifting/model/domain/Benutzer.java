package de.justinharder.powerlifting.model.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Benutzer")
public class Benutzer extends Entitaet
{
	private static final long serialVersionUID = 2411974948424821755L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Vorname")
	private String vorname;
	@Column(name = "Nachname")
	private String nachname;
	@Column(name = "Lebensalter")
	private int lebensalter;
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
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "benutzer", cascade = CascadeType.ALL)
	@JoinColumn(name = "AuthentifizierungID", nullable = false)
	private Authentifizierung authentifizierung;
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benutzer", cascade = CascadeType.ALL)
	private List<Koerpermessung> koerpermessungen = new ArrayList<>();
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benutzer", cascade = CascadeType.ALL)
	private List<Kraftwert> kraftwerte = new ArrayList<>();

	public Benutzer(
		final String vorname,
		final String nachname,
		final int lebensalter,
		final Geschlecht geschlecht,
		final Erfahrung erfahrung,
		final Ernaehrung ernaehrung,
		final Schlafqualitaet schlafqualitaet,
		final Stress stress,
		final Doping doping,
		final Regenerationsfaehigkeit regenerationsfaehigkeit,
		final Authentifizierung authentifizierung)
	{
		this.vorname = vorname;
		this.nachname = nachname;
		this.lebensalter = lebensalter;
		this.geschlecht = geschlecht;
		this.erfahrung = erfahrung;
		this.ernaehrung = ernaehrung;
		this.schlafqualitaet = schlafqualitaet;
		this.stress = stress;
		this.doping = doping;
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
		this.authentifizierung = authentifizierung;

		authentifizierung.setBenutzer(this);
	}

	public void setAuthentifizierung(final Authentifizierung authentifizierung)
	{
		this.authentifizierung = authentifizierung;
		authentifizierung.setBenutzer(this);
	}

	public void fuegeKraftwertHinzu(final Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
	}

	public void fuegeKoerpermessungHinzu(final Koerpermessung koerpermessung)
	{
		koerpermessungen.add(koerpermessung);
	}

	public double getAktuellesKoerpergewicht()
	{
		return koerpermessungen
			.stream()
			.reduce((first, second) -> second)
			.map(Koerpermessung::getKoerpergewicht)
			.orElse(null);
	}

	public int getAktuelleKoerpergroesse()
	{
		return koerpermessungen
			.stream()
			.reduce((first, second) -> second)
			.map(Koerpermessung::getKoerpergroesse)
			.orElse(null);
	}
}
