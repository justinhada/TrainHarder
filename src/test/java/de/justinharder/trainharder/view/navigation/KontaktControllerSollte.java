package de.justinharder.trainharder.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.MailException;
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
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Kontakt-Seite navigieren")
	public void test03()
	{
		final var erwartet = "/kontakt.xhtml";
		angenommenDasBindingResultFailed();

		final var ergebnis = sut.kontaktiere(
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Fehlerhafte Nachricht"));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei erfolgreichem Kontaktieren zur Kontaktiert-Seite navigieren")
	public void test04() throws MailException
	{
		final var erwartet = "/kontaktiert.xhtml";

		final var kontaktformular =
			new Kontaktformular("mail@justinharder.de", "harder", "Justin", "Harder", "Fehlerhafte Nachricht");
		final var ergebnis = sut.kontaktiere(kontaktformular);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(kontaktService).kontaktiere(kontaktformular);
	}
}
