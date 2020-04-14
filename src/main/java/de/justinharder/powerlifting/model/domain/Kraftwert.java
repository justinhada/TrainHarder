package de.justinharder.powerlifting.model.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import de.justinharder.powerlifting.model.domain.enums.Wiederholungen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Kraftwert extends Entitaet
{
	private static final long serialVersionUID = -1203157961547955006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int maximum;
	private double koerpergewicht;
	private LocalDate datum;
	private Wiederholungen wiederholungen;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(nullable = false)
	private Uebung uebung;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(nullable = false)
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
