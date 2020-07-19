package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import de.justinharder.trainharder.model.services.mapper.KraftwertDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.KraftwertDto;

public class KraftwertServiceSollte
{
	private KraftwertService sut;

	private KraftwertRepository kraftwertRepository;
	private BenutzerRepository benutzerRepository;
	private UebungRepository uebungRepository;
	private KraftwertDtoMapper kraftwertDtoMapper;

	@BeforeEach
	public void setup()
	{
		kraftwertRepository = mock(KraftwertRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		uebungRepository = mock(UebungRepository.class);
		kraftwertDtoMapper = mock(KraftwertDtoMapper.class);

		sut = new KraftwertService(kraftwertRepository, benutzerRepository, uebungRepository, kraftwertDtoMapper);
	}

	private void angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(final String benutzerId,
		final List<Kraftwert> kraftwerte)
	{
		when(kraftwertRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId))).thenReturn(kraftwerte);
	}

	private void angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(final String id,
		final Optional<Kraftwert> kraftwert)
	{
		when(kraftwertRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(kraftwert);
	}

	private void angenommenDasKraftwertRepositoryGibtNullZurueck(final String id)
	{
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(id, Optional.empty());
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(final String benutzerId,
		final Optional<Uebung> uebung)
	{
		when(uebungRepository.ermittleZuId(new Primaerschluessel(benutzerId))).thenReturn(uebung);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(final String benutzerId,
		final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))).thenReturn(benutzer);
	}

	private void angenommenDasUebungRepositoryErmitteltKeineUebung(final String id)
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(id, Optional.empty());
	}

	private void angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer(final String benutzerId)
	{
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzerId, Optional.empty());
	}

	private void angenommenDasKraftwertRepositorySpeichertKraftwert(final Kraftwert kraftwert)
	{
		when(kraftwertRepository.speichereKraftwert(any(Kraftwert.class))).thenReturn(kraftwert);
	}

	private void angenommenDerKraftwertDtoMapperKonvertiertAlleZuKraftwertDto(final List<Kraftwert> kraftwerte,
		final List<KraftwertDto> kraftwertDtos)
	{
		when(kraftwertDtoMapper.konvertiereAlle(kraftwerte)).thenReturn(kraftwertDtos);
	}

	private void angenommenDerKraftwertDtoMapperKonvertiertZuKraftwertDto(final Kraftwert kraftwert,
		final KraftwertDto kraftwertDto)
	{
		when(kraftwertDtoMapper.konvertiere(kraftwert)).thenReturn(kraftwertDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BenutzerID null ist")
	public void test01()
	{
		final var erwartet = "Die Ermittlung des Kraftwerts benötigt eine gültige BenutzerID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuBenutzer(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
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
		final var benutzerId = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(benutzerId, kraftwerte);
		angenommenDerKraftwertDtoMapperKonvertiertAlleZuKraftwertDto(kraftwerte, erwartet);

		final var ergebnis = sut.ermittleAlleZuBenutzer(benutzerId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kraftwertRepository).ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId));
		verify(kraftwertDtoMapper).konvertiereAlle(kraftwerte);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ID null ist")
	public void test03()
	{
		final var erwartet = "Die Ermittlung des Kraftwerts benötigt eine gültige KraftwertID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("KraftwertNichtGefundenException werfen, wenn ID zu keinem Kraftwert gehört")
	public void test04()
	{
		final var id = new Primaerschluessel().getId().toString();
		angenommenDasKraftwertRepositoryGibtNullZurueck(id);

		final var exception = assertThrows(KraftwertNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Kraftwert mit der ID \"" + id + "\" existiert nicht!");
		verify(kraftwertRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Kraftwert zur ID ermitteln")
	public void test05() throws KraftwertNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE;
		final var kraftwert = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE;
		final var id = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE_ID.getId().toString();
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(id, Optional.of(kraftwert));
		angenommenDerKraftwertDtoMapperKonvertiertZuKraftwertDto(kraftwert, erwartet);

		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kraftwertRepository).ermittleZuId(new Primaerschluessel(id));
		verify(kraftwertDtoMapper).konvertiere(kraftwert);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn die Uebung nicht gefunden werden kann")
	public void test06()
	{
		final var uebungId = new Primaerschluessel().getId().toString();
		final var erwartet = "Die Uebung mit der ID \"" + uebungId + "\" existiert nicht!";
		angenommenDasUebungRepositoryErmitteltKeineUebung(uebungId);

		final var exception = assertThrows(UebungNichtGefundenException.class, () -> sut.speichereKraftwert(
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN,
			uebungId,
			Testdaten.BENUTZER_JUSTIN_ID.getId().toString()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn der Benutzer nicht gefunden werden kann")
	public void test07()
	{
		final var benutzerId = new Primaerschluessel().getId().toString();
		final var erwartet = "Der Benutzer mit der ID \"" + benutzerId + "\" existiert nicht!";
		final var uebungId = Testdaten.WETTKAMPFBANKDRUECKEN_ID.getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(
			uebungId,
			Optional.of(Testdaten.KONVENTIONELLES_KREUZHEBEN));
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer(benutzerId);

		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.speichereKraftwert(
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN,
			uebungId,
			benutzerId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(benutzerId));
	}

	@Test
	@DisplayName("einen Kraftwert erstellen")
	public void test08() throws UebungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN;
		final var kraftwert = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;
		final var uebung = Testdaten.WETTKAMPFBANKDRUECKEN;
		final var uebungId = Testdaten.WETTKAMPFBANKDRUECKEN_ID.getId().toString();
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var benutzerId = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(uebungId, Optional.of(uebung));
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzerId, Optional.of(benutzer));
		angenommenDasKraftwertRepositorySpeichertKraftwert(kraftwert);
		angenommenDerKraftwertDtoMapperKonvertiertZuKraftwertDto(kraftwert, erwartet);

		final var ergebnis = sut.speichereKraftwert(
			erwartet,
			uebung.getPrimaerschluessel().getId().toString(),
			benutzer.getPrimaerschluessel().getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(benutzerId));
		verify(kraftwertDtoMapper).konvertiere(kraftwert);
	}
}
