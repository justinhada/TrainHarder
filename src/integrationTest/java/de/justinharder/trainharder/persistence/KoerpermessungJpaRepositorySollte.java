package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class KoerpermessungJpaRepositorySollte extends JpaRepositorySollte
{
	private KoerpermessungJpaRepository sut;

	@Before
	public void setup()
	{
		sut = new KoerpermessungJpaRepository();

		sut.setEntityManager(erzeugeEntityManager());
	}

	@Test
	public void alleKoerpermessungenZuBenutzerErmitteln()
	{
		assertAll(
			() -> assertThat(sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_JUSTIN_ID))
				.contains(Testdaten.KOERPERMESSUNG_JUSTIN),
			() -> assertThat(sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_EDUARD_ID))
				.contains(Testdaten.KOERPERMESSUNG_EDUARD));
	}

	@Test
	public void koerpermessungZuIdErmitteln()
	{
		assertAll(() ->
		{
			var erwartet = Testdaten.KOERPERMESSUNG_JUSTIN;

			var ergebnis = sut.ermittleZuId(Testdaten.KOERPERMESSUNG_JUSTIN_ID);

			assertThat(ergebnis).hasValue(erwartet);
		}, () ->
		{
			var erwartet = Testdaten.KOERPERMESSUNG_EDUARD;

			var ergebnis = sut.ermittleZuId(Testdaten.KOERPERMESSUNG_EDUARD_ID);

			assertThat(ergebnis).hasValue(erwartet);
		});
	}

	@Test
	public void keineKoerpermessungZuIdErmitteln()
	{
		var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEmpty();
	}

	@Test
	public void koerpermessungErstellen()
	{
		var erwartet = new Koerpermessung(
			new Primaerschluessel(),
			LocalDate.now(),
			new Koerpermasse(178, 90, 25),
			2500,
			2900,
			Testdaten.BENUTZER_JUSTIN);

		var ergebnis = sut.speichereKoerpermessung(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void koerpermessungAktualisieren()
	{
		var erwartet = Testdaten.KOERPERMESSUNG_JUSTIN;
		erwartet.setKalorieneinnahme(1900);

		var ergebnis = sut.speichereKoerpermessung(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(erwartet.getDatum()),
			() -> assertThat(ergebnis.getKoerpermasse()).isEqualTo(erwartet.getKoerpermasse()),
			() -> assertThat(ergebnis.getKalorieneinnahme()).isEqualTo(erwartet.getKalorieneinnahme()),
			() -> assertThat(ergebnis.getKalorienverbrauch()).isEqualTo(erwartet.getKalorienverbrauch()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(erwartet.getBenutzer()));
	}
}
