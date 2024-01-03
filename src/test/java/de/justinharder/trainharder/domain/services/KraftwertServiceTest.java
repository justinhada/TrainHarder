package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.domain.model.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.domain.model.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.KraftwertRepository;
import de.justinharder.trainharder.domain.repository.UebungRepository;
import de.justinharder.trainharder.domain.services.dto.KraftwertDto;
import de.justinharder.trainharder.domain.services.mapper.KraftwertDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KraftwertServiceSollte
{
	private KraftwertService sut;

	private KraftwertRepository kraftwertRepository;
	private BenutzerRepository benutzerRepository;
	private UebungRepository uebungRepository;
	private KraftwertDtoMapper kraftwertDtoMapper;

	@BeforeEach
	void setup()
	{
		kraftwertRepository = mock(KraftwertRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		uebungRepository = mock(UebungRepository.class);
		kraftwertDtoMapper = mock(KraftwertDtoMapper.class);

		sut = new KraftwertService(kraftwertRepository, benutzerRepository, uebungRepository, kraftwertDtoMapper);
	}

	private void angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(String benutzerId,
		List<Kraftwert> kraftwerte)
	{
		when(kraftwertRepository.ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId))).thenReturn(kraftwerte);
	}

	private void angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(String id, Optional<Kraftwert> kraftwert)
	{
		when(kraftwertRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(kraftwert);
	}

	private void angenommenDasKraftwertRepositoryGibtNullZurueck(String id)
	{
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(id, Optional.empty());
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(String benutzerId, Optional<Uebung> uebung)
	{
		when(uebungRepository.ermittleZuId(new Primaerschluessel(benutzerId))).thenReturn(uebung);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(String benutzerId, Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(new Primaerschluessel(benutzerId))).thenReturn(benutzer);
	}

	private void angenommenDasUebungRepositoryErmitteltKeineUebung(String id)
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(id, Optional.empty());
	}

	private void angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer(String benutzerId)
	{
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzerId, Optional.empty());
	}

	private void angenommenDasKraftwertRepositorySpeichertKraftwert(Kraftwert kraftwert)
	{
		when(kraftwertRepository.speichereKraftwert(any(Kraftwert.class))).thenReturn(kraftwert);
	}

	private void angenommenDerKraftwertDtoMapperMapptAlleZuKraftwertDto(List<Kraftwert> kraftwerte,
		List<KraftwertDto> kraftwertDtos)
	{
		when(kraftwertDtoMapper.mappeAlle(kraftwerte)).thenReturn(kraftwertDtos);
	}

	private void angenommenDerKraftwertDtoMapperMapptZuKraftwertDto(Kraftwert kraftwert, KraftwertDto kraftwertDto)
	{
		when(kraftwertDtoMapper.mappe(kraftwert)).thenReturn(kraftwertDto);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleAlleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.speichereKraftwert(null, "uebungId", "benutzerId")),
			() -> assertThrows(NullPointerException.class,
				() -> sut.speichereKraftwert(Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, null, "benutzerId")),
			() -> assertThrows(NullPointerException.class,
				() -> sut.speichereKraftwert(Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, "uebungId", null)));
	}

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	void test02()
	{
		var erwartet = List.of(Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		var kraftwerte = List.of(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN, Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		var benutzerId = new Primaerschluessel().getId().toString();
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(benutzerId, kraftwerte);
		angenommenDerKraftwertDtoMapperMapptAlleZuKraftwertDto(kraftwerte, erwartet);

		assertThat(sut.ermittleAlleZuBenutzer(benutzerId)).isEqualTo(erwartet);
		verify(kraftwertRepository).ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId));
		verify(kraftwertDtoMapper).mappeAlle(kraftwerte);
	}

	@Test
	@DisplayName("KraftwertNichtGefundenException werfen, wenn ID zu keinem Kraftwert gehört")
	void test03()
	{
		var id = new Primaerschluessel().getId().toString();
		angenommenDasKraftwertRepositoryGibtNullZurueck(id);

		assertThrows(KraftwertNichtGefundenException.class, () -> sut.ermittleZuId(id));
		verify(kraftwertRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Kraftwert zur ID ermitteln")
	void test04() throws KraftwertNichtGefundenException
	{
		var erwartet = Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE;
		var kraftwert = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE;
		var id = new Primaerschluessel().getId().toString();
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(id, Optional.of(kraftwert));
		angenommenDerKraftwertDtoMapperMapptZuKraftwertDto(kraftwert, erwartet);

		assertThat(sut.ermittleZuId(id)).isEqualTo(erwartet);
		verify(kraftwertRepository).ermittleZuId(new Primaerschluessel(id));
		verify(kraftwertDtoMapper).mappe(kraftwert);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn die Uebung nicht gefunden werden kann")
	void test05()
	{
		var uebungId = new Primaerschluessel().getId().toString();
		angenommenDasUebungRepositoryErmitteltKeineUebung(uebungId);

		assertThrows(UebungNichtGefundenException.class,
			() -> sut.speichereKraftwert(Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN, uebungId,
				new Primaerschluessel().getId().toString()));
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn der Benutzer nicht gefunden werden kann")
	void test06()
	{
		var benutzerId = new Primaerschluessel().getId().toString();
		var uebungId = new Primaerschluessel().getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(uebungId,
			Optional.of(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN));
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer(benutzerId);

		assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.speichereKraftwert(Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN, uebungId, benutzerId));
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(benutzerId));
	}

	@Test
	@DisplayName("einen Kraftwert erstellen")
	void test07() throws UebungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN;
		var kraftwert = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;
		var uebung = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN;
		var uebungId = new Primaerschluessel().getId().toString();
		var benutzer = Testdaten.BENUTZER_JUSTIN;
		var benutzerId = new Primaerschluessel().getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(uebungId, Optional.of(uebung));
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzerId, Optional.of(benutzer));
		angenommenDasKraftwertRepositorySpeichertKraftwert(kraftwert);
		angenommenDerKraftwertDtoMapperMapptZuKraftwertDto(kraftwert, erwartet);

		assertThat(sut.speichereKraftwert(erwartet, uebung.getPrimaerschluessel().getId().toString(),
			benutzer.getPrimaerschluessel().getId().toString())).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(benutzerId));
		verify(kraftwertDtoMapper).mappe(kraftwert);
	}
}
