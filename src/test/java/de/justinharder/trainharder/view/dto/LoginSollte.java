package de.justinharder.trainharder.view.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginSollte
{
	private static final String BENUTZERNAME = "harder";
	private static final String PASSWORT = "Justinharder#98";

	private Login sut;

	@BeforeEach
	void setup()
	{
		sut = new Login(BENUTZERNAME, PASSWORT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		sut = new Login()
			.setBenutzername(BENUTZERNAME)
			.setPasswort(PASSWORT);

		assertAll(
			() -> assertThat(sut.getBenutzername()).isEqualTo(BENUTZERNAME),
			() -> assertThat(sut.getPasswort()).isEqualTo(PASSWORT));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Login.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Login(benutzername=harder, passwort=Justinharder#98)");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Login(null, PASSWORT)),
			() -> assertThrows(NullPointerException.class, () -> new Login(BENUTZERNAME, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzername(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPasswort(null)));
	}
}
