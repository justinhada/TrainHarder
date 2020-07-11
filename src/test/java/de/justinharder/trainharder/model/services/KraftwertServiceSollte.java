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

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.setup.Testdaten;

public class KraftwertServiceSollte
{
	private KraftwertService sut;
	private KraftwertRepository kraftwertRepository;
	private BenutzerRepository benutzerRepository;
	private UebungRepository uebungRepository;

	@BeforeEach
	public void setup()
	{
		kraftwertRepository = mock(KraftwertRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		uebungRepository = mock(UebungRepository.class);
		sut = new KraftwertService(kraftwertRepository, benutzerRepository, uebungRepository);
	}

	private void angenommenDasKraftwertRepositoryGibtAlleKraftwerteZurueck(final List<Kraftwert> kraftwerte)
	{
		when(kraftwertRepository.ermittleAlle()).thenReturn(kraftwerte);
	}

	private void angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(final List<Kraftwert> kraftwerte)
	{
		when(kraftwertRepository.ermittleAlleZuBenutzer(any(Primaerschluessel.class))).thenReturn(kraftwerte);
	}

	private void angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(final Optional<Kraftwert> kraftwert)
	{
		when(kraftwertRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(kraftwert);
	}

	private void angenommenDasKraftwertRepositoryGibtNullZurueck()
	{
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(Optional.empty());
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(final Optional<Uebung> uebung)
	{
		when(uebungRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(uebung);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(benutzer);
	}

	private void angenommenDasUebungRepositoryErmitteltKeineUebung()
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(Optional.empty());
	}

	private void angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer()
	{
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(Optional.empty());
	}

	private void angenommenDasKraftwertRepositorySpeichertKraftwert(final Kraftwert kraftwert)
	{
		when(kraftwertRepository.speichereKraftwert(any(Kraftwert.class))).thenReturn(kraftwert);
	}

	@Test
	@DisplayName("alle Kraftwerte ermitteln")
	public void test01()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var kraftwerte = List.of(
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZurueck(kraftwerte);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	public void test02()
	{
		final var erwartet = List.of(
			Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var kraftwerte = List.of(
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(kraftwerte);

		final var ergebnis = sut.ermittleAlleZuBenutzer(new Primaerschluessel().getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn die Uebung nicht gefunden werden kann")
	public void test031()
	{
		final var id = new Primaerschluessel().getId().toString();
		final var erwartet = "Die Uebung mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasUebungRepositoryErmitteltKeineUebung();

		final var exception = assertThrows(UebungNichtGefundenException.class, () -> sut
			.speichereKraftwert(Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN, id,
				Testdaten.BENUTZER_JUSTIN_ID.getId().toString()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn der Benutzer nicht gefunden werden kann")
	public void test032()
	{
		final var id = new Primaerschluessel().getId().toString();
		final var erwartet = "Der Benutzer mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasUebungRepositoryGibtEineUebungZurueck(Optional.of(Testdaten.KONVENTIONELLES_KREUZHEBEN));
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer();

		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut
			.speichereKraftwert(Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN,
				Testdaten.WETTKAMPFBANKDRUECKEN_ID.getId().toString(), id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert erstellen")
	public void test03() throws UebungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var uebung = Testdaten.WETTKAMPFBANKDRUECKEN;
		final var kraftwert = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;
		angenommenDasUebungRepositoryGibtEineUebungZurueck(Optional.of(uebung));
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(Optional.of(benutzer));
		angenommenDasKraftwertRepositorySpeichertKraftwert(kraftwert);

		final var ergebnis = sut.speichereKraftwert(
			erwartet,
			uebung.getPrimaerschluessel().getId().toString(),
			benutzer.getPrimaerschluessel().getId().toString());

		assertAll(
			() -> assertThat(ergebnis.getMaximum()).isEqualTo(erwartet.getMaximum()),
			() -> assertThat(ergebnis.getKoerpergewicht()).isEqualTo(erwartet.getKoerpergewicht()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(erwartet.getDatum()),
			() -> assertThat(ergebnis.getWiederholungen()).isEqualTo(erwartet.getWiederholungen()));
	}

	@Test
	@DisplayName("KraftwertNichtGefundenException werfen, wenn ID zu keinem Kraftwert gehört")
	public void test04()
	{
		angenommenDasKraftwertRepositoryGibtNullZurueck();

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(KraftwertNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Kraftwert mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Kraftwert zur ID ermitteln")
	public void test05() throws KraftwertNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE;
		final var kraftwert = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE;
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(Optional.of(kraftwert));

		final var id = kraftwert.getPrimaerschluessel().getId().toString();
		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
