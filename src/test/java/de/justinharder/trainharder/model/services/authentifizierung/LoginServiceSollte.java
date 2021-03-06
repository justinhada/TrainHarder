package de.justinharder.trainharder.model.services.authentifizierung;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.model.services.mail.Mail;
import de.justinharder.trainharder.model.services.mail.MailAdresse;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginServiceSollte
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

		sut = new LoginService(authentifizierungRepository, authentifizierungDtoMapper, passwortHasher, passwortCheck, mailServer);
	}

	private void angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(Authentifizierung authentifizierung, AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.mappe(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(String mail, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuMail(mail)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuMail(String mail)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(mail, Optional.empty());
	}

	private void angenommenDasPasswortIstUnsicher(String passwort, boolean unsicher)
	{
		when(passwortCheck.isUnsicher(passwort)).thenReturn(unsicher);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(UUID resetUuid, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuResetUuid(resetUuid)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuResetUuid(UUID resetUuid)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(resetUuid, Optional.empty());
	}

	private void angenommenDerPasswortHasherChecktPasswort(Passwort aktuellesPasswort, String passwort, boolean check) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		when(passwortHasher.check(aktuellesPasswort, passwort)).thenReturn(check);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(String benutzername, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(String benutzername)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername, Optional.empty());
	}

	private void angenommenDerPasswortHasherHashtPasswort(String passwort, String salt, String hash) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		when(passwortHasher.hash(passwort, salt)).thenReturn(hash);
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
		var benutzername = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		var passwort = "Justinharder#98";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(benutzername);

		assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));
		verify(authentifizierungRepository).ermittleZuBenutzername(benutzername);
	}

	@Test
	@DisplayName("LoginException werfen, wenn das Passwort falsch ist")
	void test03() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		var benutzername = authentifizierung.getBenutzername();
		var passwort = "Justinharder#98";
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername, Optional.of(authentifizierung));
		angenommenDerPasswortHasherChecktPasswort(authentifizierung.getPasswort(), passwort, false);

		assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));
		verify(authentifizierungRepository).ermittleZuBenutzername(benutzername);
		verify(passwortHasher).check(authentifizierung.getPasswort(), passwort);
	}

	@Test
	@DisplayName("einen Benutzer einloggen")
	void test04() throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		var benutzername = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		var passwort = "Justinharder#98";
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername, Optional.of(authentifizierung));
		angenommenDerPasswortHasherChecktPasswort(authentifizierung.getPasswort(), passwort, true);
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		var ergebnis = sut.login(benutzername, passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuBenutzername(benutzername);
		verify(passwortHasher).check(authentifizierung.getPasswort(), passwort);
		verify(authentifizierungDtoMapper).mappe(authentifizierung);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die Mail nicht existiert")
	void test05()
	{
		var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		var resetUuid = UUID.randomUUID();
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuMail(mail);

		assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.sendeResetMail(mail, resetUuid));
		verify(authentifizierungRepository).ermittleZuMail(mail);
	}

	@Test
	@DisplayName("die Reset-Mail senden")
	void test06() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		var mail = authentifizierung.getMail();
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(mail, Optional.of(authentifizierung));

		var resetUuid = UUID.randomUUID();
		sut.sendeResetMail(mail, resetUuid);

		assertThat(authentifizierung.getResetUuid()).isEqualTo(resetUuid);
		verify(authentifizierungRepository).ermittleZuMail(mail);
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
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
		angenommenDasPasswortIstUnsicher(passwort, true);

		assertThrows(PasswortUnsicherException.class, () -> sut.resetPassword(resetUuid, passwort));
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die ResetUUID nicht existiert")
	void test08()
	{
		var resetUuid = UUID.randomUUID();
		var passwort = "SicheresPasswort";
		angenommenDasPasswortIstUnsicher(passwort, false);
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuResetUuid(resetUuid);

		assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.resetPassword(resetUuid, passwort));
	}

	@Test
	@DisplayName("das Passwort zurücksetzen")
	void test09() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var authentifizierung = new Authentifizierung(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPrimaerschluessel(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPasswort());
		var resetUuid = UUID.randomUUID();
		var passwort = "Justinharder#98";
		angenommenDasPasswortIstUnsicher(passwort, false);
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(resetUuid, Optional.of(authentifizierung));
		angenommenDerPasswortHasherHashtPasswort(passwort, authentifizierung.getPasswort().getSalt(), Testdaten.PASSWORT.getPasswortHash());

		sut.resetPassword(resetUuid, passwort);

		assertThat(authentifizierung.getPasswort()).isEqualTo(Testdaten.PASSWORT);
		verify(passwortCheck).isUnsicher(passwort);
		verify(authentifizierungRepository).ermittleZuResetUuid(resetUuid);
		verify(passwortHasher).hash(passwort, authentifizierung.getPasswort().getSalt());
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
	}
}