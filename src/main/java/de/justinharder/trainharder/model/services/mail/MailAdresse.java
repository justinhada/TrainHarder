package de.justinharder.trainharder.model.services.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailAdresse
{
	private final String adresse;
	private String name;
	
	public MailAdresse(String adresse)
	{
		this.adresse=adresse;
	}
}
