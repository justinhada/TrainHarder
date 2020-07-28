package de.justinharder.trainharder.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

public class KoerpermessungJpaRepositorySollte
{
	private KoerpermessungJpaRepository sut;

	@BeforeClass
	public static void setupClass()
	{
		JpaRepositorySollte.erzeugeTestdaten();
	}

	@AfterClass
	public static void resetClass()
	{
		JpaRepositorySollte.schliesseEntityMananger();
	}

	@Before
	public void setup()
	{
		sut = new KoerpermessungJpaRepository(JpaRepositorySollte.erzeugeEntityManager());
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
			final var erwartet = Optional.of(Testdaten.KOERPERMESSUNG_JUSTIN);

			final var ergebnis = sut.ermittleZuId(Testdaten.KOERPERMESSUNG_JUSTIN_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		}, () ->
		{
			final var erwartet = Optional.of(Testdaten.KOERPERMESSUNG_EDUARD);

			final var ergebnis = sut.ermittleZuId(Testdaten.KOERPERMESSUNG_EDUARD_ID);

			assertThat(ergebnis).isEqualTo(erwartet);
		});
	}

	@Test
	public void keineKoerpermessungZuIdErmitteln()
	{
		final var erwartet = Optional.empty();

		final var ergebnis = sut.ermittleZuId(new Primaerschluessel());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void koerpermessungErstellen()
	{
		final var erwartet = new Koerpermessung(
			new Primaerschluessel(),
			LocalDate.now(),
			new Koerpermasse(178, 90, 25),
			2500,
			2900,
			Testdaten.BENUTZER_JUSTIN);

		final var ergebnis = sut.speichereKoerpermessung(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	public void koerpermessungAktualisieren()
	{
		final var erwartet = Testdaten.KOERPERMESSUNG_JUSTIN;
		erwartet.setEingenommeneKalorien(1900);

		final var ergebnis = sut.speichereKoerpermessung(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getPrimaerschluessel()).isEqualTo(erwartet.getPrimaerschluessel()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(erwartet.getDatum()),
			() -> assertThat(ergebnis.getKoerpermasse()).isEqualTo(erwartet.getKoerpermasse()),
			() -> assertThat(ergebnis.getEingenommeneKalorien()).isEqualTo(erwartet.getEingenommeneKalorien()),
			() -> assertThat(ergebnis.getVerbrannteKalorien()).isEqualTo(erwartet.getVerbrannteKalorien()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(erwartet.getBenutzer()));
	}
}
