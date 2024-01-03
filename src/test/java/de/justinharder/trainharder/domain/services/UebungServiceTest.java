package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.enums.Uebungsart;
import de.justinharder.trainharder.domain.model.enums.Uebungskategorie;
import de.justinharder.trainharder.domain.model.exceptions.BelastungException;
import de.justinharder.trainharder.domain.model.exceptions.UebungException;
import de.justinharder.trainharder.domain.repository.BelastungRepository;
import de.justinharder.trainharder.domain.repository.UebungRepository;
import de.justinharder.trainharder.domain.services.mapper.UebungDtoMapper;
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

@DisplayName("UebungService sollte")
class UebungServiceTest
{
	private UebungService sut;

	private UebungRepository uebungRepository;
	private BelastungRepository belastungRepository;
	private UebungDtoMapper uebungDtoMapper;

	@BeforeEach
	void setup()
	{
		uebungRepository = mock(UebungRepository.class);
		belastungRepository = mock(BelastungRepository.class);
		uebungDtoMapper = mock(UebungDtoMapper.class);

		sut = new UebungService(uebungRepository, belastungRepository, uebungDtoMapper);
	}

	@Test
	@DisplayName("alle finden")
	void test01()
	{
		var erwartet = List.of(UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		var uebungen =
			List.of(UEBUNG_WETTKAMPFBANKDRUECKEN, UEBUNG_LOWBAR_KNIEBEUGE, UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		when(uebungRepository.findeAlle()).thenReturn(uebungen);
		when(uebungDtoMapper.mappeAlle(uebungen)).thenReturn(erwartet);

		assertThat(sut.findeAlle()).isEqualTo(erwartet);
		verify(uebungRepository).findeAlle();
		verify(uebungDtoMapper).mappeAlle(uebungen);
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.erstelle(UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.erstelle(null, BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN.getId())));
	}

	@Test
	@DisplayName("alle mit Uebungsart finden")
	void test03()
	{
		var erwartet = List.of(UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		var uebungen =
			List.of(UEBUNG_WETTKAMPFBANKDRUECKEN, UEBUNG_LOWBAR_KNIEBEUGE, UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		var uebungsart = "GRUNDUEBUNG";
		when(uebungRepository.findeAlleMitUebungsart(Uebungsart.zuWert(uebungsart))).thenReturn(uebungen);
		when(uebungDtoMapper.mappeAlle(uebungen)).thenReturn(erwartet);

		assertThat(sut.findeAlleMitUebungsart(uebungsart)).isEqualTo(erwartet);
		verify(uebungRepository).findeAlleMitUebungsart(Uebungsart.zuWert(uebungsart));
		verify(uebungDtoMapper).mappeAlle(uebungen);
	}

	@Test
	@DisplayName("alle mit Uebungskategorie finden")
	void test04()
	{
		var erwartet = List.of(UEBUNG_DTO_LOWBAR_KNIEBEUGE);
		var uebungen = List.of(UEBUNG_LOWBAR_KNIEBEUGE);
		var uebungskategorie = "WETTKAMPF_KNIEBEUGE";
		when(uebungRepository.findeAlleMitUebungskategorie(Uebungskategorie.zuWert(uebungskategorie))).thenReturn(
			uebungen);
		when(uebungDtoMapper.mappeAlle(uebungen)).thenReturn(erwartet);

		assertThat(sut.findeAlleMitUebungskategorie(uebungskategorie)).isEqualTo(erwartet);
		verify(uebungRepository).findeAlleMitUebungskategorie(Uebungskategorie.zuWert(uebungskategorie));
		verify(uebungDtoMapper).mappeAlle(uebungen);
	}

	@Test
	@DisplayName("UebungException werfen, wenn die UebungID nicht existiert")
	void test05()
	{
		when(uebungRepository.finde(UEBUNG_WETTKAMPFBANKDRUECKEN.getId())).thenReturn(Optional.empty());

		assertThrows(UebungException.class, () -> sut.finde(UEBUNG_DTO_WETTKAMPFBANKDRUECKEN.getId()));
		verify(uebungRepository).finde(UEBUNG_WETTKAMPFBANKDRUECKEN.getId());
	}

	@Test
	@DisplayName("finden")
	void test06() throws UebungException
	{
		when(uebungRepository.finde(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getId())).thenReturn(
			Optional.of(UEBUNG_KONVENTIONELLES_KREUZHEBEN));
		when(uebungDtoMapper.mappe(UEBUNG_KONVENTIONELLES_KREUZHEBEN)).thenReturn(
			UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

		assertThat(sut.finde(UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN.getId())).isEqualTo(
			UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		verify(uebungRepository).finde(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getId());
		verify(uebungDtoMapper).mappe(UEBUNG_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("BelastungException werfen, wenn die BelastungID nicht existiert")
	void test07()
	{
		when(belastungRepository.finde(BELASTUNG_WETTKAMPFBANKDRUECKEN.getId())).thenReturn(Optional.empty());

		assertThrows(BelastungException.class,
			() -> sut.erstelle(UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN.getId()));
		verify(belastungRepository).finde(BELASTUNG_WETTKAMPFBANKDRUECKEN.getId());
	}

	@Test
	@DisplayName("erstellen")
	void test08() throws BelastungException
	{
		when(belastungRepository.finde(BELASTUNG_LOWBAR_KNIEBEUGE.getId())).thenReturn(
			Optional.of(BELASTUNG_LOWBAR_KNIEBEUGE));
		when(uebungDtoMapper.mappe(any(Uebung.class))).thenReturn(UEBUNG_DTO_LOWBAR_KNIEBEUGE);

		assertThat(sut.erstelle(UEBUNG_DTO_LOWBAR_KNIEBEUGE, BELASTUNG_DTO_LOWBAR_KNIEBEUGE.getId())).isEqualTo(
			UEBUNG_DTO_LOWBAR_KNIEBEUGE);
		verify(belastungRepository).finde(BELASTUNG_LOWBAR_KNIEBEUGE.getId());
		verify(uebungRepository).speichere(any(Uebung.class));
		verify(uebungDtoMapper).mappe(any(Uebung.class));
	}
}
