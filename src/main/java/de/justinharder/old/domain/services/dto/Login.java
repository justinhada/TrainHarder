package de.justinharder.old.domain.services.dto;

import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.*;

import java.util.Objects;

@Getter
@ToString
public class Login
{
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	@FormParam(value = "benutzername")
	private String benutzername;
	@Size(min = 12, max = 64, message = "Ungültiges Passwort!")
	@FormParam(value = "passwort")
	private String passwort;

	public Login()
	{}

	public Login(@NonNull String benutzername, @NonNull String passwort)
	{
		this.benutzername = benutzername;
		this.passwort = passwort;
	}

	public Login setBenutzername(@NonNull String benutzername)
	{
		this.benutzername = benutzername;
		return this;
	}

	public Login setPasswort(@NonNull String passwort)
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
		if (!(o instanceof Login))
		{
			return false;
		}
		var login = (Login) o;
		return Objects.equals(benutzername, login.benutzername) && Objects.equals(passwort, login.passwort);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(benutzername, passwort);
	}
}
