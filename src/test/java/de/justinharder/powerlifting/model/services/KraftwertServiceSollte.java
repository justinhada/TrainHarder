package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class KraftwertServiceSollte
{
	private KraftwertService sut;
	private KraftwertRepository kraftwertRepository;

	@BeforeEach
	public void setup()
	{
		kraftwertRepository = mock(KraftwertRepository.class);
		sut = new KraftwertService(kraftwertRepository);
	}

	private void angenommenDasKraftwertRepositoryGibtAlleKraftwerteZurueck(final List<Kraftwert> kraftwerte)
	{
		when(kraftwertRepository.ermittleAlle()).thenReturn(kraftwerte);
	}

	private void angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(final List<Kraftwert> kraftwerte)
	{
		when(kraftwertRepository.ermittleAlleZuBenutzer(any(Benutzer.class))).thenReturn(kraftwerte);
	}

	private void angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(final Kraftwert kraftwert)
	{
		when(kraftwertRepository.ermittleZuId(anyInt())).thenReturn(kraftwert);
	}

	@Test
	@DisplayName("alle Kraftwerte ermitteln")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		final var kraftwerte = List.of(
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZurueck(kraftwerte);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	public void test02()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		final var kraftwerte = List.of(
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(kraftwerte);

		final var ergebnis = sut.ermittleAlleZuBenutzer(Testdaten.JUSTIN_BENUTZER);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert erstellen")
	public void test03()
	{
		final var erwartet = Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN;
		final var eingabe = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;

		final var ergebnis = sut.erstelleKraftwert(Testdaten.WETTKAMPFBANKDRUECKEN, 100, Testdaten.JUSTIN_BENUTZER);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kraftwertRepository).erstelleKraftwert(eingabe);
	}

	@Test
	@DisplayName("einen Kraftwert zu ID ermitteln")
	public void test04() throws KraftwertNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE;
		final var kraftwert = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE;
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(kraftwert);

		final var ergebnis = sut.ermittleZuId(0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
