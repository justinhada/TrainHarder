package de.justinharder.powerlifting.model.domain.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthentifizierungEintrag implements Serializable
{
	private static final long serialVersionUID = -2585152739995047225L;

	private int id;
	private String mail;
	private String benutzername;
	private String passwort;
	private Set<String> gruppen;
}
