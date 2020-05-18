package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.setup.Testdaten;

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
		final Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtNullZurueck()
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(Optional.empty());
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

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(BelastungsfaktorNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Belastungsfaktor zur ID ermitteln")
	public void test03() throws BelastungsfaktorNichtGefundenException
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOREINTRAG_KONVENTIONELLES_KREUZHEBEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN;
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(Optional.of(belastungsfaktor));

		final var id = new Primaerschluessel().getId().toString();
		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Belastungsfaktor erstellen")
	@Disabled("unerklärliche NullPointerException beim Konvertieren der UUID")
	public void test04()
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOREINTRAG_WETTKAMPFBANKDRUECKEN;

		final var ergebnis = sut.speichereBelastungsfaktor(erwartet);

		assertAll(
			() -> assertThat(ergebnis.getSquat()).isEqualTo(erwartet.getSquat()),
			() -> assertThat(ergebnis.getBenchpress()).isEqualTo(erwartet.getBenchpress()),
			() -> assertThat(ergebnis.getDeadlift()).isEqualTo(erwartet.getDeadlift()),
			() -> assertThat(ergebnis.getTriceps()).isEqualTo(erwartet.getTriceps()),
			() -> assertThat(ergebnis.getChest()).isEqualTo(erwartet.getChest()),
			() -> assertThat(ergebnis.getCore()).isEqualTo(erwartet.getCore()),
			() -> assertThat(ergebnis.getBack()).isEqualTo(erwartet.getBack()),
			() -> assertThat(ergebnis.getBiceps()).isEqualTo(erwartet.getBiceps()),
			() -> assertThat(ergebnis.getGlutes()).isEqualTo(erwartet.getGlutes()),
			() -> assertThat(ergebnis.getQuads()).isEqualTo(erwartet.getQuads()),
			() -> assertThat(ergebnis.getHamstrings()).isEqualTo(erwartet.getHamstrings()),
			() -> assertThat(ergebnis.getShoulder()).isEqualTo(erwartet.getShoulder()));
	}
}
