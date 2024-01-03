package de.justinharder.trainharder.domain.services.berechnung.koerpermasse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("FettfreiesKoerpergewicht sollte")
class FettfreiesKoerpergewichtTest
{
	private static final BigDecimal KOERPERGEWICHT = new BigDecimal(90);
	private static final BigDecimal KOERPERFETT_ANTEIL = new BigDecimal(25);

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> FettfreiesKoerpergewicht.aus(null, KOERPERFETT_ANTEIL)),
			() -> assertThrows(NullPointerException.class,
				() -> FettfreiesKoerpergewicht.aus(KOERPERGEWICHT, null)));
	}

	@Test
	@DisplayName("berechnet werden")
	void test02()
	{
		assertThat(FettfreiesKoerpergewicht.aus(KOERPERGEWICHT, KOERPERFETT_ANTEIL)).isEqualTo(new BigDecimal("67.50"));
	}
}
