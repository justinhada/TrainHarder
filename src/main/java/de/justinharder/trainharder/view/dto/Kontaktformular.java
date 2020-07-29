package de.justinharder.trainharder.view.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kontaktformular
{
	@Email(message = "Ungültige E-Mail-Adresse!")
	@FormParam("mail")
	private String mail;
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	@FormParam("benutzername")
	private String benutzername;
	@Size(min = 2, max = 255, message = "Ungültiger Vorname!")
	@FormParam("vorname")
	private String vorname;
	@Size(min = 2, max = 255, message = "Ungültiger Nachname!")
	@FormParam("nachname")
	private String nachname;
	@FormParam("nachricht")
	private String nachricht;
}
