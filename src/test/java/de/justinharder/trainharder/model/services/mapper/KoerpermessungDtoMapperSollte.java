package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KoerpermessungDtoMapperSollte
{
	private KoerpermessungDtoMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new KoerpermessungDtoMapper();
	}

	@Test
	@DisplayName("alle Koerpermessungen zu KoerpermessungDtos mappen")
	void test01()
	{
		var erwartet = List.of(Testdaten.KOERPERMESSUNG_DTO_JUSTIN, Testdaten.KOERPERMESSUNG_DTO_EDUARD);
		var koerpermessungen = List.of(Testdaten.KOERPERMESSUNG_JUSTIN, Testdaten.KOERPERMESSUNG_EDUARD);

		var ergebnis = sut.mappeAlle(koerpermessungen);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test02()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.mappeAlle(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.mappe(null)));
	}
}
