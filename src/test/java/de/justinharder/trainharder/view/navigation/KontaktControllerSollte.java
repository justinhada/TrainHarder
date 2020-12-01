package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.model.services.KontaktService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;
import java.security.Principal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class KontaktControllerSollte
{
	private KontaktController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;
	private KontaktService kontaktService;
	private AuthentifizierungService authentifizierungService;
	private BenutzerService benutzerService;

	@BeforeEach
	void setup()
	{
		sut = new KontaktController();

		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);
		kontaktService = mock(KontaktService.class);
		authentifizierungService = mock(AuthentifizierungService.class);
		benutzerService = mock(BenutzerService.class);

		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setSecurityContext(securityContext);
		sut.setKontaktService(kontaktService);
		sut.setAuthentifizierungService(authentifizierungService);
		sut.setBenutzerService(benutzerService);
	}

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck(Principal principal)
	{
		when(securityContext.getCallerPrincipal()).thenReturn(principal);
	}

	private void angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(String benutzername)
		throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername))
			.thenThrow(new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!"));
	}

	private void angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(String benutzername,
		AuthentifizierungDto authentifizierungDto) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierungDto);
	}

	private void angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(String authentifizierungId,
		BenutzerDto benutzerDto) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId)).thenReturn(benutzerDto);
	}

	private void angenommenDasBindingResultFailed()
	{
		when(bindingResult.isFailed()).thenReturn(true);
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren ohne angemeldeten Benutzer")
	void test01()
	{
		var erwartet = "/kontakt.xhtml";

		var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren mit Servicefehler")
	void test02() throws AuthentifizierungNichtGefundenException
	{
		var erwartet = "/kontakt.xhtml";
		var callerPrincipal = new CallerPrincipal(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(callerPrincipal.getName());

		var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + callerPrincipal.getName() + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren mit angemeldeten Benutzer")
	void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var erwartet = "/kontakt.xhtml";
		var callerPrincipal = new CallerPrincipal(Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getBenutzername());
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var benutzerDto = Testdaten.BENUTZER_DTO_JUSTIN;
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
			callerPrincipal.getName(),
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(
			authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Kontaktformular null ist")
	void test04()
	{
		var erwartet = "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.kontaktiere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Kontakt-Seite navigieren")
	void test05()
	{
		var erwartet = "/kontakt.xhtml";
		angenommenDasBindingResultFailed();

		var ergebnis = sut.kontaktiere(
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Nachrichtentext..."));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei erfolgreichem Kontaktieren zur Kontaktiert-Seite per GET navigieren")
	void test06()
	{
		var erwartet = "/kontaktiert.xhtml";

		var kontaktformular =
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Nachrichtentext...");
		var ergebnis = sut.kontaktiere(kontaktformular);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kontaktService).kontaktiere(kontaktformular);
		verify(models).put("kontaktformular", kontaktformular);
	}
}
