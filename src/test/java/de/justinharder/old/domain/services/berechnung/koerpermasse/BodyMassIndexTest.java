package de.justinharder.old.domain.services.berechnung.koerpermasse;

import de.justinharder.old.domain.services.berechnung.koerpermasse.BodyMassIndex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BodyMassIndex sollte")
class BodyMassIndexTest
{
	private static final BigDecimal KOERPERGEWICHT = new BigDecimal(90);
	private static final BigDecimal KOERPERGROESSE = new BigDecimal(178);

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> BodyMassIndex.aus(null, KOERPERGROESSE)),
			() -> assertThrows(NullPointerException.class, () -> BodyMassIndex.aus(KOERPERGEWICHT, null)));
	}

	@Test
	@DisplayName("berechnet werden")
	void test02()
	{
		assertThat(BodyMassIndex.aus(KOERPERGEWICHT, KOERPERGROESSE)).isEqualTo(new BigDecimal("28.41"));
	}
}
