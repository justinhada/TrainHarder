package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
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
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "benutzer", cascade = CascadeType.ALL)
	private List<Koerpermessung> koerpermessungen = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benutzer")
	private List<Kraftwert> kraftwerte = new ArrayList<>();

	public Benutzer()
	{}

	public Benutzer(
		@NonNull Primaerschluessel primaerschluessel,
		@NonNull Name name,
		@NonNull LocalDate geburtsdatum,
		@NonNull Benutzerangabe benutzerangabe,
		@NonNull Authentifizierung authentifizierung)
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
		return koerpermessungen.stream()
			.reduce((first, second) -> second)
			.map(koerpermessung -> koerpermessung.getKoerpermasse().getKoerpergewicht())
			.orElseGet(() -> null);
	}

	public int getKoerpergroesse()
	{
		return koerpermessungen.stream()
			.reduce((first, second) -> second)
			.map(koerpermessung -> koerpermessung.getKoerpermasse().getKoerpergroesse())
			.orElseGet(() -> null);
	}

	public Benutzer setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Benutzer setName(@NonNull Name name)
	{
		this.name = name;
		return this;
	}

	public Benutzer setGeburtsdatum(@NonNull LocalDate geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum;
		return this;
	}

	public Benutzer setBenutzerangabe(@NonNull Benutzerangabe benutzerangabe)
	{
		this.benutzerangabe = benutzerangabe;
		return this;
	}

	public Benutzer setAuthentifizierung(@NonNull Authentifizierung authentifizierung)
	{
		this.authentifizierung = authentifizierung;
		return this;
	}

	public Benutzer fuegeKraftwertHinzu(@NonNull Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
		return this;
	}

	public Benutzer fuegeKoerpermessungHinzu(@NonNull Koerpermessung koerpermessung)
	{
		koerpermessungen.add(koerpermessung);
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
