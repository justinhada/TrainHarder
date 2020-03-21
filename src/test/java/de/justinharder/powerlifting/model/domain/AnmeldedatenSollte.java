package de.justinharder.powerlifting.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.setup.Testdaten;

public class AnmeldedatenSollte
{
	private Anmeldedaten sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.ANMELDEDATEN_JUSTIN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Anmeldedaten.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsCounstructor haben")
	public void test02()
	{
		final var anmeldedaten = new Anmeldedaten(
			"mail@justinharder.de",
			"harder",
			"JustinHarder98");

		assertAll(
			() -> assertThat(anmeldedaten.getId()).isEqualTo(0),
			() -> assertThat(anmeldedaten.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(anmeldedaten.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(anmeldedaten.getPasswort()).isEqualTo("JustinHarder98"));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test03()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(0),
			() -> assertThat(sut.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(sut.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(sut.getPasswort()).isEqualTo("JustinHarder98"),
			() -> assertThat(sut.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test04()
	{
		final var anmeldedaten = new Anmeldedaten();
		anmeldedaten.setId(0);
		anmeldedaten.setMail("mail@justinharder.de");
		anmeldedaten.setBenutzername("harder");
		anmeldedaten.setPasswort("JustinHarder98");
		anmeldedaten.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(anmeldedaten.getId()).isEqualTo(0),
			() -> assertThat(anmeldedaten.getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(anmeldedaten.getBenutzername()).isEqualTo("harder"),
			() -> assertThat(anmeldedaten.getPasswort()).isEqualTo("JustinHarder98"),
			() -> assertThat(anmeldedaten.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andereAnmeldedaten = new Anmeldedaten();
		andereAnmeldedaten.setId(1);

		final var anmeldedatenMitGleicherId = new Anmeldedaten();
		anmeldedatenMitGleicherId.setId(0);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.BENUTZER_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andereAnmeldedaten)).isEqualTo(false),
			() -> assertThat(sut.equals(anmeldedatenMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereAnmeldedaten));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		assertThat(sut.toString()).isEqualTo("Anmeldedaten{ID=0}");
	}
}
