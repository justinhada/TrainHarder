package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Objects;

@Getter
@ToString
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

	public Registrierung()
	{}

	public Registrierung(@NonNull String mail, @NonNull String benutzername, @NonNull String passwort)
	{
		this.mail = mail;
		this.benutzername = benutzername;
		this.passwort = passwort;
	}

	public Registrierung setMail(@NonNull String mail)
	{
		this.mail = mail;
		return this;
	}

	public Registrierung setBenutzername(@NonNull String benutzername)
	{
		this.benutzername = benutzername;
		return this;
	}

	public Registrierung setPasswort(@NonNull String passwort)
	{
		this.passwort = passwort;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Registrierung))
		{
			return false;
		}
		var that = (Registrierung) o;
		return mail.equals(that.mail) && benutzername.equals(that.benutzername) && passwort.equals(that.passwort);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(mail, benutzername, passwort);
	}
}
