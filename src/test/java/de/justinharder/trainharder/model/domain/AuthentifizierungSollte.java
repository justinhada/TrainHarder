package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthentifizierungSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();
	private static final String MAIL = "mail@justinharder.de";
	private static final String BENUTZERNAME = "harder";

	private Authentifizierung sut;

	@BeforeEach
	void setup()
	{
		sut = new Authentifizierung(PRIMAERSCHLUESSEL, MAIL, BENUTZERNAME, Testdaten.PASSWORT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var resetUuid = UUID.randomUUID();
		var authentifizierung = new Authentifizierung()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setMail(MAIL)
			.setBenutzername(BENUTZERNAME)
			.setPasswort(Testdaten.PASSWORT)
			.setAktiv(true)
			.setResetUuid(resetUuid)
			.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(authentifizierung.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(authentifizierung.getMail()).isEqualTo(MAIL),
			() -> assertThat(authentifizierung.getBenutzername()).isEqualTo(BENUTZERNAME),
			() -> assertThat(authentifizierung.getPasswort()).isEqualTo(Testdaten.PASSWORT),
			() -> assertThat(authentifizierung.isAktiv()).isTrue(),
			() -> assertThat(authentifizierung.getResetUuid()).isEqualTo(resetUuid),
			() -> assertThat(authentifizierung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Authentifizierung.class)
			.withPrefabValues(Benutzer.class, Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Authentifizierung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Authentifizierung(null, MAIL, BENUTZERNAME, Testdaten.PASSWORT)),
			() -> assertThrows(NullPointerException.class, () -> new Authentifizierung(PRIMAERSCHLUESSEL, null, BENUTZERNAME, Testdaten.PASSWORT)),
			() -> assertThrows(NullPointerException.class, () -> new Authentifizierung(PRIMAERSCHLUESSEL, MAIL, null, Testdaten.PASSWORT)),
			() -> assertThrows(NullPointerException.class, () -> new Authentifizierung(PRIMAERSCHLUESSEL, MAIL, BENUTZERNAME, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzername(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPasswort(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzer(null)));
	}
}