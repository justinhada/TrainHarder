package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.mvc.Models;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;

public class DatenschutzControllerSollte
{
	private DatenschutzController sut;

	private Models models;
	private SecurityContext securityContext;
	private AuthentifizierungService authentifizierungService;
	private BenutzerService benutzerService;

	@BeforeEach
	public void setup()
	{
		sut = new DatenschutzController();

		models = mock(Models.class);
		securityContext = mock(SecurityContext.class);
		authentifizierungService = mock(AuthentifizierungService.class);
		benutzerService = mock(BenutzerService.class);

		sut.setModels(models);
		sut.setSecurityContext(securityContext);
		sut.setAuthentifizierungService(authentifizierungService);
		sut.setBenutzerService(benutzerService);
	}

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck(final Principal principal)
	{
		when(securityContext.getCallerPrincipal()).thenReturn(principal);
	}

	private void angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck()
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(null);
	}

	private void angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(
		final String benutzername) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername))
			.thenThrow(new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!"));
	}

	private void angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
		final String benutzername, final AuthentifizierungDto authentifizierungDto)
		throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierungDto);
	}

	private void angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(final String authentifizierungId,
		final BenutzerDto benutzerDto) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId)).thenReturn(benutzerDto);
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test01()
	{
		final var erwartet = "/datenschutz.xhtml";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren mit Servicefehler")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/datenschutz.xhtml";
		final var callerPrincipal = new CallerPrincipal(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(callerPrincipal.getName());

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + callerPrincipal.getName() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Datenschutz-Seite per GET navigieren mit angemeldeten Benutzer")
	public void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/datenschutz.xhtml";
		final var callerPrincipal = new CallerPrincipal(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
			callerPrincipal.getName(),
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(
			authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}
}
