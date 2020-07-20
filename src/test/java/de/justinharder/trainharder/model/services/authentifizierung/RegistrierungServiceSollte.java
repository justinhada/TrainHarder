package de.justinharder.trainharder.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class RegistrierungServiceSollte
{
	private RegistrierungService sut;

	private AuthentifizierungRepository authentifizierungRepository;
	private AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private PasswortCheck passwortCheck;

	@BeforeEach
	public void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);
		passwortCheck = mock(PasswortCheck.class);

		sut = new RegistrierungService(authentifizierungRepository, authentifizierungDtoMapper, passwortCheck);
	}

	private void angenommenDieMailIstVergeben(final String mail, final boolean vergeben)
	{
		when(authentifizierungRepository.checkMail(mail)).thenReturn(vergeben);
	}

	private void angenommenDerBenutzernameIstVergeben(final String benutzername, final boolean vergeben)
	{
		when(authentifizierungRepository.checkBenutzername(benutzername)).thenReturn(vergeben);
	}

	private void angenommenDasPasswortIstUnsicher(final String passwort, final boolean unsicher)
	{
		when(passwortCheck.isUnsicher(passwort)).thenReturn(unsicher);
	}

	private void angenommenDasAuthentifizierungRepositorySpeichertAuthentifizierung(
		final Authentifizierung authentifizierung)
	{
		when(authentifizierungRepository.speichereAuthentifizierung(any(Authentifizierung.class)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
		final Authentifizierung authentifizierung, final AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.konvertiere(authentifizierung)).thenReturn(authentifizierungDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Registrierung null ist")
	public void test01()
	{
		final var erwartet = "Zum Beitreten wird eine gültige Registrierung benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.registriere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("MailVergebenException werfen, wenn die Mail vergeben ist")
	public void test02()
	{
		final var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		final var erwartet = "Die Mail \"" + registrierung.getMail() + "\" ist bereits vergeben!";
		angenommenDieMailIstVergeben(registrierung.getMail(), true);

		final var exception = assertThrows(MailVergebenException.class, () -> sut.registriere(registrierung));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).checkMail(registrierung.getMail());
	}

	@Test
	@DisplayName("BenutzernameVergebenException werfen, wenn der Benutzername vergeben ist")
	public void test03()
	{
		final var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		final var erwartet = "Der Benutzername \"" + registrierung.getBenutzername() + "\" ist bereits vergeben!";
		angenommenDieMailIstVergeben(registrierung.getMail(), false);
		angenommenDerBenutzernameIstVergeben(registrierung.getBenutzername(), true);

		final var exception = assertThrows(BenutzernameVergebenException.class, () -> sut.registriere(registrierung));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).checkMail(registrierung.getMail());
		verify(authentifizierungRepository).checkBenutzername(registrierung.getBenutzername());
	}

	@Test
	@DisplayName("PasswortUnsicherException werfen, wenn das Passwort unsicher ist")
	public void test04()
	{
		final var erwartet = "Das Passwort ist unsicher!";
		final var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		angenommenDieMailIstVergeben(registrierung.getMail(), false);
		angenommenDerBenutzernameIstVergeben(registrierung.getBenutzername(), false);
		angenommenDasPasswortIstUnsicher(registrierung.getPasswort(), true);

		final var exception = assertThrows(PasswortUnsicherException.class, () -> sut.registriere(registrierung));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).checkMail(registrierung.getMail());
		verify(authentifizierungRepository).checkBenutzername(registrierung.getBenutzername());
		verify(passwortCheck).isUnsicher(registrierung.getPasswort());
	}

	@Test
	@DisplayName("einen Benutzer registrieren")
	public void test05() throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDieMailIstVergeben(registrierung.getMail(), false);
		angenommenDerBenutzernameIstVergeben(registrierung.getBenutzername(), false);
		angenommenDasPasswortIstUnsicher(registrierung.getPasswort(), false);
		angenommenDasAuthentifizierungRepositorySpeichertAuthentifizierung(authentifizierung);
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.registriere(registrierung);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).checkMail(registrierung.getMail());
		verify(authentifizierungRepository).checkBenutzername(registrierung.getBenutzername());
		verify(passwortCheck).isUnsicher(registrierung.getPasswort());
		verify(authentifizierungDtoMapper).konvertiere(authentifizierung);
	}
}
