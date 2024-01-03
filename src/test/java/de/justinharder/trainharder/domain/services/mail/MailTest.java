package de.justinharder.trainharder.domain.services.mail;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailSollte
{
	private static final MailAdresse MAIL_ADRESSE = new MailAdresse("justinharder@t-online.de", "Justin");

	private Mail sut;

	@BeforeEach
	void setup()
	{
		sut = new Mail(new MailAdresse("mail@justinharder.de", "Justin"), "Betreff", "Inhalt")
			.fuegeEmpfaengerHinzu(MAIL_ADRESSE)
			.fuegeInKopieHinzu(MAIL_ADRESSE)
			.fuegeInBlindkopieHinzu(MAIL_ADRESSE);
	}

	@Test
	@DisplayName("Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getSender()).isEqualTo(new MailAdresse("mail@justinharder.de", "Justin")),
			() -> assertThat(sut.getBetreff()).isEqualTo("Betreff"),
			() -> assertThat(sut.getInhalt()).isEqualTo("Inhalt"),
			() -> assertThat(sut.getAlleEmpfaenger()).isEqualTo(List.of(MAIL_ADRESSE)),
			() -> assertThat(sut.getAlleInKopie()).isEqualTo(List.of(MAIL_ADRESSE)),
			() -> assertThat(sut.getAlleInBlindkopie()).isEqualTo(List.of(MAIL_ADRESSE)));
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
		assertThat(sut).hasToString("Mail("
			+ "sender=MailAdresse(adresse=mail@justinharder.de, name=Justin), "
			+ "alleEmpfaenger=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "alleInKopie=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "alleInBlindkopie=[MailAdresse(adresse=justinharder@t-online.de, name=Justin)], "
			+ "betreff=Betreff, "
			+ "inhalt=Inhalt)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Mail(null, "betreff", "inhalt")),
			() -> assertThrows(NullPointerException.class, () -> new Mail(MAIL_ADRESSE, null, "inhalt")),
			() -> assertThrows(NullPointerException.class, () -> new Mail(MAIL_ADRESSE, "betreff", null)));
	}
}
