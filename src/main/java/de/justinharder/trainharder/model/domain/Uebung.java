package de.justinharder.trainharder.model.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Uebung")
public class Uebung extends Entitaet
{
	private static final long serialVersionUID = -452069613203642245L;

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Column(name = "Name", unique = true)
	private String name;
	@Column(name = "Uebungsart")
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
	@Column(name = "Uebungskategorie")
	@Enumerated(EnumType.STRING)
	private Uebungskategorie uebungskategorie;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	@JoinColumn(name = "BelastungsfaktorID", nullable = false)
	private Belastungsfaktor belastungsfaktor;
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	private List<Kraftwert> kraftwerte = new ArrayList<>();

	public Uebung(
		final Primaerschluessel primaerschluessel,
		final String name,
		final Uebungsart uebungsart,
		final Uebungskategorie uebungskategorie,
		final Belastungsfaktor belastungsfaktor)
	{
		this.primaerschluessel = primaerschluessel;
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastungsfaktor = belastungsfaktor;

		belastungsfaktor.setUebung(this);
	}
	
	public void fuegeKraftwertHinzu(final Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
	}
}
