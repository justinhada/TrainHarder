package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FettfreiesKoerpergewichtSollte
{
	@Test
	@DisplayName("null validieren")
	void test01()
	{
		var koerpergewicht = new BigDecimal(90);
		var koerperfettAnteil = new BigDecimal(20);
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> FettfreiesKoerpergewicht.aus(null, koerperfettAnteil)),
			() -> assertThrows(NullPointerException.class, () -> FettfreiesKoerpergewicht.aus(koerpergewicht, null)));
	}

	@Test
	@DisplayName("berechnet werden")
	void test02()
	{
		assertThat(FettfreiesKoerpergewicht.aus(new BigDecimal(90), new BigDecimal(25)))
			.isEqualTo(new BigDecimal("67.50"));
	}
}
