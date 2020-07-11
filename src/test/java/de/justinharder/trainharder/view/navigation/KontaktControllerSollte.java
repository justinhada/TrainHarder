package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.MailException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.model.services.KontaktService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.Kontaktformular;

public class KontaktControllerSollte
{
	private KontaktController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;
	private CallerPrincipal callerPrincipal;
	private KontaktService kontaktService;
	private AuthentifizierungService authentifizierungService;
	private BenutzerService benutzerService;

	@BeforeEach
	public void setup()
	{
		sut = new KontaktController();

		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);
		callerPrincipal = mock(CallerPrincipal.class);
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

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck()
	{
		when(securityContext.getCallerPrincipal()).thenReturn(callerPrincipal);
	}

	private void angenommenDasCallerPrincipalGibtNamenZurueck(final String name)
	{
		when(callerPrincipal.getName()).thenReturn(name);
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

	private void angenommenDasBindingResultFailed()
	{
		when(bindingResult.isFailed()).thenReturn(true);
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren ohne angemeldeten Benutzer")
	public void test01()
	{
		final var erwartet = "/kontakt.xhtml";

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren mit Servicefehler")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/kontakt.xhtml";
		final var benutzername = "harder";
		angenommenDerSecurityContextGibtCallerPrincipalZurueck();
		angenommenDasCallerPrincipalGibtNamenZurueck(benutzername);
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(benutzername);

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler",
			"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!");
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren mit angemeldeten Benutzer")
	public void test03() throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var erwartet = "/kontakt.xhtml";
		final var benutzername = "harder";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN;
		final var benutzerDto = Testdaten.BENUTZEREINTRAG_JUSTIN;
		angenommenDerSecurityContextGibtCallerPrincipalZurueck();
		angenommenDasCallerPrincipalGibtNamenZurueck(benutzername);
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
			benutzername,
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(
			authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
		verify(models).put("benutzer", benutzerDto);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Kontaktformular null ist")
	public void test04()
	{
		final var erwartet = "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.kontaktiere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Kontakt-Seite navigieren")
	public void test05()
	{
		final var erwartet = "/kontakt.xhtml";
		angenommenDasBindingResultFailed();

		final var ergebnis = sut.kontaktiere(
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Nachrichtentext..."));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei erfolgreichem Kontaktieren zur Kontaktiert-Seite navigieren")
	public void test06() throws MailException
	{
		final var erwartet = "/kontaktiert.xhtml";

		final var kontaktformular =
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Nachrichtentext...");
		final var ergebnis = sut.kontaktiere(kontaktformular);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kontaktService).kontaktiere(kontaktformular);
		verify(models).put("kontaktformular", kontaktformular);
	}
}
