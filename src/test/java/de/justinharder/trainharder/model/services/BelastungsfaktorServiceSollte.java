package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.services.mapper.BelastungsfaktorDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;

public class BelastungsfaktorServiceSollte
{
	private BelastungsfaktorService sut;

	private BelastungsfaktorRepository belastungsfaktorRepository;
	private BelastungsfaktorDtoMapper belastungsfaktorDtoMapper;

	@BeforeEach
	public void setup()
	{
		belastungsfaktorRepository = mock(BelastungsfaktorRepository.class);
		belastungsfaktorDtoMapper = mock(BelastungsfaktorDtoMapper.class);

		sut = new BelastungsfaktorService(belastungsfaktorRepository, belastungsfaktorDtoMapper);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(
		final String id, final Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtNullZurueck(final String id)
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(id, Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(
		final Belastungsfaktor belastungsfaktor)
	{
		when(belastungsfaktorRepository.speichereBelastungsfaktor(any(Belastungsfaktor.class)))
			.thenReturn(belastungsfaktor);
	}

	private void angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(
		final Belastungsfaktor belastungsfaktor, final BelastungsfaktorDto belastungsfaktorDto)
	{
		when(belastungsfaktorDtoMapper.konvertiere(belastungsfaktor)).thenReturn(belastungsfaktorDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ID null ist")
	public void test01()
	{
		final var erwartet = "Ermittlung des Belastungsfaktors benötigt eine gültige BelastungsfaktorID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn ID zu keinem Belastungsfaktor gehört")
	public void test02()
	{
		final var id = new Primaerschluessel().getId().toString();
		final var erwartet = "Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasBelastungsfaktorRepositoryGibtNullZurueck(id);

		final var exception = assertThrows(BelastungsfaktorNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("einen Belastungsfaktor zur ID ermitteln")
	public void test03() throws BelastungsfaktorNichtGefundenException
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN;
		final var id = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN_ID.getId().toString();
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(id, Optional.of(belastungsfaktor));
		angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(belastungsfaktor, erwartet);

		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(id));
		verify(belastungsfaktorDtoMapper).konvertiere(belastungsfaktor);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das BelastungsfaktorDto null ist")
	public void test04()
	{
		final var erwartet = "Zum Speichern wird ein gueltiges BelastungsfaktorDto benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.speichereBelastungsfaktor(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Belastungsfaktor speichern")
	public void test05()
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
		angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(belastungsfaktor);
		angenommenDerBelastungsfaktorDtoMapperKonvertiertZuBelastungsfaktorDto(belastungsfaktor, erwartet);

		final var ergebnis = sut.speichereBelastungsfaktor(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorDtoMapper).konvertiere(belastungsfaktor);
	}
}
