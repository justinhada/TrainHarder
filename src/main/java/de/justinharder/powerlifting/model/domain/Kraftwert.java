package de.justinharder.powerlifting.model.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import de.justinharder.powerlifting.model.Entitaet;
import de.justinharder.powerlifting.model.domain.enums.Wiederholungen;
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
public class Kraftwert extends Entitaet
{
	private static final long serialVersionUID = -1203157961547955006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Uebung uebung;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Benutzer benutzer;
	private int maximum;
	private int gewichtBenutzer;
	private LocalDate datum;
	private Wiederholungen wiederholungen;

	public Kraftwert(
		final Uebung uebung,
		final Benutzer benutzer,
		final int maximum,
		final int gewichtBenutzer,
		final LocalDate datum,
		final Wiederholungen kraftwertOption)
	{
		this.uebung = uebung;
		this.benutzer = benutzer;
		this.maximum = maximum;
		this.gewichtBenutzer = gewichtBenutzer;
		this.datum = datum;
		this.wiederholungen = kraftwertOption;
	}
}
