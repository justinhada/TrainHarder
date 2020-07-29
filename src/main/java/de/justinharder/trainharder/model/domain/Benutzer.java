package de.justinharder.trainharder.model.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
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

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Embedded
	private Name name;
	@Column(name = "Geburtsdatum")
	private LocalDate geburtsdatum;
	@Embedded
	private Benutzerangabe benutzerangabe;
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "benutzer", cascade = CascadeType.MERGE)
	@JoinColumn(name = "AuthentifizierungID", nullable = false)
	private Authentifizierung authentifizierung;
	@Setter(value = AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benutzer", cascade = CascadeType.ALL)
	private List<Koerpermessung> koerpermessungen = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benutzer", cascade = CascadeType.ALL)
	private List<Kraftwert> kraftwerte = new ArrayList<>();

	public Benutzer(
		final Primaerschluessel primaerschluessel,
		final Name name,
		final LocalDate geburtsdatum,
		final Benutzerangabe benutzerangabe,
		final Authentifizierung authentifizierung)
	{
		this.primaerschluessel = primaerschluessel;
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.benutzerangabe = benutzerangabe;
		this.authentifizierung = authentifizierung;

		authentifizierung.setBenutzer(this);
	}

	public int getAlter()
	{
		return Period.between(geburtsdatum, LocalDate.now()).getYears();
	}

	public double getKoerpergewicht()
	{
		return koerpermessungen
			.stream()
			.reduce((first, second) -> second)
			.map(koerpermessung -> koerpermessung.getKoerpermasse().getKoerpergewicht())
			.orElseGet(() -> null);
	}

	public int getKoerpergroesse()
	{
		return koerpermessungen
			.stream()
			.reduce((first, second) -> second)
			.map(koerpermessung -> koerpermessung.getKoerpermasse().getKoerpergroesse())
			.orElseGet(() -> null);
	}

	public Benutzer setName(final Name name)
	{
		this.name = name;
		return this;
	}

	public Benutzer setGeburtsdatum(final LocalDate geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum;
		return this;
	}

	public Benutzer setBenutzerangabe(final Benutzerangabe benutzerangabe)
	{
		this.benutzerangabe = benutzerangabe;
		return this;
	}

	public Benutzer fuegeKraftwertHinzu(final Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
		return this;
	}

	public Benutzer fuegeKoerpermessungHinzu(final Koerpermessung koerpermessung)
	{
		koerpermessungen.add(koerpermessung);
		return this;
	}
}
