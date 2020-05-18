package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.dto.UebungEintrag;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.services.UebungService;
import de.justinharder.trainharder.setup.Testdaten;

public class UebungControllerSollte
{
	private UebungController sut;
	private UebungService uebungService;

	@BeforeEach
	public void setup()
	{
		uebungService = mock(UebungService.class);
		sut = new UebungController(uebungService);
	}

	private void angenommenDerUebungServiceGibtAlleUebungEintraegeZurueck(final List<UebungEintrag> erwartet)
	{
		when(uebungService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungsartZurueck(
		final List<UebungEintrag> erwartet) throws UebungNichtGefundenException
	{
		when(uebungService.ermittleZuUebungsart(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungskategorieZurueck(
		final List<UebungEintrag> erwartet) throws UebungNichtGefundenException
	{
		when(uebungService.ermittleZuUebungskategorie(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerUebungServiceGibtEineUebungMithilfeDerIdZurueck(final UebungEintrag erwartet)
		throws UebungNichtGefundenException
	{
		when(uebungService.ermittleZuId(anyString())).thenReturn(erwartet);
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
	public void test02() throws UebungNichtGefundenException
	{
		final var erwartet = List.of(
			Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungsartZurueck(erwartet);

		final var ergebnis = sut.getUebungenZuUebungsart("GRUNDUEBUNG");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller UebungEinträge einer Uebungskategorie zurückgeben")
	public void test03() throws UebungNichtGefundenException
	{
		final var erwartet = List.of(Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN);
		angenommenDerUebungServiceGibtAlleUebungEintraegeZuUebungskategorieZurueck(erwartet);

		final var ergebnis = sut.getUebungenZuUebungskategorie("WETTKAMPF_BANKDRUECKEN");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Uebung mit der übergebenen ID ermitteln")
	public void test04() throws UebungNichtGefundenException
	{
		final var erwartet = Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE;
		angenommenDerUebungServiceGibtEineUebungMithilfeDerIdZurueck(erwartet);

		final var ergebnis = sut.getUebungZuId(erwartet.getId());

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
