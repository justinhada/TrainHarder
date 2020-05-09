package de.justinharder.powerlifting.model.domain;

import java.time.LocalDate;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.justinharder.powerlifting.model.domain.enums.Wiederholungen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Kraftwert")
public class Kraftwert extends Entitaet
{
	private static final long serialVersionUID = -1203157961547955006L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Maximum")
	private int maximum;
	@Column(name = "Koerpergewicht")
	private double koerpergewicht;
	@Column(name = "Datum")
	private LocalDate datum;
	@Column(name = "Wiederholungen")
	@Enumerated(EnumType.STRING)
	private Wiederholungen wiederholungen;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "UebungID", nullable = false)
	private Uebung uebung;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Kraftwert(
		final int maximum,
		final double koerpergewicht,
		final LocalDate datum,
		final Wiederholungen wiederholungen,
		final Uebung uebung,
		final Benutzer benutzer)
	{
		this.maximum = maximum;
		this.koerpergewicht = koerpergewicht;
		this.datum = datum;
		this.wiederholungen = wiederholungen;
		this.uebung = uebung;
		this.benutzer = benutzer;

		benutzer.fuegeKraftwertHinzu(this);
		uebung.fuegeKraftwertHinzu(this);
	}
}
