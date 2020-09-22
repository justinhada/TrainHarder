package de.justinharder.trainharder.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

class KoerpermessungSollte
{
	private Koerpermessung sut;

	@BeforeEach
	void setup()
	{
		sut = Testdaten.KOERPERMESSUNG_JUSTIN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	void test01()
	{
		assertThat(Koerpermessung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	void test02()
	{
		final var id = new Primaerschluessel();
		final var koerpermessung = new Koerpermessung(
			id,
			LocalDate.of(2020, 7, 29),
			new Koerpermasse(178, 90, 25),
			2500,
			2900,
			Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(koerpermessung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(koerpermessung.getDatum()).isEqualTo(LocalDate.of(2020, 7, 29)),
			() -> assertThat(koerpermessung.getKoerpermasse()).isEqualTo(new Koerpermasse(178, 90, 25)),
			() -> assertThat(koerpermessung.getKalorieneinnahme()).isEqualTo(2500),
			() -> assertThat(koerpermessung.getKalorienverbrauch()).isEqualTo(2900),
			() -> assertThat(koerpermessung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(Testdaten.KOERPERMESSUNG_JUSTIN_ID),
			() -> assertThat(sut.getDatum()).isEqualTo(LocalDate.of(2020, 7, 29)),
			() -> assertThat(sut.getKoerpermasse()).isEqualTo(new Koerpermasse(178, 90, 25)),
			() -> assertThat(sut.getKalorieneinnahme()).isEqualTo(2500),
			() -> assertThat(sut.getKalorienverbrauch()).isEqualTo(2900),
			() -> assertThat(sut.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	void test04()
	{
		final var id = new Primaerschluessel();
		final var koerpermessung = new Koerpermessung();
		koerpermessung.setPrimaerschluessel(id);
		koerpermessung.setDatum(LocalDate.now());
		koerpermessung.setKoerpermasse(new Koerpermasse(178, 90, 25));
		koerpermessung.setKalorieneinnahme(2500);
		koerpermessung.setKalorienverbrauch(2900);
		koerpermessung.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(koerpermessung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(koerpermessung.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(koerpermessung.getKoerpermasse()).isEqualTo(new Koerpermasse(178, 90, 25)),
			() -> assertThat(koerpermessung.getKalorieneinnahme()).isEqualTo(2500),
			() -> assertThat(koerpermessung.getKalorienverbrauch()).isEqualTo(2900),
			() -> assertThat(koerpermessung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		final var andereKoerpermessung = new Koerpermessung();
		andereKoerpermessung.setPrimaerschluessel(new Primaerschluessel());

		final var koerpermessungMitGleicherId = new Koerpermessung();
		koerpermessungMitGleicherId.setPrimaerschluessel(sut.getPrimaerschluessel());

		assertAll(
			() -> assertThat(sut).isEqualTo(sut),
			() -> assertThat(sut).isNotNull(),
			() -> assertThat(sut).isNotEqualTo(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut).isNotEqualTo(andereKoerpermessung),
			() -> assertThat(sut).isEqualTo(koerpermessungMitGleicherId),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereKoerpermessung.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Koerpermessung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
