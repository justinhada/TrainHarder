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
public class Login
{
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
		Login login = (Login) o;
		return benutzername.equals(login.benutzername) && passwort.equals(login.passwort);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(benutzername, passwort);
	}
}
