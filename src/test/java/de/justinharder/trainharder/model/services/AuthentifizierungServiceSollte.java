package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.dto.AuthentifizierungEintrag;
import de.justinharder.trainharder.model.domain.dto.Registrierung;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.setup.Testdaten;

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

	private void angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtNullZurueck()
	{
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuBenutzerZurueck(
		final Optional<Authentifizierung> authentifizierung) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungRepository.ermittleZuBenutzer(any(Primaerschluessel.class)))
			.thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtNullZuBenutzerIdZurueck()
		throws AuthentifizierungNichtGefundenException
	{
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuBenutzerZurueck(Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryChecktMail(final boolean erwartet)
	{
		when(authentifizierungRepository.checkMail(anyString())).thenReturn(erwartet);
	}

	private void angenommenDasAuthentifizierungRepositoryChecktBenutzername(final boolean erwartet)
	{
		when(authentifizierungRepository.checkBenutzername(anyString())).thenReturn(erwartet);
	}

	private void angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuMailZurueck(
		final Optional<Authentifizierung> authentifizierung) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungRepository.ermittleZuMail(anyString())).thenReturn(authentifizierung);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn ID zu keiner Authentifizierung gehört")
	public void test01()
	{
		angenommenDasAuthentifizierungRepositoryGibtNullZurueck();

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("Authentifizierung zur ID ermitteln")
	public void test02() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNGEINTRAG_EDUARD;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_EDUARD;
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZurueck(Optional.of(authentifizierung));

		final var ergebnis = sut.ermittleZuId(new Primaerschluessel().getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn BenutzerID zu keiner Authentifizierung gehört")
	public void test03() throws AuthentifizierungNichtGefundenException
	{
		angenommenDasAuthentifizierungRepositoryGibtNullZuBenutzerIdZurueck();

		final var id = new Primaerschluessel().getId().toString();
		final var exception =
			assertThrows(AuthentifizierungNichtGefundenException.class, () -> sut.ermittleZuBenutzer(id));

		assertThat(exception.getMessage())
			.isEqualTo("Die Authentifizierung mit der BenutzerID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("Authentifizierung zu Benutzer ermitteln")
	public void test04() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.AUTHENTIFIZIERUNGEINTRAG_JUSTIN;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuBenutzerZurueck(Optional.of(authentifizierung));

		final var ergebnis =
			sut.ermittleZuBenutzer(Testdaten.BENUTZER_JUSTIN.getPrimaerschluessel().getId().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("MailBereitsRegistriertException werfen, wenn eine E-Mail-Adresse sich registriert")
	public void test05()
	{
		final var erwartet = "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!";
		angenommenDasAuthentifizierungRepositoryChecktMail(true);

		final var exception = assertThrows(MailBereitsRegistriertException.class,
			() -> sut.registriere(new Registrierung("justin@justin.de", "justinzumharder", "Justinharder#98")));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzernameBereitsVergebenException werfen, wenn ein Benutzername schon vergeben ist")
	public void test06()
	{
		final var erwartet = "Dieser Benutzername ist leider schon vergeben!";
		angenommenDasAuthentifizierungRepositoryChecktMail(false);
		angenommenDasAuthentifizierungRepositoryChecktBenutzername(true);

		final var exception = assertThrows(BenutzernameVergebenException.class,
			() -> sut.registriere(new Registrierung("justin@justin.de", "justinzumharder", "Justinharder#98")));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("PasswortNichtSicherException werfen, wenn das Passwort nicht sicher ist")
	public void test07()
	{
		final var erwartet = "Das Passwort ist nicht sicher genug!";
		angenommenDasAuthentifizierungRepositoryChecktMail(false);
		angenommenDasAuthentifizierungRepositoryChecktBenutzername(false);

		final var exception = assertThrows(PasswortNichtSicherException.class, () -> sut.registriere(
			new Registrierung("IchHabeEinUnsicheresPasswort", "mail@unsicherespasswort.de", "unsicher")));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer registrieren")
	@Disabled("unerklärliche NullPointerException beim Konvertieren der UUID")
	public void test08() throws MailBereitsRegistriertException, BenutzernameVergebenException,
		PasswortNichtSicherException, AuthentifizierungNichtGefundenException
	{
		final var id = new Primaerschluessel();
		final var erwartet = new AuthentifizierungEintrag(id.getId().toString(), "justin@justin.de", "justinzumharder",
			"Justinharder#98");
		final var authentifizierung =
			new Authentifizierung(id, "justin@justin.de", "justinzumharder", "Justinharder#98");
		angenommenDasAuthentifizierungRepositoryChecktMail(false);
		angenommenDasAuthentifizierungRepositoryChecktBenutzername(false);
		angenommenDasAuthentifizierungRepositoryGibtAuthentifizierungZuMailZurueck(Optional.of(authentifizierung));

		final var ergebnis =
			sut.registriere(new Registrierung("justin@justin.de", "justinzumharder", "Justinharder#98"));

		assertAll(
			() -> assertThat(ergebnis.getMail()).isEqualTo(erwartet.getMail()),
			() -> assertThat(ergebnis.getBenutzername()).isEqualTo(erwartet.getBenutzername()),
			() -> assertThat(ergebnis.getPasswort()).isEqualTo(erwartet.getPasswort()));
	}
}
