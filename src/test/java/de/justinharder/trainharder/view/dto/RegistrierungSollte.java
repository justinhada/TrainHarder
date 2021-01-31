package de.justinharder.trainharder.view.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrierungSollte
{
	private static final String MAIL = "mail@justinharder.de";
	private static final String BENUTZERNAME = "harder";
	private static final String PASSWORT = "Justinharder#98";

	private Registrierung sut;

	@BeforeEach
	void setup()
	{
		sut = new Registrierung(MAIL, BENUTZERNAME, PASSWORT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new Registrierung()
			.setMail(MAIL)
			.setBenutzername(BENUTZERNAME)
			.setPasswort(PASSWORT);

		assertAll(
			() -> assertThat(sut.getMail()).isEqualTo(MAIL),
			() -> assertThat(sut.getBenutzername()).isEqualTo(BENUTZERNAME),
			() -> assertThat(sut.getPasswort()).isEqualTo(PASSWORT));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Registrierung.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString(			"Registrierung(mail=mail@justinharder.de, benutzername=harder, passwort=Justinharder#98)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Registrierung(null, BENUTZERNAME, PASSWORT)),
			() -> assertThrows(NullPointerException.class, () -> new Registrierung(MAIL, null, PASSWORT)),
			() -> assertThrows(NullPointerException.class, () -> new Registrierung(MAIL, BENUTZERNAME, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzername(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPasswort(null)));
	}
}