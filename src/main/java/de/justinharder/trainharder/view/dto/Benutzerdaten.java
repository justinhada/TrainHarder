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
public class Benutzerdaten
{
	@Size(min = 2, max = 255, message = "Ungültiger Vorname!")
	@FormParam(value = "vorname")
	private String vorname;
	@Size(min = 2, max = 255, message = "Ungültiger Nachname!")
	@FormParam(value = "nachname")
	private String nachname;
	@Size(min = 10, max = 10, message = "Ungültiges Geburtsdatum!")
	@FormParam(value = "geburtsdatum")
	private String geburtsdatum;
	@FormParam(value = "geschlecht")
	private String geschlecht;
	@FormParam(value = "erfahrung")
	private String erfahrung;
	@FormParam(value = "ernaehrung")
	private String ernaehrung;
	@FormParam(value = "schlafqualitaet")
	private String schlafqualitaet;
	@FormParam(value = "stress")
	private String stress;
	@FormParam(value = "doping")
	private String doping;
	@FormParam(value = "regenerationsfaehigkeit")
	private String regenerationsfaehigkeit;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Benutzerdaten))
		{
			return false;
		}
		var that = (Benutzerdaten) o;
		return vorname.equals(that.vorname) &&
			nachname.equals(that.nachname) &&
			geburtsdatum.equals(that.geburtsdatum) &&
			geschlecht.equals(that.geschlecht) &&
			erfahrung.equals(that.erfahrung) &&
			ernaehrung.equals(that.ernaehrung) &&
			schlafqualitaet.equals(that.schlafqualitaet) &&
			stress.equals(that.stress) &&
			doping.equals(that.doping) &&
			regenerationsfaehigkeit.equals(that.regenerationsfaehigkeit);
	}

	@Override
	public int hashCode()
	{
		return Objects
			.hash(vorname, nachname, geburtsdatum, geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping,
				regenerationsfaehigkeit);
	}
}
