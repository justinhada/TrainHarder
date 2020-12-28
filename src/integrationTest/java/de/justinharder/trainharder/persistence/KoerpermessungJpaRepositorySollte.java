package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermessungJpaRepositorySollte extends JpaRepositorySollte
{
	private KoerpermessungJpaRepository sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermessungJpaRepository();

		sut.setEntityManager(getEntityManager());
	}

	@Test
	@DisplayName("alle Koerpermessungen zu Benutzer ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID))
				.containsExactlyInAnyOrder(Testdaten.KOERPERMESSUNG_JUSTIN),
			() -> assertThat(sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID))
				.containsExactlyInAnyOrder(Testdaten.KOERPERMESSUNG_EDUARD));
	}

	@Test
	@DisplayName("keine Koerpermessung zu ID ermitteln")
	void test02()
	{
		assertThat(sut.ermittleZuId(new Primaerschluessel())).isEmpty();
	}

	@Test
	@DisplayName("Koerpermessung zu ID ermitteln")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.ermittleZuId(Testdaten.KOERPERMESSUNG_JUSTIN_ID))
				.hasValue(Testdaten.KOERPERMESSUNG_JUSTIN),
			() -> assertThat(sut.ermittleZuId(Testdaten.KOERPERMESSUNG_EDUARD_ID))
				.hasValue(Testdaten.KOERPERMESSUNG_EDUARD));
	}

	@Test
	@DisplayName("Koerpermessung erstellen")
	void test04()
	{
		var koerpermessung = new Koerpermessung(
			new Primaerschluessel(),
			LocalDate.now(),
			new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25)),
			2500,
			2900,
			Testdaten.BENUTZER_JUSTIN);

		assertThat(sut.speichereKoerpermessung(koerpermessung)).isEqualTo(koerpermessung);
	}

	@Test
	@DisplayName("Koerpermessung aktualisieren")
	void test05()
	{
		var koerpermessung = Testdaten.KOERPERMESSUNG_JUSTIN;
		koerpermessung.setKalorieneinnahme(1900);

		var ergebnis = sut.speichereKoerpermessung(koerpermessung);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(koerpermessung.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(koerpermessung.getDatum()),
			() -> assertThat(ergebnis.getKoerpermasse()).isEqualTo(koerpermessung.getKoerpermasse()),
			() -> assertThat(ergebnis.getKalorieneinnahme()).isEqualTo(koerpermessung.getKalorieneinnahme()),
			() -> assertThat(ergebnis.getKalorienverbrauch()).isEqualTo(koerpermessung.getKalorienverbrauch()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(koerpermessung.getBenutzer()));
	}

	@Test
	@DisplayName("null validieren")
	void test06()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setEntityManager(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereKoerpermessung(null)));
	}
}
