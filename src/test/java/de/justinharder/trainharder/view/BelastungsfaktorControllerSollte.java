package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.services.BelastungsfaktorService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Navigator;

public class BelastungsfaktorControllerSollte
{
	private BelastungsfaktorController sut;
	private ExternerWebContext externerWebContext;
	private Navigator navigator;
	private BelastungsfaktorService belastungsfaktorService;

	@BeforeEach
	public void setup()
	{
		externerWebContext = mock(ExternerWebContext.class);
		navigator = mock(Navigator.class);
		belastungsfaktorService = mock(BelastungsfaktorService.class);
		sut = new BelastungsfaktorController(externerWebContext, navigator, belastungsfaktorService);
	}

	private void angenommenDerBelastungsfaktorServiceGibtAlleBelastungsfaktorEintraegeZurueck(
		final List<BelastungsfaktorEintrag> erwartet)
	{
		when(belastungsfaktorService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerBelastungsfaktorServiceGibtEinenBelastungsfaktorEintragMithilfeDerIdZurueck(
		final BelastungsfaktorEintrag erwartet) throws BelastungsfaktorNichtGefundenException
	{
		when(belastungsfaktorService.ermittleZuId(anyInt())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BelastungsfaktorEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDerBelastungsfaktorServiceGibtAlleBelastungsfaktorEintraegeZurueck(erwartet);

		final var ergebnis = sut.getBelastungsfaktoren();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Belastungsfaktor mit der übergebenen ID ermitteln")
	public void test02() throws BelastungsfaktorNichtGefundenException
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE;
		angenommenDerBelastungsfaktorServiceGibtEinenBelastungsfaktorEintragMithilfeDerIdZurueck(erwartet);

		sut.getBelastungsfaktorEintrag().setId(0);
		final var ergebnis = sut.getBelastungsfaktorZuId();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Belastungsfaktor weiter an den BelastungsfaktorService geben")
	public void test03()
	{
		final var belastungsfaktorEintrag = Testdaten.BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN;
		belastungsfaktorEintrag.setId(0);

		sut.getBelastungsfaktorEintrag().setBack(0.0);
		sut.getBelastungsfaktorEintrag().setBenchpress(1.0);
		sut.getBelastungsfaktorEintrag().setBiceps(0.0);
		sut.getBelastungsfaktorEintrag().setChest(1.0);
		sut.getBelastungsfaktorEintrag().setCore(0.0);
		sut.getBelastungsfaktorEintrag().setDeadlift(0.0);
		sut.getBelastungsfaktorEintrag().setGlutes(0.0);
		sut.getBelastungsfaktorEintrag().setHamstrings(0.0);
		sut.getBelastungsfaktorEintrag().setQuads(0.0);
		sut.getBelastungsfaktorEintrag().setShoulder(0.1);
		sut.getBelastungsfaktorEintrag().setSquat(0.0);
		sut.getBelastungsfaktorEintrag().setTriceps(0.7);
		sut.erstelleBelastungsfaktor();

		verify(belastungsfaktorService).erstelleBelastungsfaktor(belastungsfaktorEintrag);
	}

}
