package de.justinharder.trainharder.model.services.berechnung;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OneRepMaxSollte
{
	@Test
	@DisplayName("sich richtig auswerten")
	void test01()
	{
		assertAll(
			() -> assertThat(OneRepMax.aus(100, 5, 2).getRichtwert()).isEqualTo(122.7),
			() -> assertThat(OneRepMax.aus(140, 1, 0).getRichtwert()).isEqualTo(140.0),
			() -> assertThat(OneRepMax.aus(400, 3, 3).getRichtwert()).isEqualTo(476.2),
			() -> assertThat(OneRepMax.aus(95, 8, 4).getRichtwert()).isEqualTo(138.7));
	}
}
