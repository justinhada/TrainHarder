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

import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.repository.UebungRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class UebungServiceSollte
{
	private UebungService sut;
	private UebungRepository uebungRepository;

	@BeforeEach
	public void setup()
	{
		uebungRepository = mock(UebungRepository.class);
		sut = new UebungService(uebungRepository);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZurueck(final List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlle()).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(final List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleZuUebungsart(any(Uebungsart.class))).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(final List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleZuUebungskategorie(any(Uebungskategorie.class))).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(final Uebung uebung)
	{
		when(uebungRepository.ermittleZuId(anyInt())).thenReturn(uebung);
	}

	private void angenommenDasUebungRepositoryGibtNullZurueck()
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(null);
	}

	@Test
	@DisplayName("alle Uebungen ermitteln")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		final var uebungen = List.of(
			Testdaten.WETTKAMPFBANKDRUECKEN,
			Testdaten.LOWBAR_KNIEBEUGE,
			Testdaten.KONVENTIONELLES_KREUZHEBEN);
		angenommenDasUebungRepositoryGibtAlleUebungenZurueck(uebungen);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungsart ermitteln")
	public void test02()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		final var uebungen = List.of(
			Testdaten.WETTKAMPFBANKDRUECKEN,
			Testdaten.LOWBAR_KNIEBEUGE,
			Testdaten.KONVENTIONELLES_KREUZHEBEN);
		angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(uebungen);

		final var ergebnis = sut.ermittleZuUebungsart("GRUNDUEBUNG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungskategorie ermitteln")
	public void test03()
	{
		final var erwartet = List.of(Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE);
		final var uebungen = List.of(Testdaten.LOWBAR_KNIEBEUGE);
		angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(uebungen);

		final var ergebnis = sut.ermittleZuUebungskategorie("WETTKAMPF_KNIEBEUGE");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn ID zu keiner Uebung gehört")
	public void test04()
	{
		angenommenDasUebungRepositoryGibtNullZurueck();

		final var exception = assertThrows(UebungNichtGefundenException.class, () -> sut.ermittleZuId(10000));

		assertThat(exception.getMessage()).isEqualTo("Die Uebung mit der ID \"10000\" existiert nicht!");
	}

	@Test
	@DisplayName("eine Uebung zur ID ermitteln")
	public void test05() throws UebungNichtGefundenException
	{
		final var erwartet = Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN;
		final var uebung = Testdaten.KONVENTIONELLES_KREUZHEBEN;
		angenommenDasUebungRepositoryGibtEineUebungZurueck(uebung);

		final var ergebnis = sut.ermittleZuId(0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Uebung erstellen")
	public void test06()
	{
		final var uebungEintrag = Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE;
		final var uebung = Testdaten.LOWBAR_KNIEBEUGE;

		sut.erstelleUebung(uebungEintrag, Testdaten.BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE);

		verify(uebungRepository).erstelleUebung(uebung);
	}
}
