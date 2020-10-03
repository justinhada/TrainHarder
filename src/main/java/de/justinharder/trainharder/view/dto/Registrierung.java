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
public class Registrierung
{
	@Email(message = "Ungültige E-Mail-Adresse!")
	@FormParam("mail")
	private String mail;
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	@FormParam("benutzername")
	private String benutzername;
	@Size(min = 12, max = 64, message = "Ungültiges Passwort!")
	@FormParam("passwort")
	private String passwort;

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
		Registrierung that = (Registrierung) o;
		return mail.equals(that.mail) && benutzername.equals(that.benutzername) && passwort.equals(that.passwort);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(mail, benutzername, passwort);
	}
}
