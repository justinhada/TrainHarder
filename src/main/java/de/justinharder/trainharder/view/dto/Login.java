package de.justinharder.trainharder.view.dto;

import lombok.*;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Objects;

@Getter
@ToString
public class Login
{
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	@FormParam("benutzername")
	private String benutzername;
	@Size(min = 12, max = 64, message = "Ungültiges Passwort!")
	@FormParam("passwort")
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
		var that = (Login) o;
		return benutzername.equals(that.benutzername) && passwort.equals(that.passwort);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(benutzername, passwort);
	}
}
