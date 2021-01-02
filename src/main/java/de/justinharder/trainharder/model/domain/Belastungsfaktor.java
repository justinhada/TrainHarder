package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.model.domain.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.embeddables.UnterkoerperBelastung;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "Belastungsfaktor")
public class Belastungsfaktor extends Entitaet
{
	private static final long serialVersionUID = 5251603922467033680L;

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
	@Embedded
	private GrunduebungBelastung grunduebungBelastung;
	@Embedded
	private OberkoerperBelastung oberkoerperBelastung;
	@Embedded
	private UnterkoerperBelastung unterkoerperBelastung;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "UebungID", nullable = false)
	private Uebung uebung;

	public Belastungsfaktor()
	{}

	public Belastungsfaktor(
		@NonNull Primaerschluessel primaerschluessel,
		@NonNull GrunduebungBelastung grunduebungBelastung,
		@NonNull OberkoerperBelastung oberkoerperBelastung,
		@NonNull UnterkoerperBelastung unterkoerperBelastung)
	{
		this.primaerschluessel = primaerschluessel;
		this.grunduebungBelastung = grunduebungBelastung;
		this.oberkoerperBelastung = oberkoerperBelastung;
		this.unterkoerperBelastung = unterkoerperBelastung;
	}

	public Belastungsfaktor setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Belastungsfaktor setGrunduebungBelastung(@NonNull GrunduebungBelastung grunduebungBelastung)
	{
		this.grunduebungBelastung = grunduebungBelastung;
		return this;
	}

	public Belastungsfaktor setOberkoerperBelastung(@NonNull OberkoerperBelastung oberkoerperBelastung)
	{
		this.oberkoerperBelastung = oberkoerperBelastung;
		return this;
	}

	public Belastungsfaktor setUnterkoerperBelastung(@NonNull UnterkoerperBelastung unterkoerperBelastung)
	{
		this.unterkoerperBelastung = unterkoerperBelastung;
		return this;
	}

	public Belastungsfaktor setUebung(@NonNull Uebung uebung)
	{
		this.uebung = uebung;
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
