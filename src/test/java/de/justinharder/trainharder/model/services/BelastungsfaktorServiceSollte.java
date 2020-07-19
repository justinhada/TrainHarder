package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.setup.Testdaten;

public class BelastungsfaktorServiceSollte
{
	private BelastungsfaktorService sut;
	private BelastungsfaktorRepository belastungsfaktorRepository;

	@BeforeEach
	public void setup()
	{
		belastungsfaktorRepository = mock(BelastungsfaktorRepository.class);
		sut = new BelastungsfaktorService(belastungsfaktorRepository);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(
		final Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtNullZurueck()
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(
		final Belastungsfaktor belastungsfaktor)
	{
		when(belastungsfaktorRepository.speichereBelastungsfaktor(any(Belastungsfaktor.class)))
			.thenReturn(belastungsfaktor);
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn ID zu keinem Belastungsfaktor gehört")
	public void test01()
	{
		angenommenDasBelastungsfaktorRepositoryGibtNullZurueck();

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(BelastungsfaktorNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Belastungsfaktor zur ID ermitteln")
	public void test02() throws BelastungsfaktorNichtGefundenException
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_KONVENTIONELLES_KREUZHEBEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN;
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(Optional.of(belastungsfaktor));

		final var id = new Primaerschluessel().getId().toString();
		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Belastungsfaktor erstellen")
	public void test03()
	{
		final var erwartet = Testdaten.BELASTUNGSFAKTOR_DTO_WETTKAMPFBANKDRUECKEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN;
		angenommenDasBelastungsfaktorRepositorySpeichertBelastungsfaktor(belastungsfaktor);

		final var ergebnis = sut.speichereBelastungsfaktor(erwartet);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
