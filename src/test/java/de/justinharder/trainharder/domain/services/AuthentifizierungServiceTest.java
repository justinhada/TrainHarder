package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.domain.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.domain.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.domain.services.dto.AuthentifizierungDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthentifizierungServiceSollte
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

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(String id, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(new ID(id))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(String id)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.empty());
	}

	private void angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(Authentifizierung authentifizierung, AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.mappe(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(String benutzerId, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzer(new ID(benutzerId))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(String benutzerId)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId, Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(String benutzername, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(String benutzername)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername, Optional.empty());
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzername(null)));
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn keine Authentifizierung zur ID ermittelt werden kann")
	void test02()
	{
		var id = new ID().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(id);

		assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuId(id));
	}

	@Test
	@DisplayName("eine Authentifizierung zur ID ermitteln")
	void test03() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD;
		var id = new ID().getId().toString();
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_EDUARD;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		assertThat(sut.ermittleZuId(id)).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn BenutzerID zu keiner Authentifizierung gehört")
	void test04()
	{
		var benutzerId = new ID().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(benutzerId);

		assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzer(benutzerId));
	}

	@Test
	@DisplayName("Authentifizierung zu BenutzerID ermitteln")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerId = new ID().getId().toString();
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId, Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		assertThat(sut.ermittleZuBenutzer(benutzerId)).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn keine Authentifizierung zum Benutzernamen ermittelt werden kann")
	void test06()
	{
		var benutzername = "nichtbekannt";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(benutzername);

		assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzername(benutzername));
	}

	@Test
	@DisplayName("Authentifizierung zum Benutzernamen ermitteln")
	void test07() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzername = "harder";
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername, Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		assertThat(sut.ermittleZuBenutzername(benutzername)).isEqualTo(erwartet);
	}
}
