package de.justinharder.trainharder.model.services.mail;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
public class Mail
{
	private final MailAdresse sender;
	private final List<MailAdresse> alleEmpfaenger = new ArrayList<>();
	private final List<MailAdresse> alleInKopie = new ArrayList<>();
	private final List<MailAdresse> alleInBlindkopie = new ArrayList<>();
	private final String betreff;
	private final String inhalt;

	public Mail(@NonNull MailAdresse sender, @NonNull String betreff, @NonNull String inhalt)
	{
		this.sender = sender;
		this.betreff = betreff;
		this.inhalt = inhalt;
	}

	public Mail fuegeEmpfaengerHinzu(MailAdresse... empfaenger)
	{
		alleEmpfaenger.addAll(Arrays.asList(empfaenger));
		return this;
	}

	public Mail fuegeInKopieHinzu(MailAdresse... inKopie)
	{
		alleInKopie.addAll(Arrays.asList(inKopie));
		return this;
	}

	public Mail fuegeInBlindkopieHinzu(MailAdresse... inBlindkopie)
	{
		alleInBlindkopie.addAll(Arrays.asList(inBlindkopie));
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Mail))
		{
			return false;
		}
		var mail = (Mail) o;
		return Objects.equals(sender, mail.sender)
			&& Objects.equals(alleEmpfaenger, mail.alleEmpfaenger)
			&& Objects.equals(alleInKopie, mail.alleInKopie)
			&& Objects.equals(alleInBlindkopie, mail.alleInBlindkopie)
			&& Objects.equals(betreff, mail.betreff)
			&& Objects.equals(inhalt, mail.inhalt);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(sender, alleEmpfaenger, alleInKopie, alleInBlindkopie, betreff, inhalt);
	}
}