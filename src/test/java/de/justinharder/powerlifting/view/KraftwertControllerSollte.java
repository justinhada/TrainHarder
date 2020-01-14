package de.justinharder.powerlifting.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.services.KraftwertService;
import de.justinharder.powerlifting.setup.Testdaten;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;

public class KraftwertControllerSollte
{
	private KraftwertController sut;
	private ExternerWebContext externerWebContext;
	private Navigator navigator;
	private KraftwertService kraftwertService;

	@BeforeEach
	public void setup()
	{
		externerWebContext = mock(ExternerWebContext.class);
		navigator = mock(Navigator.class);
		kraftwertService = mock(KraftwertService.class);
		sut = new KraftwertController(externerWebContext, navigator, kraftwertService);
	}

	private void angenommenDerKraftwertServiceGibtAlleKraftwertEintraegeZurueck(final List<KraftwertEintrag> erwartet)
	{
		when(kraftwertService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerKraftwertServiceGibtAlleKraftwertEintraegeZuBenutzerZurueck(
		final List<KraftwertEintrag> erwartet)
	{
		when(kraftwertService.ermittleAlleZuBenutzer(any(Benutzer.class))).thenReturn(erwartet);
	}

	private void angenommenDerKraftwertServiceGibtEinenKraftwertEintragZurueck(final KraftwertEintrag erwartet)
	{
		when(kraftwertService.erstelleKraftwert(any(Uebung.class), anyInt(), any(Benutzer.class))).thenReturn(erwartet);
	}

	private void angenommenDerKraftwertServiceGibtEinenKraftwertMithilfeDerIdZurueck(final KraftwertEintrag erwartet)
		throws KraftwertNichtGefundenException
	{
		when(kraftwertService.ermittleZuId(anyInt())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller KraftwertEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerKraftwertServiceGibtAlleKraftwertEintraegeZurueck(erwartet);

		final var ergebnis = sut.getKraftwerte();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert weiter an den KraftwertService geben")
	public void test02()
	{
		final var erwartet = Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN;
		angenommenDerKraftwertServiceGibtEinenKraftwertEintragZurueck(erwartet);

		sut.setBenutzer(Testdaten.JUSTIN_BENUTZER);
		sut.setMaximum(100);
		sut.setUebung(Testdaten.WETTKAMPFBANKDRUECKEN);
		final var ergebnis = sut.erstelleKraftwert();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert mit der übergebenen ID ermitteln")
	public void test03() throws KraftwertNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN;
		angenommenDerKraftwertServiceGibtEinenKraftwertMithilfeDerIdZurueck(erwartet);

		sut.setId(0);
		final var ergebnis = sut.getKraftwertZuId();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller KraftwertEinträge eines Benutzers zurückgeben")
	public void test04()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERTEINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerKraftwertServiceGibtAlleKraftwertEintraegeZuBenutzerZurueck(erwartet);

		final var ergebnis = sut.getKraftwerteZuBenutzer(Testdaten.JUSTIN_BENUTZER);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
