package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermessungSollte
{
	private static final Primaerschluessel PRIMAERSCHLUESSEL = new Primaerschluessel();
	private static final LocalDate DATUM = LocalDate.of(2020, 7, 29);
	private static final Koerpermasse KOERPERMASSE =
		new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25));
	private static final int KALORIENEINNAHME = 2500;
	private static final int KALORIENVERBRAUCH = 2500;

	private Koerpermessung sut;

	@BeforeEach
	void setup()
	{
		sut = new Koerpermessung(
			PRIMAERSCHLUESSEL,
			DATUM,
			KOERPERMASSE,
			KALORIENEINNAHME,
			KALORIENVERBRAUCH,
			Testdaten.BENUTZER_JUSTIN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var koerpermessung = new Koerpermessung()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setDatum(DATUM)
			.setKoerpermasse(KOERPERMASSE)
			.setKalorieneinnahme(KALORIENEINNAHME)
			.setKalorienverbrauch(KALORIENVERBRAUCH)
			.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(koerpermessung.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(koerpermessung.getDatum()).isEqualTo(DATUM),
			() -> assertThat(koerpermessung.getKoerpermasse()).isEqualTo(KOERPERMASSE),
			() -> assertThat(koerpermessung.getKalorieneinnahme()).isEqualTo(KALORIENEINNAHME),
			() -> assertThat(koerpermessung.getKalorienverbrauch()).isEqualTo(KALORIENVERBRAUCH),
			() -> assertThat(koerpermessung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
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
	void test03()
	{
		assertThat(sut).hasToString("Koerpermessung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Koerpermessung(null, DATUM,
				KOERPERMASSE, KALORIENEINNAHME, KALORIENVERBRAUCH, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Koerpermessung(PRIMAERSCHLUESSEL, null,
				KOERPERMASSE, KALORIENEINNAHME, KALORIENVERBRAUCH, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Koerpermessung(PRIMAERSCHLUESSEL, DATUM,
				null, KALORIENEINNAHME, KALORIENVERBRAUCH, Testdaten.BENUTZER_JUSTIN)),
			() -> assertThrows(NullPointerException.class, () -> new Koerpermessung(PRIMAERSCHLUESSEL, DATUM,
				KOERPERMASSE, KALORIENEINNAHME, KALORIENVERBRAUCH, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpermasse(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzer(null)));
	}
}
