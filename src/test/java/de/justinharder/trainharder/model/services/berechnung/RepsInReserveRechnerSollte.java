package de.justinharder.trainharder.model.services.berechnung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigesMaximumException;
import de.justinharder.trainharder.model.services.berechnung.RepsInReserveRechner;

class RepsInReserveRechnerSollte
{
	private static final int GUELTIGES_MAXIMUM = 100;
	private static final int GUELTIGE_WIEDERHOLUNGEN = 5;
	private static final int GUELTIGE_RIR = 2;
	private static final int UNGUELTIGES_MAXIMUM = 0;
	private static final int UNGUELTIGE_WIEDERHOLUNGEN = 15;
	private static final int UNGUELTIGE_RIR = 6;

	private RepsInReserveRechner sut;

	@BeforeEach
	void setup()
	{
		sut = new RepsInReserveRechner();
	}

	@Test
	@DisplayName("UngueltigesMaximumException werfen, wenn Maximum ungültig ist")
	void test01()
	{
		final var e = assertThrows(UngueltigesMaximumException.class,
			() -> sut.berechneRichtwert(UNGUELTIGES_MAXIMUM, GUELTIGE_WIEDERHOLUNGEN, GUELTIGE_RIR));

		assertThat(e.getMessage()).isEqualTo("Du bist leider zu schwach, um überhaupt mit dem Training zu beginnen!");
	}

	@Test
	@DisplayName("UngueltigeWiederholungenException werfen, wenn Wiederholungen ungültig sind")
	void test02()
	{
		final var e = assertThrows(UngueltigeWiederholungenException.class,
			() -> sut.berechneRichtwert(GUELTIGES_MAXIMUM, UNGUELTIGE_WIEDERHOLUNGEN, GUELTIGE_RIR));

		assertThat(e.getMessage())
			.isEqualTo("Die Wiederholungszahl (" + UNGUELTIGE_WIEDERHOLUNGEN + ") ist leider ungültig!");
	}

	@Test
	@DisplayName("UngueltigeRepsInReserveException werfen, wenn RIR ungültig sind")
	void test03()
	{
		final var e = assertThrows(UngueltigeRepsInReserveException.class,
			() -> sut.berechneRichtwert(GUELTIGES_MAXIMUM, GUELTIGE_WIEDERHOLUNGEN, UNGUELTIGE_RIR));

		assertThat(e.getMessage())
			.isEqualTo("Die RIR-Zahl (" + UNGUELTIGE_RIR + ") ist ungültig!");
	}

	@Test
	@DisplayName("richtige Richtwerte berechnen")
	void test04() throws Exception
	{
		assertAll("richtige Richtwerte für alle Beispiele berechnen",
			() -> assertThat(sut.berechneRichtwert(100, 5, 2)).isEqualTo(82),
			() -> assertThat(sut.berechneRichtwert(140, 1, 0)).isEqualTo(140),
			() -> assertThat(sut.berechneRichtwert(400, 3, 3)).isEqualTo(340),
			() -> assertThat(sut.berechneRichtwert(95, 12, 4)).isEqualTo(64));
	}
}
