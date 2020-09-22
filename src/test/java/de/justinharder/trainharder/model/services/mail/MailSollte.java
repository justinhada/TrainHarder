package de.justinharder.trainharder.model.services.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.setup.Testdaten;

class MailSollte
{
	private Mail sut;

	@BeforeEach
	void setup()
	{
		sut = new Mail(new MailAdresse("mail@justinharder.de", "Justin"), "Betreff", "Inhalt")
			.fuegeEmpfaengerHinzu(new MailAdresse("justinharder@t-online.de", "Justin"))
			.fuegeInKopieHinzu(new MailAdresse("justinharder@t-online.de", "Justin"))
			.fuegeInBlindkopieHinzu(new MailAdresse("justinharder@t-online.de", "Justin"));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test01()
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
	void test02()
	{
		final var andereMail =
			new Mail(new MailAdresse("justinharder@t-online.de", "Justin"), "Anderer Betreff", "Anderer Inhalt");

		assertAll(
			() -> assertThat(sut).isEqualTo(sut),
			() -> assertThat(sut).isNotEqualTo(null),
			() -> assertThat(sut).isNotEqualTo(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut).isNotEqualTo(andereMail),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereMail.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		final var erwartet = "Mail("
			+ "sender=MailAdresse(adresse=mail@justinharder.de, name=Justin), "
			+ "alleEmpfaenger=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "alleInKopie=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "alleInBlindkopie=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "betreff=Betreff, "
			+ "inhalt=Inhalt)";

		assertThat(sut).hasToString(erwartet);
	}
}
