package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.dto.AnmeldedatenEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.AnmeldedatenNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.powerlifting.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.powerlifting.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.powerlifting.model.repository.AnmeldedatenRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class AnmeldedatenServiceSollte
{
	private AnmeldedatenService sut;
	private AnmeldedatenRepository anmeldedatenRepository;

	@BeforeEach
	public void setup()
	{
		anmeldedatenRepository = mock(AnmeldedatenRepository.class);
		sut = new AnmeldedatenService(anmeldedatenRepository);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtAlleAnmeldedatenZurueck(
		final List<Anmeldedaten> alleAnmeldedaten)
	{
		when(anmeldedatenRepository.ermittleAlle()).thenReturn(alleAnmeldedaten);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZurueck(final Anmeldedaten anmeldedaten)
	{
		when(anmeldedatenRepository.ermittleZuId(anyInt())).thenReturn(anmeldedaten);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtNullZurueck()
	{
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZurueck(null);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZuBenutzerZurueck(final Anmeldedaten anmeldedaten)
		throws AnmeldedatenNichtGefundenException
	{
		when(anmeldedatenRepository.ermittleZuBenutzer(anyInt())).thenReturn(anmeldedaten);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtNullZuBenutzerIdZurueck()
		throws AnmeldedatenNichtGefundenException
	{
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZuBenutzerZurueck(null);
	}

	private void angenommenDasAnmeldedatenRepositoryChecktMail(final boolean erwartet)
	{
		when(anmeldedatenRepository.checkMail(anyString())).thenReturn(erwartet);
	}

	private void angenommenDasAnmeldedatenRepositoryChecktBenutzername(final boolean erwartet)
	{
		when(anmeldedatenRepository.checkBenutzername(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("alle Anmeldedaten ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.ANMELDEDATENEINTRAG_JUSTIN, Testdaten.ANMELDEDATENEINTRAG_EDUARD);
		final var alleAnmeldedaten = List.of(Testdaten.ANMELDEDATEN_JUSTIN, Testdaten.ANMELDEDATEN_EDUARD);
		angenommenDasAnmeldedatenRepositoryGibtAlleAnmeldedatenZurueck(alleAnmeldedaten);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AnmeldedatenNichtGefundenException werfen, wenn ID zu keinen Anmeldedaten gehört")
	public void test02()
	{
		angenommenDasAnmeldedatenRepositoryGibtNullZurueck();

		final var exception = assertThrows(AnmeldedatenNichtGefundenException.class, () -> sut.ermittleZuId("10000"));

		assertThat(exception.getMessage()).isEqualTo("Die Anmeldedaten mit der ID \"10000\" existieren nicht!");
	}

	@Test
	@DisplayName("Anmeldedaten zur ID ermitteln")
	public void test03() throws AnmeldedatenNichtGefundenException
	{
		final var erwartet = Testdaten.ANMELDEDATENEINTRAG_EDUARD;
		final var anmeldedaten = Testdaten.ANMELDEDATEN_EDUARD;
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZurueck(anmeldedaten);

		final var ergebnis = sut.ermittleZuId("0");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AnmeldedatenNichtGefundenException werfen, wenn BenutzerID zu keinen Anmeldedaten gehört")
	public void test04() throws AnmeldedatenNichtGefundenException
	{
		angenommenDasAnmeldedatenRepositoryGibtNullZuBenutzerIdZurueck();

		final var exception =
			assertThrows(AnmeldedatenNichtGefundenException.class, () -> sut.ermittleZuBenutzer("10000"));

		assertThat(exception.getMessage()).isEqualTo("Die Anmeldedaten mit der BenutzerID \"10000\" existieren nicht!");
	}

	@Test
	@DisplayName("Anmeldedaten zu Benutzer ermitteln")
	public void test05() throws AnmeldedatenNichtGefundenException
	{
		final var erwartet = Testdaten.ANMELDEDATENEINTRAG_JUSTIN;
		final var anmeldedaten = Testdaten.ANMELDEDATEN_JUSTIN;
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZuBenutzerZurueck(anmeldedaten);

		final var ergebnis = sut.ermittleZuBenutzer(String.valueOf(Testdaten.BENUTZER_JUSTIN.getId()));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Anmeldedaten erstellen")
	public void test06()
	{
		final var anmeldedatenEintrag = Testdaten.ANMELDEDATENEINTRAG_JUSTIN;
		final var anmeldedaten = Testdaten.ANMELDEDATEN_JUSTIN;

		sut.erstelleAnmeldedaten(anmeldedatenEintrag);

		verify(anmeldedatenRepository).erstelleAnmeldedaten(anmeldedaten);
	}

	@Test
	@DisplayName("MailBereitsRegistriertException werfen, wenn eine E-Mail-Adresse sich registriert")
	public void test07()
	{
		final var erwartet = "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!";
		angenommenDasAnmeldedatenRepositoryChecktMail(true);

		final var exception = assertThrows(MailBereitsRegistriertException.class,
			() -> sut.registriereBenutzer(Testdaten.ANMELDEDATENEINTRAG_JUSTIN));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzernameBereitsVergebenException werfen, wenn ein Benutzername schon vergeben ist")
	public void test08()
	{
		final var erwartet = "Dieser Benutzername ist leider schon vergeben!";
		angenommenDasAnmeldedatenRepositoryChecktMail(false);
		angenommenDasAnmeldedatenRepositoryChecktBenutzername(true);

		final var exception = assertThrows(BenutzernameVergebenException.class,
			() -> sut.registriereBenutzer(Testdaten.ANMELDEDATENEINTRAG_JUSTIN));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("PasswortNichtSicherException werfen, wenn das Passwort nicht sicher ist")
	public void test09()
	{
		final var erwartet = "Das Passwort ist nicht sicher genug!";
		angenommenDasAnmeldedatenRepositoryChecktMail(false);
		angenommenDasAnmeldedatenRepositoryChecktBenutzername(false);

		final var exception = assertThrows(PasswortNichtSicherException.class,
			() -> sut.registriereBenutzer(
				new AnmeldedatenEintrag(1, "mail@unsicherespasswort.de", "IchHabeEinUnsicheresPasswort", "unsicher")));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer registrieren")
	public void test10()
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException
	{
		angenommenDasAnmeldedatenRepositoryChecktMail(false);
		angenommenDasAnmeldedatenRepositoryChecktBenutzername(false);

		sut.registriereBenutzer(Testdaten.ANMELDEDATENEINTRAG_JUSTIN);

		verify(anmeldedatenRepository).erstelleAnmeldedaten(Testdaten.ANMELDEDATEN_JUSTIN);
	}
}
