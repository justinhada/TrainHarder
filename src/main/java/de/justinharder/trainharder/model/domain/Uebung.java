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
import javax.persistence.Transient;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
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
	@Enumerated(EnumType.STRING)
	@Column(name = "Uebungsart")
	private Uebungsart uebungsart;
	@Enumerated(EnumType.STRING)
	@Column(name = "Uebungskategorie")
	private Uebungskategorie uebungskategorie;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	@JoinColumn(name = "BelastungsfaktorID", nullable = false)
	private Belastungsfaktor belastungsfaktor;
	@Transient
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	private final List<Kraftwert> kraftwerte = new ArrayList<>();

	public Uebung()
	{}

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

	public Uebung setPrimaerschluessel(final Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Uebung setName(final String name)
	{
		this.name = name;
		return this;
	}

	public Uebung setUebungsart(final Uebungsart uebungsart)
	{
		this.uebungsart = uebungsart;
		return this;
	}

	public Uebung setUebungskategorie(final Uebungskategorie uebungskategorie)
	{
		this.uebungskategorie = uebungskategorie;
		return this;
	}

	public Uebung setBelastungsfaktor(final Belastungsfaktor belastungsfaktor)
	{
		this.belastungsfaktor = belastungsfaktor;
		return this;
	}

	public Uebung fuegeKraftwertHinzu(final Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
		return this;
	}

	@Override
	public boolean equals(final Object obj)
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
