package de.justinharder.trainharder.view.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.RegistrierungService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class RegistrierungControllerSollte
{
	private RegistrierungController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;
	private RegistrierungService registrierungService;

	@BeforeEach
	public void setup()
	{
		sut = new RegistrierungController();

		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);
		registrierungService = mock(RegistrierungService.class);

		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setSecurityContext(securityContext);
		sut.setRegistrierungService(registrierungService);
	}

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck(final Principal principal)
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
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		when(registrierungService.registriere(any(Registrierung.class))).thenThrow(
			new MailVergebenException("Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!"));
	}

	private void angenommenDerRegistrierungServiceGibtAuthentifizierungDtoZurueck(
		final AuthentifizierungDto authentifizierungDto)
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		when(registrierungService.registriere(any(Registrierung.class))).thenReturn(authentifizierungDto);
	}

	private void angenommenDerRegistrierungServiceWirftAuthentifizierungNichtGefundenException(
		final String authentifizierungId) throws AuthentifizierungNichtGefundenException
	{
		doThrow(new AuthentifizierungNichtGefundenException(
			"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!"))
				.when(registrierungService).aktiviere(authentifizierungId);
	}

	@Test
	@DisplayName("zur Registrierung-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	public void test01()
	{
		final var callerPrincipal = new CallerPrincipal("harder");
		final var erwartet = "redirect:benutzer/" + callerPrincipal.getName();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Registrierung-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	public void test02()
	{
		final var erwartet = "/join.xhtml";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Registrierung null ist")
	public void test03()
	{
		final var erwartet = "Zum Beitreten wird eine gültige Registrierung benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.registriere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Registrierung-Seite navigieren")
	public void test04()
	{
		final var erwartet = "/join.xhtml";
		angenommenDasBindingResultFailed();

		final var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei fehlerhafter Registrierung zurück zur Registrierung-Seite navigieren")
	public void test05()
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		final var erwartet = "/join.xhtml";
		angenommenDerRegistrierungServiceWirftMailBereitsRegistriertException();

		final var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!");
	}

	@Test
	@DisplayName("bei erfolgreicher Registrierung zur Success-Seite navigieren")
	public void test06()
		throws MailVergebenException, BenutzernameVergebenException, PasswortUnsicherException
	{
		final var erwartet = "/success.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		angenommenDerRegistrierungServiceGibtAuthentifizierungDtoZurueck(authentifizierungDto);

		final var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die AuthentifizierungID null ist")
	public void test07()
	{
		final var erwartet = "Zum Aktivieren wird eine gültige ID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.aktiviere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlerhafter Aktivierung zur Fehler-Seite navigieren")
	public void test08() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/error";
		final var authentifizierungId = new Primaerschluessel().getId().toString();
		angenommenDerRegistrierungServiceWirftAuthentifizierungNichtGefundenException(authentifizierungId);

		final var ergebnis = sut.aktiviere(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(registrierungService).aktiviere(authentifizierungId);
		verify(models).put(
			"fehler",
			"Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!");
	}

	@Test
	@DisplayName("bei erfolgreicher Aktivierung zur Aktiviert-Seite navigieren")
	public void test09() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/aktiviert.xhtml";
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();

		final var ergebnis = sut.aktiviere(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(registrierungService).aktiviere(authentifizierungId);
	}
}
