package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		var id = new Primaerschluessel();
		var koerpermessung = new Koerpermessung(
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
		var id = new Primaerschluessel();
		var koerpermessung = new Koerpermessung()
			.setPrimaerschluessel(id)
			.setDatum(LocalDate.now())
			.setKoerpermasse(new Koerpermasse(178, 90, 25))
			.setKalorieneinnahme(2500)
			.setKalorienverbrauch(2900)
			.setBenutzer(Testdaten.BENUTZER_JUSTIN);

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
	void test05()
	{
		EqualsVerifier.forClass(Koerpermessung.class)
			.withPrefabValues(Benutzer.class, Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		var erwartet = "Koerpermessung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test08()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(null, LocalDate.now(), new Koerpermasse(), 0, 0, new Benutzer())),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(new Primaerschluessel(), null, new Koerpermasse(), 0, 0, new Benutzer())),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(new Primaerschluessel(), LocalDate.now(), null, 0, 0, new Benutzer())),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(new Primaerschluessel(), LocalDate.now(), new Koerpermasse(), 0, 0, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpermasse(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzer(null)));
	}
}
