package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

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

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(final String id,
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(final String id)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.empty());
	}

	private void angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
		final Authentifizierung authentifizierung, final AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.konvertiere(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(
		final String benutzerId, final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzer(new Primaerschluessel(benutzerId)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(
		final String benutzerId)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId, Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(
		final String benutzername,
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(
		final String benutzername)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.empty());
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ID null ist")
	void test01()
	{
		final var erwartet = "Ermittlung der Authentifizierung benötigt eine gültige AuthentifizierungID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn keine Authentifizierung zur ID ermittelt werden kann")
	void test02()
	{
		final var id = new Primaerschluessel().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZurId(id);

		final var exception = assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("eine Authentifizierung zur ID ermitteln")
	void test03() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_EDUARD;
		final var id = Testdaten.AUTHENTIFIZIERUNG_EDUARD_ID.getId().toString();
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_EDUARD;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZurId(id, Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BenutzerID null ist")
	void test04()
	{
		final var erwartet = "Ermittlung der Authentifizierung benötigt eine gültige BenutzerID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzer(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn BenutzerID zu keiner Authentifizierung gehört")
	void test05() throws AuthentifizierungNichtGefundenException
	{
		final var benutzerId = new Primaerschluessel().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzer(benutzerId);

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzer(benutzerId));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der BenutzerID \"" + benutzerId + "\" existiert nicht!");
	}

	@Test
	@DisplayName("Authentifizierung zu BenutzerID ermitteln")
	void test06() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzer(benutzerId,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.ermittleZuBenutzer(benutzerId);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn der Benutzername null ist")
	void test07()
	{
		final var erwartet = "Ermittlung der Authentifizierung benötigt einen gültigen Benutzernamen!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuBenutzername(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn keine Authentifizierung zum Benutzernamen ermittelt werden kann")
	void test08()
	{
		final var benutzername = "nichtbekannt";
		final var erwartet = "Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(benutzername);

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzername(benutzername));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Authentifizierung zum Benutzernamen ermitteln")
	void test09() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzername = "harder";
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.ermittleZuBenutzername(benutzername);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
