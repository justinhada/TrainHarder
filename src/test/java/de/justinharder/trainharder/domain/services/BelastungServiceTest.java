package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.exceptions.BelastungException;
import de.justinharder.trainharder.domain.repository.BelastungRepository;
import de.justinharder.trainharder.domain.services.mapper.BelastungDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BelastungService sollte")
class BelastungServiceTest
{
	private BelastungService sut;

	private BelastungRepository belastungRepository;
	private BelastungDtoMapper belastungDtoMapper;

	@BeforeEach
	void setup()
	{
		belastungRepository = mock(BelastungRepository.class);
		belastungDtoMapper = mock(BelastungDtoMapper.class);

		sut = new BelastungService(belastungRepository, belastungDtoMapper);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)));
	}

	@Test
	@DisplayName("BelastungException werfen, wenn ID zu keiner Belastung gehört")
	void test02()
	{
		when(belastungRepository.finde(BELASTUNG_KONVENTIONELLES_KREUZHEBEN.getId())).thenReturn(Optional.empty());

		assertThrows(BelastungException.class, () -> sut.finde(BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN.getId()));
		verify(belastungRepository).finde(BELASTUNG_KONVENTIONELLES_KREUZHEBEN.getId());
	}

	@Test
	@DisplayName("eine Belastung zur ID ermitteln")
	void test03() throws BelastungException
	{
		when(belastungRepository.finde(BELASTUNG_KONVENTIONELLES_KREUZHEBEN.getId())).thenReturn(
			Optional.of(BELASTUNG_KONVENTIONELLES_KREUZHEBEN));
		when(belastungDtoMapper.mappe(BELASTUNG_KONVENTIONELLES_KREUZHEBEN)).thenReturn(
			BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);

		assertThat(sut.finde(BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN.getId())).isEqualTo(
			BELASTUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		verify(belastungRepository).finde(BELASTUNG_KONVENTIONELLES_KREUZHEBEN.getId());
		verify(belastungDtoMapper).mappe(BELASTUNG_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("eine Belastung speichern")
	void test04()
	{
		when(belastungRepository.finde(BELASTUNG_WETTKAMPFBANKDRUECKEN.getId())).thenReturn(
			Optional.of(BELASTUNG_WETTKAMPFBANKDRUECKEN));
		when(belastungDtoMapper.mappe(BELASTUNG_WETTKAMPFBANKDRUECKEN)).thenReturn(BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);

		assertThat(sut.speichere(BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN)).isEqualTo(BELASTUNG_DTO_WETTKAMPFBANKDRUECKEN);
		verify(belastungRepository).speichere(BELASTUNG_WETTKAMPFBANKDRUECKEN);
		verify(belastungDtoMapper).mappe(BELASTUNG_WETTKAMPFBANKDRUECKEN);
	}
}
