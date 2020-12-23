package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
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

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(String id,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(authentifizierung);
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
		when(authentifizierungRepository.ermittleZuBenutzer(new Primaerschluessel(benutzerId)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(String benutzerId)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId, Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(String benutzername,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierung);
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
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzername(null)));
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn keine Authentifizierung zur ID ermittelt werden kann")
	void test02()
	{
		var id = new Primaerschluessel().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(id);

		var exception = assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("eine Authentifizierung zur ID ermitteln")
	void test03() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD;
		var id = Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID.getId().toString();
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_EDUARD;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn BenutzerID zu keiner Authentifizierung gehört")
	void test04()
	{
		var benutzerId = new Primaerschluessel().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(benutzerId);

		var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzer(benutzerId));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der BenutzerID \"" + benutzerId + "\" existiert nicht!");
	}

	@Test
	@DisplayName("Authentifizierung zu BenutzerID ermitteln")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		var ergebnis = sut.ermittleZuBenutzer(benutzerId);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn keine Authentifizierung zum Benutzernamen ermittelt werden kann")
	void test06()
	{
		var benutzername = "nichtbekannt";
		var erwartet = "Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(benutzername);

		var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzername(benutzername));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Authentifizierung zum Benutzernamen ermitteln")
	void test07() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzername = "harder";
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		var ergebnis = sut.ermittleZuBenutzername(benutzername);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
