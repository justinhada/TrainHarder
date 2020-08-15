package de.justinharder.trainharder.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Passwort;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class LoginServiceSollte
{
	private LoginService sut;

	private AuthentifizierungRepository authentifizierungRepository;
	private AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private PasswortHasher passwortHasher;
	private PasswortCheck passwortCheck;
	private MailServer mailServer;

	@BeforeEach
	public void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);
		passwortHasher = mock(PasswortHasher.class);
		passwortCheck = mock(PasswortCheck.class);
		mailServer = mock(MailServer.class);

		sut = new LoginService(authentifizierungRepository, authentifizierungDtoMapper, passwortHasher, passwortCheck,
			mailServer);
	}

	private void angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(
		final Authentifizierung authentifizierung, final AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.konvertiere(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(final String mail,
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuMail(mail)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuMail(final String mail)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(mail, Optional.empty());
	}

	private void angenommenDasPasswortIstUnsicher(final String passwort, final boolean unsicher)
	{
		when(passwortCheck.isUnsicher(passwort)).thenReturn(unsicher);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(final UUID resetUuid,
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuResetUuid(resetUuid)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuResetUuid(
		final UUID resetUuid)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(resetUuid, Optional.empty());
	}

	private void angenommenDerPasswortHasherChecktPasswort(final Passwort aktuellesPasswort, final String passwort,
		final boolean check) throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		when(passwortHasher.check(aktuellesPasswort, passwort)).thenReturn(check);
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

	private void angenommenDerPasswortHasherHashtPasswort(final String passwort, final byte[] salt, final byte[] hash)
		throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		when(passwortHasher.hash(passwort, salt)).thenReturn(hash);
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
	@DisplayName("LoginException werfen, wenn der Benutzername nicht existiert")
	public void test03()
	{
		final var erwartet = "Der Benutzername oder das Passwort ist leider falsch!";
		final var benutzername = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		final var passwort = "Justinharder#98";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuBenutzername(benutzername);

		final var exception = assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuBenutzername(benutzername);
	}

	@Test
	@DisplayName("LoginException werfen, wenn das Passwort falsch ist")
	public void test04() throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var erwartet = "Der Benutzername oder das Passwort ist leider falsch!";
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		final var benutzername = authentifizierung.getBenutzername();
		final var passwort = "Justinharder#98";
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.of(authentifizierung));
		angenommenDerPasswortHasherChecktPasswort(authentifizierung.getPasswort(), passwort, false);

		final var exception = assertThrows(LoginException.class, () -> sut.login(benutzername, passwort));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuBenutzername(benutzername);
		verify(passwortHasher).check(authentifizierung.getPasswort(), passwort);
	}

	@Test
	@DisplayName("einen Benutzer einloggen")
	public void test05() throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		final var benutzername = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername();
		final var passwort = "Justinharder#98";
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuBenutzername(benutzername,
			Optional.of(authentifizierung));
		angenommenDerPasswortHasherChecktPasswort(authentifizierung.getPasswort(), passwort, true);
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.login(benutzername, passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuBenutzername(benutzername);
		verify(passwortHasher).check(authentifizierung.getPasswort(), passwort);
		verify(authentifizierungDtoMapper).konvertiere(authentifizierung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Mail null ist")
	public void test06()
	{
		final var erwartet = "Zum Senden der Reset-Mail wird eine gültige Mail benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.sendeResetMail(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ResetUUID null ist")
	public void test07()
	{
		final var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		final var erwartet = "Zum Senden der Reset-Mail wird eine gültige ResetUUID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.sendeResetMail(mail, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die Mail nicht existiert")
	public void test08()
	{
		final var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		final var erwartet = "Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuMail(mail);

		final var resetUuid = UUID.randomUUID();
		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.sendeResetMail(mail, resetUuid));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuMail(mail);
	}

	@Test
	@DisplayName("die Reset-Mail senden")
	public void test09() throws AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		final var mail = authentifizierung.getMail();
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(mail, Optional.of(authentifizierung));

		final var resetUuid = UUID.randomUUID();
		sut.sendeResetMail(mail, resetUuid);

		assertThat(authentifizierung.getResetUuid()).isEqualTo(resetUuid);
		verify(authentifizierungRepository).ermittleZuMail(mail);
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
		//		verify(mailServer).sendeMail(new Mail(
		//			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
		//			"Anfrage der Passwort-Zurücksetzung",
		//			"Hallo " + authentifizierung.getBenutzername() + ",\n"
		//				+ "wir haben deine Anfrage der Passwort-Zurücksetzung erhalten.\n"
		//				+ "Über folgenden Link kannst du dein Passwort zurücksetzen: \n"
		//				+ "\thttps://www.trainharder.de/login/reset?id=" + resetUuid + "\n\n"
		//				+ "Mit den besten Grüßen!\n"
		//				+ "das TrainHarder-Team")
		//					.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail())),
		//			StandardCharsets.UTF_8);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ResetUUID null ist")
	public void test10()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.resetPassword(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Passwort null ist")
	public void test11()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird ein gültiges Passwort benötigt!";

		final var resetUuid = UUID.randomUUID();
		final var exception = assertThrows(NullPointerException.class, () -> sut.resetPassword(resetUuid, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("PasswortUnsicherException werfen, wenn das Passwort unsicher ist")
	public void test12()
	{
		final var erwartet = "Das Passwort ist unsicher!";
		final var passwort = "UnsicheresPasswort";
		angenommenDasPasswortIstUnsicher(passwort, true);

		final var resetUuid = UUID.randomUUID();
		final var exception =
			assertThrows(PasswortUnsicherException.class, () -> sut.resetPassword(resetUuid, passwort));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die ResetUUID nicht existiert")
	public void test13()
	{
		final var resetUuid = UUID.randomUUID();
		final var erwartet = "Die Authentifizierung mit der ResetUUID \"" + resetUuid + "\" existiert nicht!";
		final var passwort = "SicheresPasswort";
		angenommenDasPasswortIstUnsicher(passwort, false);
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuResetUuid(resetUuid);

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.resetPassword(resetUuid, passwort));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("das Passwort zurücksetzen")
	public void test14() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException,
		InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var authentifizierung = new Authentifizierung(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPrimaerschluessel(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPasswort());
		final var resetUuid = UUID.randomUUID();
		final var passwort = "Justinharder#98";
		angenommenDasPasswortIstUnsicher(passwort, false);
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(resetUuid,
			Optional.of(authentifizierung));
		angenommenDerPasswortHasherHashtPasswort(passwort, authentifizierung.getPasswort().getSalt(),
			Testdaten.PASSWORT.getPasswortHash());

		sut.resetPassword(resetUuid, passwort);

		assertThat(authentifizierung.getPasswort()).isEqualTo(Testdaten.PASSWORT);
		verify(passwortCheck).isUnsicher(passwort);
		verify(authentifizierungRepository).ermittleZuResetUuid(resetUuid);
		verify(passwortHasher).hash(passwort, authentifizierung.getPasswort().getSalt());
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
	}
}
