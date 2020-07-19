package de.justinharder.trainharder.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class LoginServiceSollte
{
	private LoginService sut;

	private AuthentifizierungRepository authentifizierungRepository;
	private AuthentifizierungDtoMapper authentifizierungDtoMapper;

	@BeforeEach
	public void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);

		sut = new LoginService(authentifizierungRepository, authentifizierungDtoMapper);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(final String benutzername,
		final String passwort, final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.login(benutzername, passwort)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtKeineAuthentifizierungZurueck(final String benutzername,
		final String passwort)
	{
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(benutzername, passwort, Optional.empty());
	}

	private void angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
		final Authentifizierung authentifizierung, final AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.konvertiere(authentifizierung)).thenReturn(authentifizierungDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn der Benutzername null ist")
	public void test01()
	{
		final var erwartet = "Zum Login wird ein gültiger Benutzername benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.login(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Passwort null ist")
	public void test02()
	{
		final var erwartet = "Zum Login wird ein gültiges Passwort benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.login("harder", null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("LoginException werfen, wenn der Benutzername oder das Passwort falsch ist")
	public void test03()
	{
		final var erwartet = "Der Benutzername oder das Passwort ist leider falsch!";
		final var benutzername = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		final var passwort = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPasswort();
		angenommenDasAuthentifizierungRepositoryGibtKeineAuthentifizierungZurueck(benutzername, passwort);

		final var exception = assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).login(benutzername, passwort);
	}

	@Test
	@DisplayName("einen Benutzer einloggen")
	public void test04() throws LoginException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		final var benutzername = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		final var passwort = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPasswort();
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(benutzername, passwort,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.login(benutzername, passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).login(benutzername, passwort);
		verify(authentifizierungDtoMapper).konvertiere(authentifizierung);
	}
}
