package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Belastung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.services.mapper.BelastungsfaktorDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BelastungServiceSollte
{
	private BelastungsfaktorService sut;

	private BelastungsfaktorRepository belastungsfaktorRepository;
	private BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@BeforeEach
	void setup()
	{
		belastungsfaktorRepository = mock(BelastungsfaktorRepository.class);
		belastungsfaktorDtoMapper = mock(BelastungsfaktorDtoMapper.class);

		sut = new BelastungsfaktorService(belastungsfaktorRepository, belastungsfaktorDtoMapper);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(String id, Optional<Belastung> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtNullZurueck(String id)
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(id, Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(Belastung belastung)
	{
		when(belastungsfaktorRepository.speichereBelastungsfaktor(any(Belastung.class))).thenReturn(belastung);
	}

	private void angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Belastung belastung, BelastungDto belastungDto)
	{
		when(belastungsfaktorDtoMapper.mappe(belastung)).thenReturn(belastungDto);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichereBelastungsfaktor(null)));
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn ID zu keinem Belastungsfaktor gehört")
	void test02()
	{
		var id = new Primaerschluessel().getId().toString();
		angenommenDasBelastungsfaktorRepositoryGibtNullZurueck(id);

		assertThrows(BelastungsfaktorNichtGefundenException.class, () -> sut.ermittleZuId(id));
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Belastungsfaktor zur ID ermitteln")
	void test03() throws BelastungsfaktorNichtGefundenException
	{
		var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN;
		var belastungsfaktor = Testdaten.BELASTUNG_KONVENTIONELLES_KREUZHEBEN;
		var id = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID.getId().toString();
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(id, Optional.of(belastungsfaktor));
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(belastungsfaktor, erwartet);

		assertThat(sut.ermittleZuId(id)).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(id));
		verify(belastungsfaktorDtoMapper).mappe(belastungsfaktor);
	}

	@Test
	@DisplayName("einen Belastungsfaktor speichern")
	void test04()
	{
		var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN;
		var belastungsfaktor = Testdaten.BELASTUNG_WETTKAMPFBANKDRUECKEN;
		angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(belastungsfaktor);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(belastungsfaktor, erwartet);

		assertThat(sut.speichereBelastungsfaktor(erwartet)).isEqualTo(erwartet);
		verify(belastungsfaktorDtoMapper).mappe(belastungsfaktor);
	}
}