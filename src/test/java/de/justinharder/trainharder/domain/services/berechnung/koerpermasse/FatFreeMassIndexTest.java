package de.justinharder.trainharder.domain.services.berechnung.koerpermasse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("FatFreeMassIndex sollte")
class FatFreeMassIndexTest
{
	private static final BigDecimal KOERPERGEWICHT = new BigDecimal(90);
	private static final BigDecimal KOERPERFETT_ANTEIL = new BigDecimal(25);
	private static final BigDecimal KOERPERGROESSE = new BigDecimal(178);

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> FatFreeMassIndex.aus(null, KOERPERFETT_ANTEIL, KOERPERGROESSE)),
			() -> assertThrows(NullPointerException.class,
				() -> FatFreeMassIndex.aus(KOERPERGEWICHT, null, KOERPERGROESSE)),
			() -> assertThrows(NullPointerException.class,
				() -> FatFreeMassIndex.aus(KOERPERGEWICHT, KOERPERFETT_ANTEIL, null)));
	}

	@Test
	@DisplayName("berechnet werden")
	void test02()
	{
		assertThat(FatFreeMassIndex.aus(KOERPERGEWICHT, KOERPERFETT_ANTEIL, KOERPERGROESSE))
			.isEqualTo(new BigDecimal("21.42"));
	}
}
