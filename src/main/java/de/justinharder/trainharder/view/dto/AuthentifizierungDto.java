package de.justinharder.trainharder.view.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthentifizierungDto implements Serializable
{
	private static final long serialVersionUID = -2585152739995047225L;

	private String primaerschluessel; 
	private String mail;
	private String benutzername;
}
