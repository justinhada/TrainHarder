package de.justinharder.trainharder.model.services.berechnung.koerpermasse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FatFreeMassIndexSollte
{
	@Test
	@DisplayName("null validieren")
	void test01()
	{
		var koerpergewicht = new BigDecimal(90);
		var koerperfettAnteil = new BigDecimal(20);
		var koerpergroesse = new BigDecimal(178);
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> FatFreeMassIndex.aus(null, koerperfettAnteil, koerpergroesse)),
			() -> assertThrows(NullPointerException.class, () -> FatFreeMassIndex.aus(koerpergewicht, null, koerpergroesse)),
			() -> assertThrows(NullPointerException.class, () -> FatFreeMassIndex.aus(koerpergewicht, koerperfettAnteil, null)));
	}

	@Test
	@DisplayName("berechnet werden")
	void test02()
	{
		assertThat(FatFreeMassIndex.aus(new BigDecimal(90), new BigDecimal(25), new BigDecimal(178))).isEqualTo(new BigDecimal("21.42"));
	}
}