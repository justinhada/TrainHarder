package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.services.BelastungsfaktorService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;

public class BelastungsfaktorControllerSollte
{
	private BelastungsfaktorController sut;
	private BelastungsfaktorService belastungsfaktorService;

	@BeforeEach
	public void setup()
	{
		belastungsfaktorService = mock(BelastungsfaktorService.class);
		sut = new BelastungsfaktorController(belastungsfaktorService);
	}

	private void angenommenDerBelastungsfaktorServiceGibtAlleBelastungsfaktorEintraegeZurueck(
		final List<BelastungsfaktorDto> erwartet)
	{
		when(belastungsfaktorService.ermittleAlle()).thenReturn(erwartet);
	}

	private void angenommenDerBelastungsfaktorServiceGibtEinenBelastungsfaktorEintragMithilfeDerIdZurueck(
		final BelastungsfaktorDto erwartet) throws BelastungsfaktorNichtGefundenException
	{
		when(belastungsfaktorService.ermittleZuId(anyString())).thenReturn(erwartet);
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

		final var ergebnis = sut.getBelastungsfaktorZuId(erwartet.getId());

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
