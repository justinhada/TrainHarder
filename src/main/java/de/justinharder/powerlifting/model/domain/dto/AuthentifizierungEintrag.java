package de.justinharder.powerlifting.model.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnmeldedatenEintrag
{
	private int id;
	private String mail;
	private String benutzername;
	private String passwort;
}
