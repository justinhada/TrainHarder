package de.justinharder.powerlifting.model.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Koerpermessung")
public class Koerpermessung extends Entitaet
{
	private static final long serialVersionUID = -6355583837778945437L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Koerpergroesse")
	private int koerpergroesse;
	@Column(name = "Koerpergewicht")
	private double koerpergewicht;
	@Column(name = "BodyMassIndex")
	private double bodyMassIndex;
	@Column(name = "FatFreeMassIndex")
	private double fatFreeMassIndex;
	@Column(name = "KoerperfettAnteil")
	private double koerperfettAnteil;
	@Column(name = "FettfreiesKoerpergewicht")
	private double fettfreiesKoerpergewicht;
	@Column(name = "SubkutanesFett")
	private double subkutanesFett;
	@Column(name = "Viszeralfett")
	private double viszeralfett;
	@Column(name = "Koerperwasser")
	private double koerperwasser;
	@Column(name = "Skelettmuskel")
	private double skelettmuskel;
	@Column(name = "Muskelmasse")
	private double muskelmasse;
	@Column(name = "Knochenmasse")
	private double knochenmasse;
	@Column(name = "Protein")
	private double protein;
	@Column(name = "Grundumsatz")
	private int grundumsatz;
	@Column(name = "EingenommeneKalorien")
	private int eingenommeneKalorien;
	@Column(name = "VerbrannteKalorien")
	private int verbrannteKalorien;
	@Column(name = "BiologischesAlter")
	private int biologischesAlter;
	@Column(name = "Datum")
	private LocalDate datum;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Koerpermessung(
		final int koerpergroesse,
		final double koerpergewicht,
		final double koerperfettAnteil,
		final double fettfreiesKoerpergewicht,
		final double subkutanesFett,
		final double viszeralfett,
		final double koerperwasser,
		final double skelettmuskel,
		final double muskelmasse,
		final double knochenmasse,
		final double protein,
		final int grundumsatz,
		final int eingenommeneKalorien,
		final int verbrannteKalorien,
		final int biologischesAlter,
		final LocalDate datum,
		final Benutzer benutzer)
	{
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
		this.koerperfettAnteil = koerperfettAnteil;
		this.fettfreiesKoerpergewicht = fettfreiesKoerpergewicht;
		this.subkutanesFett = subkutanesFett;
		this.viszeralfett = viszeralfett;
		this.koerperwasser = koerperwasser;
		this.skelettmuskel = skelettmuskel;
		this.muskelmasse = muskelmasse;
		this.knochenmasse = knochenmasse;
		this.protein = protein;
		this.grundumsatz = grundumsatz;
		this.eingenommeneKalorien = eingenommeneKalorien;
		this.verbrannteKalorien = verbrannteKalorien;
		this.biologischesAlter = biologischesAlter;
		this.datum = datum;
		this.benutzer = benutzer;

		benutzer.fuegeKoerpermessungHinzu(this);
	}

	private double berechneBmi(final int koerpergroesse, final double koerpergewicht)
	{
		final var bmi = koerpergewicht / Math.pow(koerpergroesse / 100.0, 2);
		return Math.round(bmi * 100) / 100.0;
	}

	private double berechneFfmi(final double koerpergewicht, final double koerperfettAnteil, final int koerpergroesse)
	{
		final var magermasse = koerpergewicht * (1 - koerperfettAnteil / 100.0);
		final var ffmi = magermasse / Math.pow(koerpergroesse / 100.0, 2) + 6.1 * (1.8 - koerpergroesse / 100.0);
		return Math.round(ffmi * 100) / 100.0;
	}

	public void setKoerpergroesse(final int koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
	}

	public void setKoerpergewicht(final double koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);
	}

	public void setKoerperfettAnteil(final double koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		bodyMassIndex = berechneBmi(koerpergroesse, koerpergewicht);
		fatFreeMassIndex = berechneFfmi(koerpergewicht, koerperfettAnteil, koerpergroesse);

	}
}
