package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.repository.KraftwertRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.model.services.mapper.KraftwertDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.KraftwertDto;
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
			() -> assertThrows(NullPointerException.class, () -> sut.speichereKraftwert(null,
				"uebungId", "benutzerId")),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereKraftwert(
				Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, null, "benutzerId")),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereKraftwert(
				Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, "uebungId", null)));
	}

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	void test02()
	{
		var erwartet = List.of(
			Testdaten.KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		var kraftwerte = List.of(
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		var benutzerId = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		angenommenDasKraftwertRepositoryGibtAlleKraftwerteZuBenutzerZurueck(benutzerId, kraftwerte);
		angenommenDerKraftwertDtoMapperMapptAlleZuKraftwertDto(kraftwerte, erwartet);

		var ergebnis = sut.ermittleAlleZuBenutzer(benutzerId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kraftwertRepository).ermittleAlleZuBenutzer(new Primaerschluessel(benutzerId));
		verify(kraftwertDtoMapper).mappeAlle(kraftwerte);
	}

	@Test
	@DisplayName("KraftwertNichtGefundenException werfen, wenn ID zu keinem Kraftwert gehört")
	void test03()
	{
		var id = new Primaerschluessel().getId().toString();
		angenommenDasKraftwertRepositoryGibtNullZurueck(id);

		var exception = assertThrows(KraftwertNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Kraftwert mit der ID \"" + id + "\" existiert nicht!");
		verify(kraftwertRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Kraftwert zur ID ermitteln")
	void test04() throws KraftwertNichtGefundenException
	{
		var erwartet = Testdaten.KRAFTWERT_DTO_LOWBAR_KNIEBEUGE;
		var kraftwert = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE;
		var id = Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE_ID.getId().toString();
		angenommenDasKraftwertRepositoryGibtEinenKraftwertZurueck(id, Optional.of(kraftwert));
		angenommenDerKraftwertDtoMapperMapptZuKraftwertDto(kraftwert, erwartet);

		var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kraftwertRepository).ermittleZuId(new Primaerschluessel(id));
		verify(kraftwertDtoMapper).mappe(kraftwert);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn die Uebung nicht gefunden werden kann")
	void test05()
	{
		var uebungId = new Primaerschluessel().getId().toString();
		var erwartet = "Die Uebung mit der ID \"" + uebungId + "\" existiert nicht!";
		angenommenDasUebungRepositoryErmitteltKeineUebung(uebungId);

		var exception = assertThrows(UebungNichtGefundenException.class, () -> sut.speichereKraftwert(
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN,
			uebungId,
			Testdaten.BENUTZER_JUSTIN_ID.getId().toString()));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn der Benutzer nicht gefunden werden kann")
	void test06()
	{
		var benutzerId = new Primaerschluessel().getId().toString();
		var erwartet = "Der Benutzer mit der ID \"" + benutzerId + "\" existiert nicht!";
		var uebungId = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN_ID.getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(
			uebungId,
			Optional.of(Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN));
		angenommenDasBenutzerRepositoryErmitteltKeinenBenutzer(benutzerId);

		var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.speichereKraftwert(
			Testdaten.KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN,
			uebungId,
			benutzerId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
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
		var uebungId = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN_ID.getId().toString();
		var benutzer = Testdaten.BENUTZER_JUSTIN;
		var benutzerId = Testdaten.BENUTZER_JUSTIN_ID.getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(uebungId, Optional.of(uebung));
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(benutzerId, Optional.of(benutzer));
		angenommenDasKraftwertRepositorySpeichertKraftwert(kraftwert);
		angenommenDerKraftwertDtoMapperMapptZuKraftwertDto(kraftwert, erwartet);

		var ergebnis = sut.speichereKraftwert(
			erwartet,
			uebung.getPrimaerschluessel().getId().toString(),
			benutzer.getPrimaerschluessel().getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(uebungId));
		verify(benutzerRepository).ermittleZuId(new Primaerschluessel(benutzerId));
		verify(kraftwertDtoMapper).mappe(kraftwert);
	}
}
