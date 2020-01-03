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
import de.justinharder.powerlifting.model.services.KraftwertService;
import de.justinharder.powerlifting.setup.Testdaten;

public class KraftwertControllerSollte
{
	private KraftwertController sut;
	private KraftwertService kraftwertService;

	@BeforeEach
	public void setup()
	{
		kraftwertService = mock(KraftwertService.class);
		sut = new KraftwertController(kraftwertService);
	}

	private void angenommenDerKraftwertServiceGibtAlleKraftwertEintraegeZurueck(final List<KraftwertEintrag> erwartet)
	{
		when(kraftwertService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerKraftwertServiceGibtEinenKraftwertEintragZurueck(final KraftwertEintrag erwartet)
	{
		when(kraftwertService.erstelleKraftwert(any(Uebung.class), anyInt(), any(Benutzer.class))).thenReturn(erwartet);
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
}
