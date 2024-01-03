package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.Koerpermasse;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static de.justinharder.trainharder.setup.Testdaten.BENUTZER_EDUARD;
import static de.justinharder.trainharder.setup.Testdaten.BENUTZER_JUSTIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Koerpermessung sollte")
class KoerpermessungTest
{
	private static final ID ID = new ID();
	private static final LocalDate DATUM = LocalDate.of(2020, 7, 29);
	private static final Koerpermasse KOERPERMASSE =
		new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25));
	private static final int KALORIENEINNAHME = 2500;
	private static final int KALORIENVERBRAUCH = 2500;

	private Koerpermessung sut;

	@BeforeEach
	void setup()
	{
		sut = new Koerpermessung(ID, DATUM, KOERPERMASSE, KALORIENEINNAHME, KALORIENVERBRAUCH);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var koerpermessung = new Koerpermessung()
			.setDatum(DATUM)
			.setKoerpermasse(KOERPERMASSE)
			.setKalorieneinnahme(KALORIENEINNAHME)
			.setKalorienverbrauch(KALORIENVERBRAUCH);

		assertAll(
			() -> assertThat(koerpermessung.getDatum()).isEqualTo(DATUM),
			() -> assertThat(koerpermessung.getKoerpermasse()).isEqualTo(KOERPERMASSE),
			() -> assertThat(koerpermessung.getKalorieneinnahme()).isEqualTo(KALORIENEINNAHME),
			() -> assertThat(koerpermessung.getKalorienverbrauch()).isEqualTo(KALORIENVERBRAUCH));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Koerpermessung.class)
			.withPrefabValues(Benutzer.class, BENUTZER_JUSTIN, BENUTZER_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Koerpermessung{ID=" + sut.getId().getWert() + "}");
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(null, DATUM, KOERPERMASSE, KALORIENEINNAHME, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(ID, null, KOERPERMASSE, KALORIENEINNAHME, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(ID, DATUM, null, KALORIENEINNAHME, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(ID, DATUM, KOERPERMASSE, null, KALORIENVERBRAUCH)),
			() -> assertThrows(NullPointerException.class,
				() -> new Koerpermessung(ID, DATUM, KOERPERMASSE, KALORIENEINNAHME, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpermasse(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKalorieneinnahme(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKalorienverbrauch(null)));
	}
}
