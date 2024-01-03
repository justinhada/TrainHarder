package de.justinharder.trainharder.domain.services.authentifizierung.passwort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswortCheckSollte
{
	private PasswortCheck sut;

	@BeforeEach
	void setup()
	{
		sut = new PasswortCheck();
	}

	@Test
	@DisplayName("ein ungültiges Passwort checken")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.isUnsicher("zukurz")).isTrue(),
			() -> assertThat(sut.isUnsicher("ICHHABEKEINENKLEINBUCHSTABEN#12")).isTrue(),
			() -> assertThat(sut.isUnsicher("ichhabekeinengrossbuchstaben#12")).isTrue(),
			() -> assertThat(sut.isUnsicher("IchhabekeinSonderzeichen=12")).isTrue(),
			() -> assertThat(sut.isUnsicher("IchhabekeineZahl#")).isTrue());
	}

	@Test
	@DisplayName("ein gültiges Passwort checken")
	void test02()
	{
		assertThat(sut.isUnsicher("IchBinEinGueltigesPasswort#1234")).isFalse();
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> sut.isUnsicher(null));
	}
}
