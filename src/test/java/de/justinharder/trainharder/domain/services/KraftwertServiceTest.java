package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.KraftwertException;
import de.justinharder.trainharder.domain.model.exceptions.UebungException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.KraftwertRepository;
import de.justinharder.trainharder.domain.repository.UebungRepository;
import de.justinharder.trainharder.domain.services.mapper.KraftwertDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("KraftwertService sollte")
class KraftwertServiceTest
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

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.speichere(null, "uebungId", "benutzerId")),
			() -> assertThrows(NullPointerException.class,
				() -> sut.speichere(KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, null, "benutzerId")),
			() -> assertThrows(NullPointerException.class,
				() -> sut.speichere(KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, "uebungId", null)));
	}

	@Test
	@DisplayName("alle Kraftwerte zu Benutzer ermitteln")
	void test02()
	{
		var erwartet = List.of(KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN, KRAFTWERT_DTO_LOWBAR_KNIEBEUGE,
			KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN);
		var kraftwerte =
			List.of(KRAFTWERT_WETTKAMPFBANKDRUECKEN, KRAFTWERT_LOWBAR_KNIEBEUGE, KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
		var benutzerId = new ID().getWert().toString();
		when(kraftwertRepository.findeAlleMitBenutzer(new ID(benutzerId))).thenReturn(kraftwerte);
		when(kraftwertDtoMapper.mappeAlle(kraftwerte)).thenReturn(erwartet);

		assertThat(sut.findeAlleMitBenutzer(benutzerId)).isEqualTo(erwartet);
		verify(kraftwertRepository).findeAlleMitBenutzer(new ID(benutzerId));
		verify(kraftwertDtoMapper).mappeAlle(kraftwerte);
	}

	@Test
	@DisplayName("KraftwertException werfen, wenn ID zu keinem Kraftwert gehört")
	void test03()
	{
		var id = new ID().getWert().toString();
		when(kraftwertRepository.finde(new ID(id))).thenReturn(Optional.empty());

		assertThrows(KraftwertException.class, () -> sut.finde(id));
		verify(kraftwertRepository).finde(new ID(id));
	}

	@Test
	@DisplayName("einen Kraftwert zur ID ermitteln")
	void test04() throws KraftwertException
	{
		var erwartet = KRAFTWERT_DTO_LOWBAR_KNIEBEUGE;
		var kraftwert = KRAFTWERT_LOWBAR_KNIEBEUGE;
		var id = new ID().getWert().toString();
		when(kraftwertRepository.finde(new ID(id))).thenReturn(Optional.of(kraftwert));
		when(kraftwertDtoMapper.mappe(kraftwert)).thenReturn(erwartet);

		assertThat(sut.finde(id)).isEqualTo(erwartet);
		verify(kraftwertRepository).finde(new ID(id));
		verify(kraftwertDtoMapper).mappe(kraftwert);
	}

	@Test
	@DisplayName("UebungException werfen, wenn die Uebung nicht gefunden werden kann")
	void test05()
	{
		var uebungId = new ID().getWert().toString();
		when(uebungRepository.finde(new ID(uebungId))).thenReturn(Optional.empty());

		assertThrows(UebungException.class,
			() -> sut.speichere(KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN, uebungId,
				new ID().getWert().toString()));
		verify(uebungRepository).finde(new ID(uebungId));
	}

	@Test
	@DisplayName("BenutzerException werfen, wenn der Benutzer nicht gefunden werden kann")
	void test06()
	{
		when(uebungRepository.finde(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getId())).thenReturn(
			Optional.of(UEBUNG_KONVENTIONELLES_KREUZHEBEN));
		when(benutzerRepository.finde(BENUTZER_JUSTIN.getId())).thenReturn(Optional.empty());

		assertThrows(BenutzerException.class,
			() -> sut.speichere(KRAFTWERT_DTO_KONVENTIONELLES_KREUZHEBEN, UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN.getId(),
				BENUTZER_DTO_JUSTIN.getId()));
		verify(uebungRepository).finde(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getId());
		verify(benutzerRepository).finde(BENUTZER_JUSTIN.getId());
	}

	@Test
	@DisplayName("einen Kraftwert erstellen")
	void test07() throws UebungException, BenutzerException
	{
		when(uebungRepository.finde(UEBUNG_WETTKAMPFBANKDRUECKEN.getId())).thenReturn(
			Optional.of(UEBUNG_WETTKAMPFBANKDRUECKEN));
		when(benutzerRepository.finde(BENUTZER_JUSTIN.getId())).thenReturn(Optional.of(BENUTZER_JUSTIN));
		when(kraftwertDtoMapper.mappe(any(Kraftwert.class))).thenReturn(KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN);

		assertThat(sut.speichere(KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN,
			UEBUNG_WETTKAMPFBANKDRUECKEN.getId().getWert().toString(),
			BENUTZER_JUSTIN.getId().getWert().toString())).isEqualTo(KRAFTWERT_DTO_WETTKAMPFBANKDRUECKEN);
		verify(uebungRepository).finde(new ID(UEBUNG_WETTKAMPFBANKDRUECKEN.getId().getWert().toString()));
		verify(benutzerRepository).finde(new ID(BENUTZER_JUSTIN.getId().getWert().toString()));
		verify(kraftwertRepository).speichere(any(Kraftwert.class));
		verify(kraftwertDtoMapper).mappe(any(Kraftwert.class));
	}
}
