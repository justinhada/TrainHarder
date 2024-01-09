package de.justinharder.old.domain.services.authentifizierung;

import de.justinharder.old.domain.model.Authentifizierung;
import de.justinharder.old.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.old.domain.model.exceptions.LoginException;
import de.justinharder.old.domain.model.exceptions.PasswortUnsicherException;
import de.justinharder.old.domain.repository.AuthentifizierungRepository;
import de.justinharder.old.domain.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.old.domain.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.old.domain.services.mail.Mail;
import de.justinharder.old.domain.services.mail.MailAdresse;
import de.justinharder.old.domain.services.mail.MailServer;
import de.justinharder.old.domain.services.mapper.AuthentifizierungDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.UUID;

import static de.justinharder.old.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("LoginService sollte")
class LoginServiceTest
{
	private LoginService sut;

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

		sut = new LoginService(
			authentifizierungRepository,
			authentifizierungDtoMapper,
			passwortHasher,
			passwortCheck,
			mailServer);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		var resetUuid = UUID.randomUUID();
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.login(null, "passwort")),
			() -> assertThrows(NullPointerException.class, () -> sut.login("benutzername", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.resetPassword(null, "passwort")),
			() -> assertThrows(NullPointerException.class, () -> sut.resetPassword(resetUuid, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.sendeResetMail("mail", null)),
			() -> assertThrows(NullPointerException.class, () -> sut.sendeResetMail(null, resetUuid)));
	}

	@Test
	@DisplayName("LoginException werfen, wenn der Benutzername nicht existiert")
	void test02()
	{
		var benutzername = AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		var passwort = "Justinharder#98";
		when(authentifizierungRepository.findeMitBenutzername(benutzername)).thenReturn(Optional.empty());

		assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));
		verify(authentifizierungRepository).findeMitBenutzername(benutzername);
	}

	@Test
	@DisplayName("LoginException werfen, wenn das Passwort falsch ist")
	void test03() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN;
		var benutzername = authentifizierung.getBenutzername();
		var passwort = "Justinharder#98";
		when(authentifizierungRepository.findeMitBenutzername(benutzername)).thenReturn(Optional.of(authentifizierung));
		when(passwortHasher.check(authentifizierung.getPasswort(), passwort)).thenReturn(false);

		assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));
		verify(authentifizierungRepository).findeMitBenutzername(benutzername);
		verify(passwortHasher).check(authentifizierung.getPasswort(), passwort);
	}

	@Test
	@DisplayName("einen Benutzer einloggen")
	void test04() throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var erwartet = AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN;
		var benutzername = AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		var passwort = "Justinharder#98";
		when(authentifizierungRepository.findeMitBenutzername(benutzername)).thenReturn(Optional.of(authentifizierung));
		when(passwortHasher.check(authentifizierung.getPasswort(), passwort)).thenReturn(true);
		when(authentifizierungDtoMapper.mappe(authentifizierung)).thenReturn(erwartet);

		var ergebnis = sut.login(benutzername, passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).findeMitBenutzername(benutzername);
		verify(passwortHasher).check(authentifizierung.getPasswort(), passwort);
		verify(authentifizierungDtoMapper).mappe(authentifizierung);
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn die Mail nicht existiert")
	void test05()
	{
		var mail = AUTHENTIFIZIERUNG_JUSTIN.getMail();
		var resetUuid = UUID.randomUUID();
		when(authentifizierungRepository.findeMitMail(mail)).thenReturn(Optional.empty());

		assertThrows(AuthentifizierungException.class, () -> sut.sendeResetMail(mail, resetUuid));
		verify(authentifizierungRepository).findeMitMail(mail);
	}

	@Test
	@DisplayName("die Reset-Mail senden")
	void test06() throws AuthentifizierungException
	{
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN;
		var mail = authentifizierung.getMail();
		when(authentifizierungRepository.findeMitMail(mail)).thenReturn(Optional.of(authentifizierung));

		var resetUuid = UUID.randomUUID();
		sut.sendeResetMail(mail, resetUuid);

		assertThat(authentifizierung.getResetUuid()).isEqualTo(resetUuid);
		verify(authentifizierungRepository).findeMitMail(mail);
		verify(authentifizierungRepository).speichere(authentifizierung);
		verify(mailServer).sende(new Mail(
			new MailAdresse("trainharder2021@gmail.com", "TrainHarder-Team"),
			"Anfrage der Passwort-Zurücksetzung",
			"Hallo " + authentifizierung.getBenutzername() + ",\n"
				+ "wir haben deine Anfrage der Passwort-Zurücksetzung erhalten.\n"
				+ "Über folgenden Link kannst du dein Passwort zurücksetzen: \n"
				+ "\thttps://www.trainharder.de/login/reset/" + resetUuid.toString() + "\n\n"
				+ "Mit den besten Grüßen!\n"
				+ "das TrainHarder-Team")
			.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail())));
	}

	@Test
	@DisplayName("PasswortUnsicherException werfen, wenn das Passwort unsicher ist")
	void test07()
	{
		var passwort = "UnsicheresPasswort";
		var resetUuid = UUID.randomUUID();
		when(passwortCheck.isUnsicher(passwort)).thenReturn(true);

		assertThrows(PasswortUnsicherException.class, () -> sut.resetPassword(resetUuid, passwort));
	}

	@Test
	@DisplayName("AuthentifizierungException werfen, wenn die ResetUUID nicht existiert")
	void test08()
	{
		var resetUuid = UUID.randomUUID();
		var passwort = "SicheresPasswort";
		when(passwortCheck.isUnsicher(passwort)).thenReturn(false);
		when(authentifizierungRepository.findeMitResetUuid(resetUuid)).thenReturn(Optional.empty());

		assertThrows(AuthentifizierungException.class, () -> sut.resetPassword(resetUuid, passwort));
	}

	@Test
	@DisplayName("das Passwort zurücksetzen")
	void test09()
		throws PasswortUnsicherException, AuthentifizierungException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var authentifizierung = new Authentifizierung(
			AUTHENTIFIZIERUNG_JUSTIN.getId(),
			AUTHENTIFIZIERUNG_JUSTIN.getMail(),
			AUTHENTIFIZIERUNG_JUSTIN.getBenutzername(),
			AUTHENTIFIZIERUNG_JUSTIN.getPasswort());
		var resetUuid = UUID.randomUUID();
		var passwort = "Justinharder#98";
		when(passwortCheck.isUnsicher(passwort)).thenReturn(false);
		when(authentifizierungRepository.findeMitResetUuid(resetUuid)).thenReturn(Optional.of(authentifizierung));
		when(passwortHasher.hash(passwort, authentifizierung.getPasswort().getSalt())).thenReturn(
			PASSWORT.getPasswortHash());

		sut.resetPassword(resetUuid, passwort);

		assertThat(authentifizierung.getPasswort()).isEqualTo(PASSWORT);
		verify(passwortCheck).isUnsicher(passwort);
		verify(authentifizierungRepository).findeMitResetUuid(resetUuid);
		verify(passwortHasher).hash(passwort, authentifizierung.getPasswort().getSalt());
		verify(authentifizierungRepository).speichere(authentifizierung);
	}
}
