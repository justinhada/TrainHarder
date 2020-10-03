package de.justinharder.trainharder.view.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Objects;

@Getter
@Setter
@ToString
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Kontaktformular that = (Kontaktformular) o;
		return mail.equals(that.mail) &&
			benutzername.equals(that.benutzername) &&
			vorname.equals(that.vorname) &&
			nachname.equals(that.nachname) &&
			nachricht.equals(that.nachricht);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(mail, benutzername, vorname, nachname, nachricht);
	}
}
