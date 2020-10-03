package de.justinharder.trainharder.model.services.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@ToString
@EqualsAndHashCode
public class Mail
{
	private final MailAdresse sender;
	private final List<MailAdresse> alleEmpfaenger = new ArrayList<>();
	private final List<MailAdresse> alleInKopie = new ArrayList<>();
	private final List<MailAdresse> alleInBlindkopie = new ArrayList<>();
	private final String betreff;
	private final String inhalt;

	public Mail(final MailAdresse sender, final String betreff, final String inhalt)
	{
		this.sender = sender;
		this.betreff = betreff;
		this.inhalt = inhalt;
	}

	public Mail fuegeEmpfaengerHinzu(final MailAdresse... empfaenger)
	{
		Stream.of(empfaenger).forEach(alleEmpfaenger::add);

		return this;
	}

	public Mail fuegeInKopieHinzu(final MailAdresse... inKopie)
	{
		Stream.of(inKopie).forEach(alleInKopie::add);

		return this;
	}

	public Mail fuegeInBlindkopieHinzu(final MailAdresse... inBlindkopie)
	{
		Stream.of(inBlindkopie).forEach(alleInBlindkopie::add);

		return this;
	}
}
