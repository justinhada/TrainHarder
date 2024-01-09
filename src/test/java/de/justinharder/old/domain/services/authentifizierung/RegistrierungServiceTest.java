package de.justinharder.old.domain.services.authentifizierung;

import de.justinharder.old.domain.model.Authentifizierung;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.old.domain.model.exceptions.BenutzernameVergebenException;
import de.justinharder.old.domain.model.exceptions.MailVergebenException;
import de.justinharder.old.domain.model.exceptions.PasswortUnsicherException;
import de.justinharder.old.domain.repository.AuthentifizierungRepository;
import de.justinharder.old.domain.services.authentifizierung.RegistrierungService;
import de.justinharder.old.domain.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.old.domain.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.old.domain.services.dto.Registrierung;
import de.justinharder.old.domain.services.mail.Mail;
import de.justinharder.old.domain.services.mail.MailServer;
import de.justinharder.old.domain.services.mapper.AuthentifizierungDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static de.justinharder.old.setup.Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
import static de.justinharder.old.setup.Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("RegistrierungService sollte")
class RegistrierungServiceTest
{
	private RegistrierungService sut;

	private AuthentifizierungRepository authentifizierungRepository;
	private AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private PasswortHasher passwortHasher;
	private PasswortCheck passwortCheck;
	private MailServer mailServer;

	@BeforeEach
	void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);
		passwortHasher = mock(PasswortHasher.class);
		passwortCheck = mock(PasswortCheck.class);
		mailServer = mock(MailServer.class);

		sut = new RegistrierungService(authentifizierungRepository, authentifizierungDtoMapper, passwortHasher,
			passwortCheck, mailServer);
	}

	@Test
	@DisplayName("null valdieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.registriere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktiviere(null)));
	}

	@Test
	@DisplayName("MailVergebenException werfen, wenn die Mail vergeben ist")
	void test02()
	{
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		when(authentifizierungRepository.findeMitMail(registrierung.getMail())).thenReturn(
			Optional.of(AUTHENTIFIZIERUNG_JUSTIN));

		assertThrows(MailVergebenException.class, () -> sut.registriere(registrierung));
		verify(authentifizierungRepository).findeMitMail(registrierung.getMail());
	}

	@Test
	@DisplayName("BenutzernameVergebenException werfen, wenn der Benutzername vergeben ist")
	void test03()
	{
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		when(authentifizierungRepository.findeMitMail(registrierung.getMail())).thenReturn(Optional.empty());
		when(authentifizierungRepository.findeMitBenutzername(registrierung.getBenutzername())).thenReturn(
			Optional.of(AUTHENTIFIZIERUNG_JUSTIN));

		assertThrows(BenutzernameVergebenException.class, () -> sut.registriere(registrierung));
		verify(authentifizierungRepository).findeMitMail(registrierung.getMail());
		verify(authentifizierungRepository).findeMitBenutzername(registrierung.getBenutzername());
	}

	@Test
	@DisplayName("PasswortUnsicherException werfen, wenn das Passwort unsicher ist")
	void test04()
	{
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		when(authentifizierungRepository.findeMitMail(registrierung.getMail())).thenReturn(Optional.empty());
		when(authentifizierungRepository.findeMitBenutzername(registrierung.getBenutzername())).thenReturn(
			Optional.empty());
		when(passwortCheck.isUnsicher(registrierung.getPasswort())).thenReturn(true);

		assertThrows(PasswortUnsicherException.class, () -> sut.registriere(registrierung));
		verify(authentifizierungRepository).findeMitMail(registrierung.getMail());
		verify(authentifizierungRepository).findeMitBenutzername(registrierung.getBenutzername());
		verify(passwortCheck).isUnsicher(registrierung.getPasswort());
	}

	@Test
	@DisplayName("einen Benutzer registrieren")
	void test05()
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		var salt = "AAAAAAAAAAAAAAAAAAAAAA==";
		when(authentifizierungRepository.findeMitMail(registrierung.getMail())).thenReturn(Optional.empty());
		when(authentifizierungRepository.findeMitBenutzername(registrierung.getBenutzername())).thenReturn(
			Optional.empty());
		when(passwortCheck.isUnsicher(registrierung.getPasswort())).thenReturn(false);
		byte[] bytes = new byte[16];
		when(passwortHasher.generiereSalt(bytes)).thenReturn(salt);
		when(passwortHasher.hash(registrierung.getPasswort(), salt)).thenReturn("GBy6erWCKE3CqEuWqYOk/w==");
		when(authentifizierungDtoMapper.mappe(any(Authentifizierung.class))).thenReturn(AUTHENTIFIZIERUNG_DTO_JUSTIN);

		var ergebnis = sut.registriere(registrierung);

		assertThat(ergebnis).isEqualTo(AUTHENTIFIZIERUNG_DTO_JUSTIN);
		verify(authentifizierungRepository).findeMitMail(registrierung.getMail());
		verify(authentifizierungRepository).findeMitBenutzername(registrierung.getBenutzername());
		verify(passwortCheck).isUnsicher(registrierung.getPasswort());
		verify(authentifizierungRepository).speichere(any(Authentifizierung.class));
		verify(mailServer).sende(any(Mail.class));
		verify(authentifizierungDtoMapper).mappe(any(Authentifizierung.class));
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn die AuthentifizierungID nicht existiert")
	void test06()
	{
		var authentifizierungId = new ID().getWert().toString();

		assertThrows(AuthentifizierungException.class, () -> sut.aktiviere(authentifizierungId));
	}

	@Test
	@DisplayName("eine Authentifizierung aktivieren")
	void test07() throws AuthentifizierungException
	{
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN;
		var authentifizierungId = authentifizierung.getId().getWert().toString();
		when(authentifizierungRepository.finde(new ID(authentifizierungId))).thenReturn(Optional.of(authentifizierung));

		sut.aktiviere(authentifizierungId);

		verify(authentifizierungRepository).finde(authentifizierung.getId());
		verify(authentifizierungRepository).speichere(authentifizierung);
	}
}
