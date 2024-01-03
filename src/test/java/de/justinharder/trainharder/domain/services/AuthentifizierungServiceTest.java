package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.trainharder.domain.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.domain.services.dto.AuthentifizierungDto;
import de.justinharder.trainharder.domain.services.mapper.AuthentifizierungDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AuthentifizierungService sollte")
class AuthentifizierungServiceTest
{
	private AuthentifizierungService sut;

	private AuthentifizierungRepository authentifizierungRepository;
	private AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@BeforeEach
	void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);

		sut = new AuthentifizierungService(authentifizierungRepository, authentifizierungDtoMapper);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(String id,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.finde(new ID(id))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(String id)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.empty());
	}

	private void angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(Authentifizierung authentifizierung,
		AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.mappe(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(String benutzerId,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.findeMitBenutzer(new ID(benutzerId))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(String benutzerId)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId, Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(String benutzername,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.findeMitBenutzername(benutzername)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(
		String benutzername)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.empty());
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitBenutzername(null)));
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn keine Authentifizierung zur ID ermittelt werden kann")
	void test02()
	{
		var id = new ID().getWert().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(id);

		assertThrows(AuthentifizierungException.class, () -> sut.finde(id));
	}

	@Test
	@DisplayName("eine Authentifizierung zur ID ermitteln")
	void test03() throws AuthentifizierungException
	{
		var erwartet = AUTHENTIFIZIERUNG_DTO_EDUARD;
		var id = new ID().getWert().toString();
		var authentifizierung = AUTHENTIFIZIERUNG_EDUARD;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		assertThat(sut.finde(id)).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn BenutzerID zu keiner Authentifizierung gehört")
	void test04()
	{
		var benutzerId = new ID().getWert().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(benutzerId);

		assertThrows(AuthentifizierungException.class, () -> sut.findeMitBenutzer(benutzerId));
	}

	@Test
	@DisplayName("Authentifizierung zu BenutzerID ermitteln")
	void test05() throws AuthentifizierungException
	{
		var erwartet = AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerId = new ID().getWert().toString();
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		assertThat(sut.findeMitBenutzer(benutzerId)).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn keine Authentifizierung zum Benutzernamen ermittelt werden kann")
	void test06()
	{
		var benutzername = "nichtbekannt";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(benutzername);

		assertThrows(AuthentifizierungException.class, () -> sut.findeMitBenutzername(benutzername));
	}

	@Test
	@DisplayName("Authentifizierung zum Benutzernamen ermitteln")
	void test07() throws AuthentifizierungException
	{
		var erwartet = AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzername = "harder";
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		assertThat(sut.findeMitBenutzername(benutzername)).isEqualTo(erwartet);
	}
}
