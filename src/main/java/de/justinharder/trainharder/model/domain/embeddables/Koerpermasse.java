package de.justinharder.trainharder.model.domain.embeddables;

import de.justinharder.trainharder.model.services.berechnung.koerpermasse.BodyMassIndex;
import de.justinharder.trainharder.model.services.berechnung.koerpermasse.FatFreeMassIndex;
import de.justinharder.trainharder.model.services.berechnung.koerpermasse.FettfreiesKoerpergewicht;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
@Embeddable
public class Koerpermasse implements Serializable
{
	private static final long serialVersionUID = 3836150830695827628L;

	@Column(name = "Koerpergroesse", precision = 7, scale = 2)
	private BigDecimal koerpergroesse;
	@Column(name = "Koerpergewicht", precision = 7, scale = 2)
	private BigDecimal koerpergewicht;
	@Column(name = "KoerperfettAnteil", precision = 7, scale = 2)
	private BigDecimal koerperfettAnteil;
	@Column(name = "FettfreiesKoerpergewicht", precision = 7, scale = 2)
	private BigDecimal fettfreiesKoerpergewicht;
	@Column(name = "BodyMassIndex", precision = 7, scale = 2)
	private BigDecimal bodyMassIndex;
	@Column(name = "FatFreeMassIndex", precision = 7, scale = 2)
	private BigDecimal fatFreeMassIndex;

	public Koerpermasse()
	{}

	public Koerpermasse(@NonNull BigDecimal koerpergroesse, @NonNull BigDecimal koerpergewicht, @NonNull BigDecimal koerperfettAnteil)
	{
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;
		berechneDaten();
	}

	public Koerpermasse setKoerpergroesse(@NonNull BigDecimal koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		berechneDaten();
		return this;
	}

	public Koerpermasse setKoerpergewicht(@NonNull BigDecimal koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		berechneDaten();
		return this;
	}

	public Koerpermasse setKoerperfettAnteil(@NonNull BigDecimal koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		berechneDaten();
		return this;
	}

	private void berechneDaten()
	{
		fettfreiesKoerpergewicht = FettfreiesKoerpergewicht.aus(koerpergewicht, koerperfettAnteil);
		bodyMassIndex = BodyMassIndex.aus(koerpergewicht, koerpergroesse);
		fatFreeMassIndex = FatFreeMassIndex.aus(koerpergewicht, koerperfettAnteil, koerpergroesse);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Koerpermasse))
		{
			return false;
		}
		var that = (Koerpermasse) o;
		return Objects.equals(koerpergroesse, that.koerpergroesse)
			&& Objects.equals(koerpergewicht, that.koerpergewicht)
			&& Objects.equals(koerperfettAnteil, that.koerperfettAnteil)
			&& Objects.equals(fettfreiesKoerpergewicht, that.fettfreiesKoerpergewicht)
			&& Objects.equals(bodyMassIndex, that.bodyMassIndex)
			&& Objects.equals(fatFreeMassIndex, that.fatFreeMassIndex);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(koerpergroesse, koerpergewicht, koerperfettAnteil, fettfreiesKoerpergewicht, bodyMassIndex, fatFreeMassIndex);
	}
}