package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Maps;

import de.justinharder.trainharder.model.domain.dto.KraftwertEintrag;
import de.justinharder.trainharder.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.model.services.KraftwertService;
import de.justinharder.trainharder.setup.Testdaten;

public class KraftwertControllerSollte extends ControllerSollte
{
	private KraftwertController sut;
	private KraftwertService kraftwertService;

	@BeforeEach
	public void setup()
	{
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
		when(kraftwertService.ermittleAlleZuBenutzer(anyString())).thenReturn(erwartet);
	}

	private void angenommenDerKraftwertServiceGibtEinenKraftwertMithilfeDerIdZurueck(final KraftwertEintrag erwartet)
		throws KraftwertNichtGefundenException
	{
		when(kraftwertService.ermittleZuId(anyString())).thenReturn(erwartet);
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
		final var kraftwertEintrag = Testdaten.KRAFTWERTEINTRAG_WETTKAMPFBANKDRUECKEN;
		angenommenExternerWebContextEnthaeltParameter(
			Maps.immutableEntry("benutzerId", "1"),
			Maps.immutableEntry("uebungId", "1"));
		kraftwertEintrag.setId(0);

		sut.getKraftwertEintrag().setMaximum(100);
		sut.getKraftwertEintrag().setKoerpergewicht(90);
		sut.getKraftwertEintrag().setDatum(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
		sut.getKraftwertEintrag().setWiederholungen("ONE_REP_MAX");
		sut.erstelleKraftwert("1", "1");

		verify(kraftwertService).erstelleKraftwert(kraftwertEintrag, "1", "1");
	}

	@Test
	@DisplayName("einen Kraftwert mit der übergebenen ID ermitteln")
	public void test03() throws KraftwertNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERTEINTRAG_KONVENTIONELLES_KREUZHEBEN;
		angenommenDerKraftwertServiceGibtEinenKraftwertMithilfeDerIdZurueck(erwartet);
		angenommenExternerWebContextEnthaeltParameter(Maps.immutableEntry("kraftwertId", "1"));

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

		final var ergebnis = sut.getKraftwerteZuBenutzer("0");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
