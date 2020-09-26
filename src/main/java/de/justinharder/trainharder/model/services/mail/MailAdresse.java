package de.justinharder.trainharder.model.services.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MailAdresse
{
	private final String adresse;
	private String name;

	public MailAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	public MailAdresse(String adresse, String name)
	{
		this(adresse);
		this.name = name;
	}
}
