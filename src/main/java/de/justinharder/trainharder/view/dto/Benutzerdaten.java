package de.justinharder.trainharder.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@Data
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
}
