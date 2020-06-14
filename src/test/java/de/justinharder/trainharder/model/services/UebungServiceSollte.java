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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.setup.Testdaten;

public class UebungServiceSollte
{
	private UebungService sut;
	private UebungRepository uebungRepository;
	private BelastungsfaktorRepository belastungsfaktorRepository;

	@BeforeEach
	public void setup()
	{
		uebungRepository = mock(UebungRepository.class);
		belastungsfaktorRepository = mock(BelastungsfaktorRepository.class);
		sut = new UebungService(uebungRepository, belastungsfaktorRepository);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZurueck(final List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlle()).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(final List<Uebung> uebungen)
		throws UebungNichtGefundenException
	{
		when(uebungRepository.ermittleZuUebungsart(any(Uebungsart.class))).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(final List<Uebung> uebungen)
		throws UebungNichtGefundenException
	{
		when(uebungRepository.ermittleZuUebungskategorie(any(Uebungskategorie.class))).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(final Optional<Uebung> uebung)
	{
		when(uebungRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(uebung);
	}

	private void angenommenDasUebungRepositoryGibtNullZurueck()
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(
		final Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryErmitteltKeinenBelastungsfaktor()
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(Optional.empty());
	}

	private void angenommenDasUebungRepositorySpeichertUebung(final Uebung uebung)
	{
		when(uebungRepository.speichereUebung(any(Uebung.class))).thenReturn(uebung);
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
	public void test02() throws UebungNichtGefundenException
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
	public void test03() throws UebungNichtGefundenException
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

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(UebungNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Die Uebung mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("eine Uebung zur ID ermitteln")
	public void test05() throws UebungNichtGefundenException
	{
		final var erwartet = Testdaten.UEBUNGEINTRAG_KONVENTIONELLES_KREUZHEBEN;
		final var uebung = Testdaten.KONVENTIONELLES_KREUZHEBEN;
		angenommenDasUebungRepositoryGibtEineUebungZurueck(Optional.of(uebung));

		final var ergebnis = sut.ermittleZuId(new Primaerschluessel().getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn kein Belastungsfaktor gefunden werden kann")
	public void test06()
	{
		final var id = new Primaerschluessel().getId().toString();
		final var erwartet = "Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasBelastungsfaktorRepositoryErmitteltKeinenBelastungsfaktor();

		final var exception = assertThrows(BelastungsfaktorNichtGefundenException.class,
			() -> sut.speichereUebung(Testdaten.UEBUNGEINTRAG_WETTKAMPFBANKDRUECKEN, id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Uebung erstellen")
	public void test07() throws BelastungsfaktorNichtGefundenException
	{
		final var uebungEintrag = Testdaten.UEBUNGEINTRAG_LOWBAR_KNIEBEUGE;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE;
		final var uebung = Testdaten.WETTKAMPFBANKDRUECKEN;
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(Optional.of(belastungsfaktor));
		angenommenDasUebungRepositorySpeichertUebung(uebung);

		final var ergebnis =
			sut.speichereUebung(uebungEintrag, belastungsfaktor.getPrimaerschluessel().getId().toString());

		assertAll(() -> assertThat(ergebnis));
	}
}
