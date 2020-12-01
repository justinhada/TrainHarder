package de.justinharder.trainharder.model.services.authentifizierung;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortCheck;
import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortHasher;
import de.justinharder.trainharder.model.services.mail.MailServer;
import de.justinharder.trainharder.model.services.mapper.AuthentifizierungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrierungServiceSollte
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

		sut = new RegistrierungService(
			authentifizierungRepository,
			authentifizierungDtoMapper,
			passwortHasher,
			passwortCheck,
			mailServer);
	}

	private void angenommenDieMailIstVergeben(String mail, Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuMail(mail)).thenReturn(authentifizierung);
	}

	private void angenommenDerBenutzernameIstVergeben(String benutzername,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierung);
	}

	private void angenommenDasPasswortIstUnsicher(String passwort, boolean unsicher)
	{
		when(passwortCheck.isUnsicher(passwort)).thenReturn(unsicher);
	}

	private void angenommenDasAuthentifizierungRepositorySpeichertAuthentifizierung(Authentifizierung authentifizierung)
	{
		when(authentifizierungRepository.speichereAuthentifizierung(any(Authentifizierung.class)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(
		Authentifizierung authentifizierung, AuthentifizierungDto authentifizierungDto)
	{
		when(authentifizierungDtoMapper.mappe(authentifizierung)).thenReturn(authentifizierungDto);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(String authentifizierungId,
		Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(new Primaerschluessel(authentifizierungId)))
			.thenReturn(authentifizierung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Registrierung null ist")
	void test01()
	{
		var erwartet = "Zum Beitreten wird eine gültige Registrierung benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.registriere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("MailVergebenException werfen, wenn die Mail vergeben ist")
	void test02()
	{
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		var erwartet = "Die Mail \"" + registrierung.getMail() + "\" ist bereits vergeben!";
		angenommenDieMailIstVergeben(registrierung.getMail(), Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN));

		var exception = assertThrows(MailVergebenException.class, () -> sut.registriere(registrierung));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuMail(registrierung.getMail());
	}

	@Test
	@DisplayName("BenutzernameVergebenException werfen, wenn der Benutzername vergeben ist")
	void test03()
	{
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		var erwartet = "Der Benutzername \"" + registrierung.getBenutzername() + "\" ist bereits vergeben!";
		angenommenDieMailIstVergeben(registrierung.getMail(), Optional.empty());
		angenommenDerBenutzernameIstVergeben(registrierung.getBenutzername(),
			Optional.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN));

		var exception = assertThrows(BenutzernameVergebenException.class, () -> sut.registriere(registrierung));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuMail(registrierung.getMail());
		verify(authentifizierungRepository).ermittleZuBenutzername(registrierung.getBenutzername());
	}

	@Test
	@DisplayName("PasswortUnsicherException werfen, wenn das Passwort unsicher ist")
	void test04()
	{
		var erwartet = "Das Passwort ist unsicher!";
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		angenommenDieMailIstVergeben(registrierung.getMail(), Optional.empty());
		angenommenDerBenutzernameIstVergeben(registrierung.getBenutzername(), Optional.empty());
		angenommenDasPasswortIstUnsicher(registrierung.getPasswort(), true);

		var exception = assertThrows(PasswortUnsicherException.class, () -> sut.registriere(registrierung));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuMail(registrierung.getMail());
		verify(authentifizierungRepository).ermittleZuBenutzername(registrierung.getBenutzername());
		verify(passwortCheck).isUnsicher(registrierung.getPasswort());
	}

	@Test
	@DisplayName("einen Benutzer registrieren")
	void test05() throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException,
		InvalidKeySpecException, NoSuchAlgorithmException
	{
		var erwartet = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var registrierung = new Registrierung("mail@justinharder.de", "harder", "Justinharder#98");
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDieMailIstVergeben(registrierung.getMail(), Optional.empty());
		angenommenDerBenutzernameIstVergeben(registrierung.getBenutzername(), Optional.empty());
		angenommenDasPasswortIstUnsicher(registrierung.getPasswort(), false);
		angenommenDasAuthentifizierungRepositorySpeichertAuthentifizierung(authentifizierung);
		angenommenDerAuthentifizierungDtoMapperMapptZuAuthentifizierungDto(authentifizierung, erwartet);

		var ergebnis = sut.registriere(registrierung);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(authentifizierungRepository).ermittleZuMail(registrierung.getMail());
		verify(authentifizierungRepository).ermittleZuBenutzername(registrierung.getBenutzername());
		verify(passwortCheck).isUnsicher(registrierung.getPasswort());
		//		verify(mailServer).sendeMail(new Mail(
		//			new MailAdresse("mail@justinharder.de", "TrainHarder-Team"),
		//			"Willkommen bei TrainHarder!",
		//			"Hallo " + authentifizierung.getBenutzername() + ",\n"
		//				+ "wir heißen dich herzlich Willkommen bei TrainHarder!\n"
		//				+ "Über folgenden Link kannst du deine E-Mail-Adresse bestätigen: \n"
		//				+ "\thttps://www.trainharder.de/join/" + authentifizierung.getPrimaerschluessel().getId().toString()
		//				+ "\n\n"
		//				+ "Mit den besten Grüßen!\n"
		//				+ "das TrainHarder-Team")
		//					.fuegeEmpfaengerHinzu(new MailAdresse(authentifizierung.getMail())),
		//			StandardCharsets.UTF_8);
		verify(authentifizierungDtoMapper).mappe(authentifizierung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die AuthentifizierungID null ist")
	void test06()
	{
		var erwartet = "Zum Aktivieren wird eine gültige ID benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.aktiviere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn die AuthentifizierungID nicht existiert")
	void test07()
	{
		var authentifizierungId = new Primaerschluessel().getId().toString();
		var erwartet = "Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!";

		var exception = assertThrows(AuthentifizierungNichtGefundenException.class,
			() -> sut.aktiviere(authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Authentifizierung aktivieren")
	void test08() throws AuthentifizierungNichtGefundenException
	{
		var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		var authentifizierungId = authentifizierung.getPrimaerschluessel().getId().toString();
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(authentifizierungId,
			Optional.of(authentifizierung));
		angenommenDasAuthentifizierungRepositorySpeichertAuthentifizierung(authentifizierung);

		sut.aktiviere(authentifizierungId);

		verify(authentifizierungRepository).ermittleZuId(authentifizierung.getPrimaerschluessel());
		verify(authentifizierungRepository).speichereAuthentifizierung(authentifizierung);
	}
}
