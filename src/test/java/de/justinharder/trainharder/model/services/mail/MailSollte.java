package de.justinharder.trainharder.model.services.mail;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
	void test02()
	{
		EqualsVerifier.forClass(Mail.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NULL_FIELDS)
			.verify();
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
