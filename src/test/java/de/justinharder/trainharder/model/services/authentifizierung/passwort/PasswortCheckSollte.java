package de.justinharder.trainharder.model.services.authentifizierung.passwort;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.services.authentifizierung.passwort.PasswortCheck;

public class PasswortCheckSollte
{
	private PasswortCheck sut;

	@BeforeEach
	public void setup()
	{
		sut = new PasswortCheck();
	}

	@Test
	@DisplayName("true zurückgeben, wenn das Passwort zu kurz ist")
	public void test01()
	{
		final var erwartet = true;

		final var ergebnis = sut.isUnsicher("zukurz");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("true zurückgeben, wenn das Passwort keinen Kleinbuchstaben hat")
	public void test02()
	{
		final var erwartet = true;

		final var ergebnis = sut.isUnsicher("ICHHABEKEINENKLEINBUCHSTABEN#12");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("true zurückgeben, wenn das Passwort keinen Großbuchstaben hat")
	public void test03()
	{
		final var erwartet = true;

		final var ergebnis = sut.isUnsicher("ichhabekeinengrossbuchstaben#12");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("true zurückgeben, wenn das Passwort kein Sonderzeichen hat")
	public void test04()
	{
		final var erwartet = true;

		final var ergebnis = sut.isUnsicher("IchhabekeinSonderzeichen=12");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("true zurückgeben, wenn das Passwort keine Zahl hat")
	public void test05()
	{
		final var erwartet = true;

		final var ergebnis = sut.isUnsicher("IchhabekeineZahl#");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("false zurückgeben, wenn das Passwort gültig ist")
	public void test06()
	{
		final var erwartet = false;

		final var ergebnis = sut.isUnsicher("IchBinEinGueltigesPasswort#1234");

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
