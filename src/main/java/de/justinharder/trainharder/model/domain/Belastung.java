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
@Table(name = "Belastung")
public class Belastung extends Entitaet
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

	public Belastung()
	{}

	public Belastung(
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

	public Belastung setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Belastung setGrunduebungBelastung(@NonNull GrunduebungBelastung grunduebungBelastung)
	{
		this.grunduebungBelastung = grunduebungBelastung;
		return this;
	}

	public Belastung setOberkoerperBelastung(@NonNull OberkoerperBelastung oberkoerperBelastung)
	{
		this.oberkoerperBelastung = oberkoerperBelastung;
		return this;
	}

	public Belastung setUnterkoerperBelastung(@NonNull UnterkoerperBelastung unterkoerperBelastung)
	{
		this.unterkoerperBelastung = unterkoerperBelastung;
		return this;
	}

	public Belastung setUebung(@NonNull Uebung uebung)
	{
		this.uebung = uebung;
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