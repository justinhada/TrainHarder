package de.justinharder.trainharder.domain.services.berechnung;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Backoff sollte")
class BackoffTest
{
	private Backoff sut;

	@BeforeEach
	void setup()
	{
		sut = Backoff.aus(100, 5, 2);
	}

	@Test
	@DisplayName("Eingabe validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(IllegalArgumentException.class, () -> Backoff.aus(0, 5, 2)),
			() -> assertThrows(IllegalArgumentException.class, () -> Backoff.aus(100, 0, 2)),
			() -> assertThrows(IllegalArgumentException.class, () -> Backoff.aus(100, 5, -1)));
	}

	@Test
	@DisplayName("sich richtig auswerten")
	void test02()
	{
		assertAll(
			() -> assertThat(Backoff.aus(100, 5, 2).getRichtwert()).isEqualTo(82),
			() -> assertThat(Backoff.aus(140, 1, 0).getRichtwert()).isEqualTo(140),
			() -> assertThat(Backoff.aus(400, 3, 3).getRichtwert()).isEqualTo(340),
			() -> assertThat(Backoff.aus(95, 8, 4).getRichtwert()).isEqualTo(70));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getOneRepMax()).isEqualTo(100),
			() -> assertThat(sut.getWiederholungen()).isEqualTo(5),
			() -> assertThat(sut.getRepsInReserve()).isEqualTo(2),
			() -> assertThat(sut.getRichtwert()).isEqualTo(82));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test04()
	{
		EqualsVerifier.forClass(Backoff.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test05()
	{
		assertThat(sut).hasToString("Backoff(oneRepMax=100, wiederholungen=5, repsInReserve=2, richtwert=82)");
	}
}
