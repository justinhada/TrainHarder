package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BelastungsfaktorRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class BelastungsfaktorServiceSollte
{
	private BelastungsfaktorService sut;
	private BelastungsfaktorRepository belastungsfaktorRepository;

	@BeforeEach
	public void setup()
	{
		belastungsfaktorRepository = mock(BelastungsfaktorRepository.class);
		sut = new BelastungsfaktorService(belastungsfaktorRepository);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtAlleBelastungsfaktorenZurueck(
		final List<Belastungsfaktor> belastungsfaktoren)
	{
		when(belastungsfaktorRepository.ermittleAlle()).thenReturn(belastungsfaktoren);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(
		final Belastungsfaktor belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(anyInt())).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtNullZurueck()
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(null);
	}

	@Test
	@DisplayName("alle Belastungsfaktoren ermitteln")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOREINTRAG_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN);
		final var belastungsfaktoren = List.of(
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE,
			Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasBelastungsfaktorRepositoryGibtAlleBelastungsfaktorenZurueck(belastungsfaktoren);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn ID zu keinem Belastungsfaktor gehört")
	public void test02()
	{
		angenommenDasBelastungsfaktorRepositoryGibtNullZurueck();

		final var exception = assertThrows(BelastungsfaktorNichtGefundenException.class, () -> sut.ermittleZuId(10000));

		assertThat(exception.getMessage()).isEqualTo("Der Belastungsfaktor mit der ID \"10000\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Belastungsfaktor zur ID ermitteln")
	public void test03() throws BelastungsfaktorNichtGefundenException
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN;
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(belastungsfaktor);

		final var ergebnis = sut.ermittleZuId(0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Belastungsfaktor erstellen")
	public void test04()
	{
		final var belastungsfaktorEintrag = Testdaten.BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;

		sut.erstelleBelastungsfaktor(belastungsfaktorEintrag);

		verify(belastungsfaktorRepository).erstelleBelastungsfaktor(belastungsfaktor);
	}
}
