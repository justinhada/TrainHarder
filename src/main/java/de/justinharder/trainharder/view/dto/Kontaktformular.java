package de.justinharder.trainharder.view.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Kontaktformular
{
	@Email(message = "Ungültige E-Mail-Adresse!")
	@FormParam(value = "mail")
	private String mail;
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	@FormParam(value = "benutzername")
	private String benutzername;
	@Size(min = 2, max = 255, message = "Ungültiger Vorname!")
	@FormParam(value = "vorname")
	private String vorname;
	@Size(min = 2, max = 255, message = "Ungültiger Nachname!")
	@FormParam(value = "nachname")
	private String nachname;
	@FormParam(value = "nachricht")
	private String nachricht;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Kontaktformular))
		{
			return false;
		}
		var that = (Kontaktformular) o;
		return Objects.equals(mail, that.mail)
			&& Objects.equals(benutzername, that.benutzername)
			&& Objects.equals(vorname, that.vorname)
			&& Objects.equals(nachname, that.nachname)
			&& Objects.equals(nachricht, that.nachricht);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(mail, benutzername, vorname, nachname, nachricht);
	}
}
