package de.justinharder.trainharder.model.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
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

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Column(name = "Datum")
	private LocalDate datum;
	@Embedded
	private Koerpermasse koerpermasse;
	@Column(name = "EingenommeneKalorien")
	private int eingenommeneKalorien;
	@Column(name = "VerbrannteKalorien")
	private int verbrannteKalorien;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Koerpermessung(
		final Primaerschluessel primaerschluessel,
		final LocalDate datum,
		final Koerpermasse koerpermasse,
		final int eingenommeneKalorien,
		final int verbrannteKalorien,
		final Benutzer benutzer)
	{
		this.primaerschluessel = primaerschluessel;
		this.datum = datum;
		this.koerpermasse = koerpermasse;
		this.eingenommeneKalorien = eingenommeneKalorien;
		this.verbrannteKalorien = verbrannteKalorien;
		this.benutzer = benutzer;

		benutzer.fuegeKoerpermessungHinzu(this);
	}
}
