package de.justinharder.trainharder.view.dto;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
