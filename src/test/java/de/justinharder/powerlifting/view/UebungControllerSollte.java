package de.justinharder.powerlifting.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.services.UebungService;
import de.justinharder.powerlifting.setup.Testdaten;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;

public class UebungControllerSollte
{
	private UebungController sut;
	private ExternerWebContext externerWebContext;
	private Navigator navigator;
	private UebungService uebungService;

	@BeforeEach
	public void setup()
	{
		externerWebContext = mock(ExternerWebContext.class);
		navigator = mock(Navigator.class);
		uebungService = mock(UebungService.class);
		sut = new UebungController(externerWebContext, navigator, uebungService);
	}

	private void angenommenDerUebungServiceGibtAlleUebungEintraegeZurueck(final List<UebungEintrag> erwartet)
	{
		when(uebungService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungsartZurueck(
		final List<UebungEintrag> erwartet)
	{
		when(uebungService.ermittleZuUebungsart(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungskategorieZurueck(
		final List<UebungEintrag> erwartet)
	{
		when(uebungService.ermittleZuUebungskategorie(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtEineUebungMithilfeDerIdZurueck(final UebungEintrag erwartet)
		throws UebungNichtGefundenException
	{
		when(uebungService.ermittleZuId(anyInt())).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtEinenUebungEintragZurueck(final UebungEintrag erwartet)
	{
		when(uebungService.erstelleUebung(anyString(), anyString(), anyString(), any(Belastungsfaktor.class)))
			.thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller UebungEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerUebungServiceGibtAlleUebungEintraegeZurueck(erwartet);

		final var ergebnis = sut.getUebungen();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller UebungEinträge einer Uebungsart zurückgeben")
	public void test02()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungsartZurueck(erwartet);

		sut.setUebungsart("GRUNDUEBUNG");
		final var ergebnis = sut.getUebungenZuUebungsart();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller UebungEinträge einer Uebungskategorie zurückgeben")
	public void test03()
	{
		final var erwartet = List.of(Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN);
		angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungskategorieZurueck(erwartet);

		sut.setUebungskategorie("WETTKAMPF_BANKDRUECKEN");
		final var ergebnis = sut.getUebungenZuUebungskategorie();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Uebung mit der übergebenen ID ermitteln")
	public void test04() throws UebungNichtGefundenException
	{
		final var erwartet = Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE;
		angenommenDerUebungServiceGibtEineUebungMithilfeDerIdZurueck(erwartet);

		sut.setId(0);
		final var ergebnis = sut.getUebungZuId();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Uebung weiter an den UebungService geben")
	public void test05()
	{
		final var erwartet = Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN;
		angenommenDerUebungServiceGibtEinenUebungEintragZurueck(erwartet);

		sut.setName("Konventionelles Kreuzheben");
		sut.setUebungsart("GRUNDUEBUNG");
		sut.setUebungskategorie("WETTKAMPF_KREUZHEBEN");
		sut.setBelastungsfaktor(Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);
		final var ergebnis = sut.erstelleUebung();

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
