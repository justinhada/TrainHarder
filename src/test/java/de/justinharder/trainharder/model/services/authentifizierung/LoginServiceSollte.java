package de.justinharder.trainharder.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class LoginServiceSollte
{
	private LoginService sut;

	private AuthentifizierungRepository authentifizierungRepository;
	private AuthentifizierungDtoMapper authentifizierungDtoMapper;
	private MailServer mailServer;
	private PasswortCheck passwortCheck;

	@BeforeEach
	public void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		authentifizierungDtoMapper = mock(AuthentifizierungDtoMapper.class);
		mailServer = mock(MailServer.class);
		passwortCheck = mock(PasswortCheck.class);

		sut = new LoginService(authentifizierungRepository, authentifizierungDtoMapper, mailServer, passwortCheck);
	}

	private void angenommenDasAuthentifizierungRepositoryLoggtEin(final String benutzername, final String passwort,
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.login(benutzername, passwort)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryLoggtNichtEin(final String benutzername, final String passwort)
	{
		angenommenDasAuthentifizierungRepositoryLoggtEin(benutzername, passwort, Optional.empty());
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

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(final String resetUuid,
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuResetUuid(resetUuid)).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuResetUuid(
		final String resetUuid)
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(resetUuid, Optional.empty());
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
		angenommenDasAuthentifizierungRepositoryLoggtNichtEin(benutzername, passwort);

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
		angenommenDasAuthentifizierungRepositoryLoggtEin(benutzername, passwort,
			Optional.of(authentifizierung));
		angenommenDerAuthentifizierungDtoMapperKonvertiertZuAuthentifizierungDto(authentifizierung, erwartet);

		final var ergebnis = sut.login(benutzername, passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).login(benutzername, passwort);
		verify(authentifizierungDtoMapper).konvertiere(authentifizierung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Mail null ist")
	public void test05()
	{
		final var erwartet = "Zum Senden der Reset-Mail wird eine gültige Mail benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.sendeResetMail(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ResetUUID null ist")
	public void test06()
	{
		final var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		final var erwartet = "Zum Senden der Reset-Mail wird eine gültige ResetUUID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.sendeResetMail(mail, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die Mail nicht existiert")
	public void test07()
	{
		final var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		final var erwartet = "Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierungZuMail(mail);

		final var resetUuid = UUID.randomUUID().toString();
		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.sendeResetMail(mail, resetUuid));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuMail(mail);
	}

	@Test
	@DisplayName("die Reset-Mail senden")
	public void test08() throws AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		final var mail = authentifizierung.getMail();
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuMail(mail, Optional.of(authentifizierung));

		final var resetUuid = UUID.randomUUID();
		sut.sendeResetMail(mail, resetUuid.toString());

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
	public void test09()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.resetPassword(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Passwort null ist")
	public void test10()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird ein gültiges Passwort benötigt!";

		final var resetUuid = UUID.randomUUID().toString();
		final var exception = assertThrows(NullPointerException.class, () -> sut.resetPassword(resetUuid, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("PasswortUnsicherException werfen, wenn das Passwort unsicher ist")
	public void test11()
	{
		final var erwartet = "Das Passwort ist unsicher!";
		final var passwort = "UnsicheresPasswort";
		angenommenDasPasswortIstUnsicher(passwort, true);

		final var resetUuid = UUID.randomUUID().toString();
		final var exception =
			assertThrows(PasswortUnsicherException.class, () -> sut.resetPassword(resetUuid, passwort));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die ResetUUID nicht existiert")
	public void test12()
	{
		final var resetUuid = UUID.randomUUID().toString();
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
	public void test13() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException
	{
		final var authentifizierung = new Authentifizierung(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPrimaerschluessel(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername(),
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getPasswort());
		final var resetUuid = UUID.randomUUID().toString();
		final var passwort = "SicheresPasswort";
		angenommenDasPasswortIstUnsicher(passwort, false);
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuResetUuid(resetUuid,
			Optional.of(authentifizierung));

		sut.resetPassword(resetUuid, passwort);

		assertThat(authentifizierung.getPasswort()).isEqualTo(passwort);
		verify(passwortCheck).isUnsicher(passwort);
		verify(authentifizierungRepository).ermittleZuResetUuid(resetUuid);
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
	}
}
