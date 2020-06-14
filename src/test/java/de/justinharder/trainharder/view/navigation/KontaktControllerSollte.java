package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.services.KontaktService;
import de.justinharder.trainharder.view.dto.Kontaktformular;

public class KontaktControllerSollte
{
	private KontaktController sut;

	private Models models;
	private BindingResult bindingResult;
	private KontaktService kontaktService;

	@BeforeEach
	public void setup()
	{
		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		kontaktService = mock(KontaktService.class);
		sut = new KontaktController();
		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setKontaktService(kontaktService);
	}

	private void angenommenDasBindingResultFailed()
	{
		when(bindingResult.isFailed()).thenReturn(true);
	}

	@Test
	@DisplayName("zur Kontakt-Seite per GET navigieren")
	public void test01()
	{
		final var erwartet = "/kontakt.xhtml";

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Kontaktformular null ist")
	public void test02()
	{
		final var erwartet = "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.kontaktiere(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlgeschlagenem Kontaktieren zurück zur Kontakt-Seite navigieren")
	public void test03() throws MessagingException
	{
		final var erwartet = "/kontakt.xhtml";
		angenommenDerKontaktServiceWirftMessagingException();

		final var ergebnis = sut.kontaktiere(
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Fehlerhafte Nachricht"));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	private void angenommenDerKontaktServiceWirftMessagingException() throws MessagingException
	{
		doThrow(MessagingException.class).when(kontaktService).kontaktiere(any(Kontaktformular.class));
	}
}
