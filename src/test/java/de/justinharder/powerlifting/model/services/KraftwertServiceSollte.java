package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;
import de.justinharder.powerlifting.model.repository.KraftwertRepository;
import de.justinharder.powerlifting.model.repository.UebungRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class KraftwertServiceSollte
{
	private KraftwertService sut;
	private KraftwertRepository kraftwertRepository;
	private BenutzerRepository benutzerRepository;
	private UebungRepository uebungRepository;

	@BeforeEach
	public void setup()
	{
		kraftwertRepository = mock(KraftwertRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		uebungRepository = mock(UebungRepository.class);
		sut = new KraftwertService(kraftwertRepository, benutzerRepository, uebungRepository);
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

	private void angenommenDasKraftwertRepositoryGibtNullZurueck()
	{
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(null);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(final Benutzer benutzer)
	{
		when(benutzerRepository.ermittleZuId(anyInt())).thenReturn(benutzer);
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(final Uebung uebung)
	{
		when(uebungRepository.ermittleZuId(anyInt())).thenReturn(uebung);
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

		final var ergebnis = sut.ermittleAlleZuBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert erstellen")
	public void test03()
	{
		final var kraftwertEintrag = Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN;
		final var kraftwert = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var uebung = Testdaten.WETTKAMPFBANKDRUECKEN;
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzer);
		angenommenDasUebungRepositoryGibtEineUebungZurueck(uebung);

		sut.erstelleKraftwert(kraftwertEintrag, "0", "0");

		verify(kraftwertRepository).erstelleKraftwert(kraftwert);
	}

	@Test
	@DisplayName("KraftwertNichtGefundenException werfen, wenn ID zu keinem Kraftwert gehört")
	public void test04()
	{
		angenommenDasKraftwertRepositoryGibtNullZurueck();

		final var exception = assertThrows(KraftwertNichtGefundenException.class, () -> sut.ermittleZuId("10000"));

		assertThat(exception.getMessage()).isEqualTo("Der Kraftwert mit der ID \"10000\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Kraftwert zur ID ermitteln")
	public void test05() throws KraftwertNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE;
		final var kraftwert = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE;
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(kraftwert);

		final var ergebnis = sut.ermittleZuId("0");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
