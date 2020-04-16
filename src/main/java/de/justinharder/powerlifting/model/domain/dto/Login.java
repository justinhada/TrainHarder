package de.justinharder.powerlifting.model.domain.dto;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import lombok.Data;

@Data
public class Login
{
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	@FormParam("benutzername")
	private String benutzername;
	@Size(min = 12, max = 64, message = "Ungültiges Passwort!")
	@FormParam("passwort")
	private String passwort;
	private String rememberMe;

	public boolean isRememberMe()
	{
		return rememberMe != null && rememberMe.equals("rememberMe");
	}
}
