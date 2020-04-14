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

import de.justinharder.powerlifting.model.domain.Authentifizierung;
import de.justinharder.powerlifting.model.domain.dto.Registrierung;
import de.justinharder.powerlifting.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.powerlifting.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.powerlifting.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.powerlifting.model.repository.AuthentifizierungRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class AuthentifizierungServiceSollte
{
	private AuthentifizierungService sut;
	private AuthentifizierungRepository authentifizierungRepository;

	@BeforeEach
	public void setup()
	{
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		sut = new AuthentifizierungService(authentifizierungRepository);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtAlleAuthentifizierungenZurueck(
		final List<Authentifizierung> authentifizierungen)
	{
		when(authentifizierungRepository.ermittleAlle()).thenReturn(authentifizierungen);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(
		final Authentifizierung authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(anyInt())).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtNullZurueck()
	{
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(null);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuBenutzerZurueck(
		final Authentifizierung authentifizierung)
		throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungRepository.ermittleZuBenutzer(anyInt())).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtNullZuBenutzerIdZurueck()
		throws AuthentifizierungNichtGefundenException
	{
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuBenutzerZurueck(null);
	}

	private void angenommenDasAuthentifizierungRepositoryChecktMail(final boolean erwartet)
	{
		when(authentifizierungRepository.checkMail(anyString())).thenReturn(erwartet);
	}

	private void angenommenDasAuthentifizierungRepositoryChecktBenutzername(final boolean erwartet)
	{
		when(authentifizierungRepository.checkBenutzername(anyString())).thenReturn(erwartet);
	}

	@Test
	@DisplayName("alle Authentifizierungen ermitteln")
	public void test01()
	{
		final var erwartet =
			List.of(Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN, Testdaten.AUTHENTIFIZIERUNGEINTRAG_EDUARD);
		final var authentifizierungen = List.of(Testdaten.AUTHENTIFIZIERUNG_JUSTIN, Testdaten.AUTHENTIFIZIERUNG_EDUARD);
		angenommenDasAuthentifizierungRepositoryGibtAlleAuthentifizierungenZurueck(authentifizierungen);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn ID zu keiner Authentifizierung gehört")
	public void test02()
	{
		angenommenDasAuthentifizierungRepositoryGibtNullZurueck();

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuId("10000"));

		assertThat(exception.getMessage()).isEqualTo("Die Authentifizierung mit der ID \"10000\" existiert nicht!");
	}

	@Test
	@DisplayName("Authentifizierung zur ID ermitteln")
	public void test03() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNGEINTRAG_EDUARD;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_EDUARD;
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(authentifizierung);

		final var ergebnis = sut.ermittleZuId("0");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn BenutzerID zu keiner Authentifizierung gehört")
	public void test04() throws AuthentifizierungNichtGefundenException
	{
		angenommenDasAuthentifizierungRepositoryGibtNullZuBenutzerIdZurueck();

		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzer("10000"));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der BenutzerID \"10000\" existiert nicht!");
	}

	@Test
	@DisplayName("Authentifizierung zu Benutzer ermitteln")
	public void test05() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuBenutzerZurueck(authentifizierung);

		final var ergebnis = sut.ermittleZuBenutzer(String.valueOf(Testdaten.BENUTZER_JUSTIN.getId()));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Authentifizierung erstellen")
	public void test06() throws AuthentifizierungNichtGefundenException
	{
		final var registrierung = Testdaten.REGISTRIERUNG;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;

		sut.erstelleAuthentifizierung(registrierung);

		verify(authentifizierungRepository).erstelleAuthentifizierung(authentifizierung);
	}

	@Test
	@DisplayName("MailBereitsRegistriertException werfen, wenn eine E-Mail-Adresse sich registriert")
	public void test07()
	{
		final var erwartet = "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!";
		angenommenDasAuthentifizierungRepositoryChecktMail(true);

		final var exception = assertThrows(MailBereitsRegistriertException.class,
			() -> sut.registriere(Testdaten.REGISTRIERUNG));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzernameBereitsVergebenException werfen, wenn ein Benutzername schon vergeben ist")
	public void test08()
	{
		final var erwartet = "Dieser Benutzername ist leider schon vergeben!";
		angenommenDasAuthentifizierungRepositoryChecktMail(false);
		angenommenDasAuthentifizierungRepositoryChecktBenutzername(true);

		final var exception = assertThrows(BenutzernameVergebenException.class,
			() -> sut.registriere(Testdaten.REGISTRIERUNG));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("PasswortNichtSicherException werfen, wenn das Passwort nicht sicher ist")
	public void test09()
	{
		final var erwartet = "Das Passwort ist nicht sicher genug!";
		angenommenDasAuthentifizierungRepositoryChecktMail(false);
		angenommenDasAuthentifizierungRepositoryChecktBenutzername(false);

		final var exception = assertThrows(PasswortNichtSicherException.class,
			() -> sut.registriere(
				new Registrierung("IchHabeEinUnsicheresPasswort", "mail@unsicherespasswort.de", "unsicher")));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer registrieren")
	public void test10()
		throws MailBereitsRegistriertException, BenutzernameVergebenException, PasswortNichtSicherException,
		AuthentifizierungNichtGefundenException
	{
		angenommenDasAuthentifizierungRepositoryChecktMail(false);
		angenommenDasAuthentifizierungRepositoryChecktBenutzername(false);

		sut.registriere(Testdaten.REGISTRIERUNG);

		verify(authentifizierungRepository).erstelleAuthentifizierung(Testdaten.AUTHENTIFIZIERUNG_JUSTIN);
	}
}
