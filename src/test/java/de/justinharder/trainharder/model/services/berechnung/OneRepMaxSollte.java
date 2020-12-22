package de.justinharder.trainharder.model.services.berechnung;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OneRepMaxSollte
{
	private OneRepMax sut;

	@BeforeEach
	void setup()
	{
		sut = OneRepMax.aus(100, 5, 2);
	}

	@Test
	@DisplayName("Eingabe validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(IllegalArgumentException.class, () -> OneRepMax.aus(0, 5, 2)),
			() -> assertThrows(IllegalArgumentException.class, () -> OneRepMax.aus(100, -1, 2)),
			() -> assertThrows(IllegalArgumentException.class, () -> OneRepMax.aus(100, 0, 2)),
			() -> assertThrows(IllegalArgumentException.class, () -> OneRepMax.aus(100, 9, 2)),
			() -> assertThrows(IllegalArgumentException.class, () -> OneRepMax.aus(100, 5, -1)),
			() -> assertThrows(IllegalArgumentException.class, () -> OneRepMax.aus(100, 5, 5)));
	}

	@Test
	@DisplayName("sich richtig auswerten")
	void test02()
	{
		assertAll(
			() -> assertThat(OneRepMax.aus(100, 5, 2).getRichtwert()).isEqualTo(122),
			() -> assertThat(OneRepMax.aus(140, 1, 0).getRichtwert()).isEqualTo(140),
			() -> assertThat(OneRepMax.aus(400, 3, 3).getRichtwert()).isEqualTo(476),
			() -> assertThat(OneRepMax.aus(95, 8, 4).getRichtwert()).isEqualTo(138));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getGewicht()).isEqualTo(100),
			() -> assertThat(sut.getWiederholungen()).isEqualTo(5),
			() -> assertThat(sut.getRepsInReserve()).isEqualTo(2),
			() -> assertThat(sut.getRichtwert()).isEqualTo(122));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test04()
	{
		EqualsVerifier.forClass(OneRepMax.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test05()
	{
		var erwartet = "OneRepMax(gewicht=100, wiederholungen=5, repsInReserve=2, richtwert=122)";

		assertThat(sut).hasToString(erwartet);
	}
}
