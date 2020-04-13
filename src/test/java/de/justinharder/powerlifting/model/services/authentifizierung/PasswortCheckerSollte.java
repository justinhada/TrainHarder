package de.justinharder.powerlifting.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.services.authentifizierung.PasswortChecker;

public class PasswortCheckerSollte
{
	private PasswortChecker sut;

	@BeforeEach
	public void setup()
	{
		sut = new PasswortChecker();
	}

	@Test
	@DisplayName("false zurückgeben, wenn das Passwort zu kurz ist")
	public void test01()
	{
		final var erwartet = false;

		final var ergebnis = sut.check("zukurz");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("false zurückgeben, wenn das Passwort keinen Kleinbuchstaben hat")
	public void test02()
	{
		final var erwartet = false;

		final var ergebnis = sut.check("ICHHABEKEINENKLEINBUCHSTABEN#12");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("false zurückgeben, wenn das Passwort keinen Großbuchstaben hat")
	public void test03()
	{
		final var erwartet = false;

		final var ergebnis = sut.check("ichhabekeinengrossbuchstaben#12");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("false zurückgeben, wenn das Passwort kein Sonderzeichen hat")
	public void test04()
	{
		final var erwartet = false;

		final var ergebnis = sut.check("IchhabekeinSonderzeichen=12");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("false zurückgeben, wenn das Passwort keine Zahl hat")
	public void test05()
	{
		final var erwartet = false;

		final var ergebnis = sut.check("IchhabekeineZahl#");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("true zurückgeben, wenn das Passwort gültig ist")
	public void test06()
	{
		final var erwartet = true;

		final var ergebnis = sut.check("IchBinEinGueltigesPasswort#1234");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
