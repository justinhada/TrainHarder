package de.justinharder.powerlifting.model.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Uebung extends Entitaet
{
	private static final long serialVersionUID = -452069613203642245L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
	@Enumerated(EnumType.STRING)
	private Uebungskategorie uebungskategorie;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	private Belastungsfaktor belastungsfaktor;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uebung", cascade = CascadeType.ALL)
	private List<Kraftwert> kraftwerte = new ArrayList<>();

	public Uebung(
		final String name,
		final Uebungsart uebungsart,
		final Uebungskategorie uebungskategorie,
		final Belastungsfaktor belastungsfaktor)
	{
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
