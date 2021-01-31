package de.justinharder.trainharder.model.services.mail;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MailAdresse
{
	private String adresse;
	private String name;

	public MailAdresse(@NonNull String adresse)
	{
		this.adresse = adresse;
	}

	public MailAdresse(String adresse, @NonNull String name)
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
		if (!(o instanceof MailAdresse))
		{
			return false;
		}
		var that = (MailAdresse) o;
		return Objects.equals(adresse, that.adresse) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(adresse, name);
	}
}
