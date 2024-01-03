package de.justinharder.trainharder.domain.services.dto;

import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.*;

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
		return Objects.equals(vorname, that.vorname)
			&& Objects.equals(nachname, that.nachname)
			&& Objects.equals(geburtsdatum, that.geburtsdatum)
			&& Objects.equals(geschlecht, that.geschlecht)
			&& Objects.equals(erfahrung, that.erfahrung)
			&& Objects.equals(ernaehrung, that.ernaehrung)
			&& Objects.equals(schlafqualitaet, that.schlafqualitaet)
			&& Objects.equals(stress, that.stress)
			&& Objects.equals(doping, that.doping)
			&& Objects.equals(regenerationsfaehigkeit, that.regenerationsfaehigkeit);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(vorname, nachname, geburtsdatum, geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit);
	}
}
