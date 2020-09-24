package de.justinharder.trainharder.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

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
		final var id = new Primaerschluessel();
		final var authentifizierung = new Authentifizierung(
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
		final var id = new Primaerschluessel();
		final var resetUuid = UUID.randomUUID();
		final var authentifizierung = new Authentifizierung();
		authentifizierung.setPrimaerschluessel(id);
		authentifizierung.setMail("mail@justinharder.de");
		authentifizierung.setBenutzername("harder");
		authentifizierung.setPasswort(Testdaten.PASSWORT);
		authentifizierung.setAktiv(true);
		authentifizierung.setResetUuid(resetUuid);
		authentifizierung.setBenutzer(Testdaten.BENUTZER_JUSTIN);

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
	@SuppressWarnings("unlikely-arg-type")
	void test04()
	{
		final var andereAuthentifizierung = new Authentifizierung();
		andereAuthentifizierung.setPrimaerschluessel(new Primaerschluessel());

		final var authentifizierungMitGleicherId = new Authentifizierung();
		authentifizierungMitGleicherId.setPrimaerschluessel(sut.getPrimaerschluessel());

		assertAll(
			() -> assertThat(sut).isNotNull(),
			() -> assertThat(sut).isNotEqualTo(andereAuthentifizierung),
			() -> assertThat(sut).isEqualTo(authentifizierungMitGleicherId),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereAuthentifizierung.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test05()
	{
		final var erwartet = "Authentifizierung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
