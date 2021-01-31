package de.justinharder.trainharder.view.dto;

import lombok.*;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Koerpermessdaten
{
	@Size(min = 10, max = 10, message = "Ungültiges Datum!")
	@FormParam(value = "datum")
	private String datum;
	@FormParam(value = "koerpergroesse")
	private int koerpergroesse;
	@FormParam(value = "koerpergewicht")
	private double koerpergewicht;
	@FormParam(value = "koerperfettAnteil")
	private double koerperfettAnteil;
	@FormParam(value = "kalorieneinnahme")
	private int kalorieneinnahme;
	@FormParam(value = "kalorienverbrauch")
	private int kalorienverbrauch;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Koerpermessdaten))
		{
			return false;
		}
		var that = (Koerpermessdaten) o;
		return koerpergroesse == that.koerpergroesse
			&& Double.compare(that.koerpergewicht, koerpergewicht) == 0
			&& Double.compare(that.koerperfettAnteil, koerperfettAnteil) == 0
			&& kalorieneinnahme == that.kalorieneinnahme
			&& kalorienverbrauch == that.kalorienverbrauch
			&& Objects.equals(datum, that.datum);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(datum, koerpergroesse, koerpergewicht, koerperfettAnteil, kalorieneinnahme, kalorienverbrauch);
	}
}