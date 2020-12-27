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
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> FatFreeMassIndex.aus(null, new BigDecimal(20), new BigDecimal(178))),
			() -> assertThrows(NullPointerException.class,
				() -> FatFreeMassIndex.aus(new BigDecimal(90), null, new BigDecimal(178))),
			() -> assertThrows(NullPointerException.class,
				() -> FatFreeMassIndex.aus(new BigDecimal(90), new BigDecimal(20), null)));
	}

	@Test
	@DisplayName("berechnet werden")
	void test02()
	{
		assertThat(FatFreeMassIndex.aus(new BigDecimal(90), new BigDecimal(25), new BigDecimal(178)))
			.isEqualTo(new BigDecimal("21.422"));
	}
}
