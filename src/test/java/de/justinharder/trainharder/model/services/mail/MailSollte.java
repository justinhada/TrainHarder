package de.justinharder.trainharder.model.services.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

public class MailSollte
{
	private Mail sut;

	@BeforeEach
	public void setup()
	{
		sut = new Mail(new MailAdresse("mail@justinharder.de", "Justin"), "Betreff", "Inhalt")
			.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin"))
			.fuegeInKopieHinzu(new MailAdresse("justinharder@t-online.de", "Justin"))
			.fuegeInBlindkopieHinzu(new MailAdresse("justinharder@t-online.de", "Justin"));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test01()
	{
		assertAll(
			() -> assertThat(sut.getSender()).isEqualTo(new MailAdresse("mail@justinharder.de", "Justin")),
			() -> assertThat(sut.getBetreff()).isEqualTo("Betreff"),
			() -> assertThat(sut.getInhalt()).isEqualTo("Inhalt"),
			() -> assertThat(sut.getAlleEmpfaenger())
				.isEqualTo(List.of(new MailAdresse("justinharder@t-online.de", "Justin"))),
			() -> assertThat(sut.getAlleInKopie())
				.isEqualTo(List.of(new MailAdresse("justinharder@t-online.de", "Justin"))),
			() -> assertThat(sut.getAlleInBlindkopie())
				.isEqualTo(List.of(new MailAdresse("justinharder@t-online.de", "Justin"))));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test02()
	{
		final var andereMail =
			new Mail(new MailAdresse("justinharder@t-online.de", "Justin"), "Anderer Betreff", "Anderer Inhalt");

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andereMail)).isEqualTo(false),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereMail.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test03()
	{
		final var erwartet = "Mail("
			+ "sender=MailAdresse(adresse=mail@justinharder.de, name=Justin), "
			+ "alleEmpfaenger=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "alleInKopie=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "alleInBlindkopie=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "betreff=Betreff, "
			+ "inhalt=Inhalt)";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
