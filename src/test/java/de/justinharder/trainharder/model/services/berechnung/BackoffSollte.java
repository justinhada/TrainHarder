package de.justinharder.trainharder.model.services.berechnung;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BackoffSollte
{
	@Test
	@DisplayName("sich richtig auswerten")
	void test01()
	{
		assertAll(
			() -> assertThat(Backoff.aus(100, 5, 2).getRichtwert()).isEqualTo(82),
			() -> assertThat(Backoff.aus(140, 1, 0).getRichtwert()).isEqualTo(140),
			() -> assertThat(Backoff.aus(400, 3, 3).getRichtwert()).isEqualTo(340),
			() -> assertThat(Backoff.aus(95, 8, 4).getRichtwert()).isEqualTo(70));
	}
}
