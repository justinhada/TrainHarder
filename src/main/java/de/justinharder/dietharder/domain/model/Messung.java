package de.justinharder.dietharder.domain.model;

import com.google.common.base.MoreObjects;
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
public class Messung extends Datensatz
{
	@Serial
	private static final long serialVersionUID = 5576794915409907221L;

	public Messung(
		ID id,
		Datum datum,
		Koerpergewicht koerpergewicht,
		KoerperfettAnteil koerperfettAnteil,
		Benutzer benutzer)
	{
		super(id, datum, koerpergewicht, koerperfettAnteil, benutzer);
	}

	@Override
	public Messung setDatum(@NonNull Datum datum)
	{
		return (Messung) super.setDatum(datum);
	}

	@Override
	public Messung setKoerpergewicht(@NonNull Koerpergewicht koerpergewicht)
	{
		return (Messung) super.setKoerpergewicht(koerpergewicht);
	}

	@Override
	public Messung setKoerperfettAnteil(@NonNull KoerperfettAnteil koerperfettAnteil)
	{
		return (Messung) super.setKoerperfettAnteil(koerperfettAnteil);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Datum", datum)
			.add("Koerpergewicht", koerpergewicht)
			.add("KoerperfettAnteil", koerperfettAnteil)
			.add("Benutzer", benutzer)
			.toString();
	}
}
