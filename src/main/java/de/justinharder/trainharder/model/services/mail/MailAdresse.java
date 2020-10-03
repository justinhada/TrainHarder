package de.justinharder.trainharder.model.services.mail;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
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
		MailAdresse that = (MailAdresse) o;
		return adresse.equals(that.adresse) && name.equals(that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(adresse, name);
	}
}
