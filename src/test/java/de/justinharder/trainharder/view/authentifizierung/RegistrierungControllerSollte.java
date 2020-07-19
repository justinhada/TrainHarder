package de.justinharder.trainharder.view.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.trainharder.model.services.authentifizierung.RegistrierungService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Registrierung;

public class RegistrierungControllerSollte
{
	private RegistrierungController sut;

	private Models models;
	private BindingResult bindingResult;
	private RegistrierungService registrierungService;

	@BeforeEach
	public void setup()
	{
		sut = new RegistrierungController();

		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		registrierungService = mock(RegistrierungService.class);

		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setRegistrierungService(registrierungService);
	}

	private void angenommenDasBindingResultFailed()
	{
		when(bindingResult.isFailed()).thenReturn(true);
	}

	private void angenommenDerAuthentifizierungServiceWirftMailBereitsRegistriertException()
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		when(registrierungService.registriere(any(Registrierung.class))).thenThrow(
			new MailBereitsRegistriertException("Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!"));
	}

	private void angenommenDerAuthentifizierungServiceGibtAuthentifizierungDtoZurueck(
		final AuthentifizierungDto authentifizierungDto)
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		when(registrierungService.registriere(any(Registrierung.class))).thenReturn(authentifizierungDto);
	}

	@Test
	@DisplayName("zur Registrierung-Seite per GET navigieren")
	public void test01()
	{
		final var erwartet = "/join.xhtml";

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Registrierung null ist")
	public void test02()
	{
		final var erwartet = "Zum Beitreten wird eine gültige Registrierung benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.registriere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Registrierung-Seite navigieren")
	public void test03()
	{
		final var erwartet = "/join.xhtml";
		angenommenDasBindingResultFailed();

		final var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei fehlerhafter Registrierung zurück zur Registrierung-Seite navigieren")
	public void test04()
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		final var erwartet = "/join.xhtml";
		angenommenDerAuthentifizierungServiceWirftMailBereitsRegistriertException();

		final var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("fehler", "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!");
	}

	@Test
	@DisplayName("bei erfolgreicher Registrierung zur Success-Seite navigieren")
	public void test05()
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		final var erwartet = "/success.xhtml";
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		angenommenDerAuthentifizierungServiceGibtAuthentifizierungDtoZurueck(authentifizierungDto);

		final var ergebnis = sut.registriere(new Registrierung("mail@justinharder.de", "harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(models).put("authentifizierung", authentifizierungDto);
	}
}
