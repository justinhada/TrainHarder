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

public class AuthentifizierungSollte
{
	private Authentifizierung sut;

	@BeforeEach
	public void setup()
	{
		sut = new Authentifizierung(
			new Primaerschluessel(),
			"mail@justinharder.de",
			"harder",
			Testdaten.PASSWORT);
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		assertThat(Authentifizierung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsCounstructor haben")
	public void test02()
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
			() -> assertThat(authentifizierung.isAktiv()).isEqualTo(false),
			() -> assertThat(authentifizierung.getResetUuid()).isEqualTo(null));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test03()
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
			() -> assertThat(authentifizierung.isAktiv()).isEqualTo(true),
			() -> assertThat(authentifizierung.getResetUuid()).isEqualTo(resetUuid),
			() -> assertThat(authentifizierung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test04()
	{
		final var andereAuthentifizierung = new Authentifizierung();
		andereAuthentifizierung.setPrimaerschluessel(new Primaerschluessel());

		final var authentifizierungMitGleicherId = new Authentifizierung();
		authentifizierungMitGleicherId.setPrimaerschluessel(sut.getPrimaerschluessel());

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.BENUTZER_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andereAuthentifizierung)).isEqualTo(false),
			() -> assertThat(sut.equals(authentifizierungMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereAuthentifizierung));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test05()
	{
		final var erwartet = "Authentifizierung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
