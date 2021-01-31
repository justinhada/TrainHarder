package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
	private Belastung belastung;
	@Transient
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	private final List<Kraftwert> kraftwerte = new ArrayList<>();

	public Uebung()
	{}

	public Uebung(@NonNull Primaerschluessel primaerschluessel, @NonNull String name, @NonNull Uebungsart uebungsart, @NonNull Uebungskategorie uebungskategorie, @NonNull Belastung belastung)
	{
		this.primaerschluessel = primaerschluessel;
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastung = belastung;

		belastung.setUebung(this);
	}

	public Uebung setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Uebung setName(@NonNull String name)
	{
		this.name = name;
		return this;
	}

	public Uebung setUebungsart(@NonNull Uebungsart uebungsart)
	{
		this.uebungsart = uebungsart;
		return this;
	}

	public Uebung setUebungskategorie(@NonNull Uebungskategorie uebungskategorie)
	{
		this.uebungskategorie = uebungskategorie;
		return this;
	}

	public Uebung setBelastung(@NonNull Belastung belastung)
	{
		this.belastung = belastung;
		return this;
	}

	public Uebung fuegeKraftwertHinzu(@NonNull Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
