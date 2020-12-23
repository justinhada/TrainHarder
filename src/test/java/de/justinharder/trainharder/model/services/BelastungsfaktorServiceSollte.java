package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.services.mapper.BelastungsfaktorDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BelastungsfaktorServiceSollte
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

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(String id,
		Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtNullZurueck(String id)
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(id, Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(Belastungsfaktor belastungsfaktor)
	{
		when(belastungsfaktorRepository.speichereBelastungsfaktor(any(Belastungsfaktor.class)))
			.thenReturn(belastungsfaktor);
	}

	private void angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(Belastungsfaktor belastungsfaktor,
		BelastungsfaktorDto belastungsfaktorDto)
	{
		when(belastungsfaktorDtoMapper.mappe(belastungsfaktor)).thenReturn(belastungsfaktorDto);
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
		var erwartet = "Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasBelastungsfaktorRepositoryGibtNullZurueck(id);

		var exception = assertThrows(BelastungsfaktorNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Belastungsfaktor zur ID ermitteln")
	void test03() throws BelastungsfaktorNichtGefundenException
	{
		var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN;
		var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN;
		var id = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID.getId().toString();
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(id, Optional.of(belastungsfaktor));
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(belastungsfaktor, erwartet);

		var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(id));
		verify(belastungsfaktorDtoMapper).mappe(belastungsfaktor);
	}

	@Test
	@DisplayName("einen Belastungsfaktor speichern")
	void test04()
	{
		var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN;
		var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
		angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(belastungsfaktor);
		angenommenDerBelastungsfaktorDtoMapperMapptZuBelastungsfaktorDto(belastungsfaktor, erwartet);

		var ergebnis = sut.speichereBelastungsfaktor(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorDtoMapper).mappe(belastungsfaktor);
	}
}
