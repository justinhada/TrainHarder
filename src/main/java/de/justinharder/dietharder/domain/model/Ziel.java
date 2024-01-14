package de.justinharder.dietharder.domain.model;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ziel extends Datensatz
{
	@Serial
	private static final long serialVersionUID = 3498999783670992134L;

	public Ziel(
		ID id,
		Datum datum,
		Koerpergewicht koerpergewicht,
		KoerperfettAnteil koerperfettAnteil,
		Benutzer benutzer)
	{
		super(id, datum, koerpergewicht, koerperfettAnteil, benutzer);
	}

	@Override
	public Ziel setDatum(@NonNull Datum datum)
	{
		return (Ziel) super.setDatum(datum);
	}

	@Override
	public Ziel setKoerpergewicht(@NonNull Koerpergewicht koerpergewicht)
	{
		return (Ziel) super.setKoerpergewicht(koerpergewicht);
	}

	@Override
	public Ziel setKoerperfettAnteil(@NonNull KoerperfettAnteil koerperfettAnteil)
	{
		return (Ziel) super.setKoerperfettAnteil(koerperfettAnteil);
	}
}
