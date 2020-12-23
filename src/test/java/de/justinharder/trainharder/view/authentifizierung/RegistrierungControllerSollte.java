package de.justinharder.trainharder.view.authentifizierung;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.RegistrierungService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrierungControllerSollte
{
	private RegistrierungController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;
	private RegistrierungService registrierungService;

	@BeforeEach
	void setup()
	{
		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);
		registrierungService = mock(RegistrierungService.class);

		sut = new RegistrierungController();

		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setSecurityContext(securityContext);
		sut.setRegistrierungService(registrierungService);
	}

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck(Principal principal)
	{
		when(securityContext.getCallerPrincipal()).thenReturn(principal);
	}

	private void angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck()
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(null);
	}

	private void angenommenDasBindingResultFailed()
	{
		when(bindingResult.isFailed()).thenReturn(true);
	}

	private void angenommenDerRegistrierungServiceWirftMailBereitsRegistriertException()
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		when(registrierungService.registriere(any(Registrierung.class))).thenThrow(
			new MailVergebenException("Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!"));
	}

	private void angenommenDerRegistrierungServiceGibtAuthentifizierungDtoZurueck(
		AuthentifizierungDto authentifizierungDto)
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		when(registrierungService.registriere(any(Registrierung.class))).thenReturn(authentifizierungDto);
	}

	private void angenommenDerRegistrierungServiceWirftAuthentifizierungNichtGefundenException(
		String authentifizierungId) throws AuthentifizierungNichtGefundenException
	{
		doThrow(new AuthentifizierungNichtGefundenException(
			"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"))
			.when(registrierungService).aktiviere(authentifizierungId);
	}

	@Test
	@DisplayName("zur Registrierung-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	void test01()
	{
		var callerPrincipal = new CallerPrincipal("harder");
		var erwartet = "redirect:benutzer/" + callerPrincipal.getName();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Registrierung-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	void test02()
	{
		var erwartet = "/join.xhtml";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.registriere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktiviere(null)));
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Registrierung-Seite navigieren")
	void test04()
	{
		var erwartet = "/join.xhtml";
		angenommenDasBindingResultFailed();

		var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei fehlerhafter Registrierung zurück zur Registrierung-Seite navigieren")
	void test05()
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		var erwartet = "/join.xhtml";
		angenommenDerRegistrierungServiceWirftMailBereitsRegistriertException();

		var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!");
	}

	@Test
	@DisplayName("bei erfolgreicher Registrierung zur Success-Seite navigieren")
	void test06()
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		var erwartet = "/success.xhtml";
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		angenommenDerRegistrierungServiceGibtAuthentifizierungDtoZurueck(authentifizierungDto);

		var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
	}

	@Test
	@DisplayName("bei fehlerhafter Aktivierung zur Fehler-Seite navigieren")
	void test07() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/error";
		var authentifizierungId = new Primaerschluessel().getId().toString();
		angenommenDerRegistrierungServiceWirftAuthentifizierungNichtGefundenException(authentifizierungId);

		var ergebnis = sut.aktiviere(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(registrierungService).aktiviere(authentifizierungId);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!");
	}

	@Test
	@DisplayName("bei erfolgreicher Aktivierung zur Aktiviert-Seite navigieren")
	void test08() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/aktiviert.xhtml";
		var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();

		var ergebnis = sut.aktiviere(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(registrierungService).aktiviere(authentifizierungId);
	}
}
