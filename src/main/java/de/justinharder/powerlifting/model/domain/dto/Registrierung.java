package de.justinharder.powerlifting.model.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registrierung implements Serializable
{
	private static final long serialVersionUID = -1678018986473544366L;

	@Size(min = 6, max = 255, message = "Ungültige E-Mail-Adresse!")
	private String mail;
	@Size(min = 3, max = 30, message = "Ungültiger Benutzername!")
	private String benutzername;
	@Size(min = 12, max = 64, message = "Ungültiges Passwort!")
	private String passwort;
}
