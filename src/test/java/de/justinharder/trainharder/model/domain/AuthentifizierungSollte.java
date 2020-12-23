package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthentifizierungSollte
{
	private Authentifizierung sut;

	@BeforeEach
	void setup()
	{
		sut = new Authentifizierung(
			new Primaerschluessel(),
			"mail@justinharder.de",
			"harder",
			Testdaten.PASSWORT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	void test01()
	{
		assertThat(Authentifizierung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsCounstructor haben")
	void test02()
	{
		var id = new Primaerschluessel();
		var authentifizierung = new Authentifizierung(
			id,
			"mail@justinharder.de",
			"harder",
			Testdaten.PASSWORT);

		assertAll(
			() -> assertThat(authentifizierung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(authentifizierung.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(authentifizierung.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(authentifizierung.getPasswort()).isEqualTo(Testdaten.PASSWORT),
			() -> assertThat(authentifizierung.isAktiv()).isFalse(),
			() -> assertThat(authentifizierung.getResetUuid()).isNull());
	}

	@Test
	@DisplayName("Setter besitzen")
	void test03()
	{
		var id = new Primaerschluessel();
		var resetUuid = UUID.randomUUID();
		var authentifizierung = new Authentifizierung()
			.setPrimaerschluessel(id)
			.setMail("mail@justinharder.de")
			.setBenutzername("harder")
			.setPasswort(Testdaten.PASSWORT)
			.setAktiv(true)
			.setResetUuid(resetUuid)
			.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(authentifizierung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(authentifizierung.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(authentifizierung.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(authentifizierung.getPasswort()).isEqualTo(Testdaten.PASSWORT),
			() -> assertThat(authentifizierung.isAktiv()).isTrue(),
			() -> assertThat(authentifizierung.getResetUuid()).isEqualTo(resetUuid),
			() -> assertThat(authentifizierung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test04()
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
	void test05()
	{
		var erwartet = "Authentifizierung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test06()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Authentifizierung(null, "mail@mail.de", "harder", new Passwort())),
			() -> assertThrows(NullPointerException.class,
				() -> new Authentifizierung(new Primaerschluessel(), null, "harder", new Passwort())),
			() -> assertThrows(NullPointerException.class,
				() -> new Authentifizierung(new Primaerschluessel(), "mail@mail.de", null, new Passwort())),
			() -> assertThrows(NullPointerException.class,
				() -> new Authentifizierung(new Primaerschluessel(), "mail@mail.de", "harder", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzername(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPasswort(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzer(null)));
	}
}
