package de.justinharder.powerlifting.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.setup.Testdaten;

public class AuthentifizierungSollte
{
	private Authentifizierung sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Authentifizierung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsCounstructor haben")
	public void test02()
	{
		final var authentifizierung = new Authentifizierung(
			"mail@justinharder.de",
			"harder",
			"JustinHarder98");

		assertAll(
			() -> assertThat(authentifizierung.getId()).isEqualTo(0),
			() -> assertThat(authentifizierung.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(authentifizierung.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(authentifizierung.getPasswort()).isEqualTo("JustinHarder98"));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test03()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(0),
			() -> assertThat(sut.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(sut.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(sut.getPasswort()).isEqualTo("JustinHarder#98"),
			() -> assertThat(sut.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test04()
	{
		final var authentifizierung = new Authentifizierung();
		authentifizierung.setId(0);
		authentifizierung.setMail("mail@justinharder.de");
		authentifizierung.setBenutzername("harder");
		authentifizierung.setPasswort("JustinHarder98");
		authentifizierung.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(authentifizierung.getId()).isEqualTo(0),
			() -> assertThat(authentifizierung.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(authentifizierung.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(authentifizierung.getPasswort()).isEqualTo("JustinHarder98"),
			() -> assertThat(authentifizierung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andereAuthentifizierung = new Authentifizierung();
		andereAuthentifizierung.setId(1);

		final var authentifizierungMitGleicherId = new Authentifizierung();
		authentifizierungMitGleicherId.setId(0);

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
	public void test06()
	{
		assertThat(sut.toString()).isEqualTo("Authentifizierung{ID=0}");
	}
}
