package de.justinharder.trainharder.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UuidMapperSollte
{
	private UuidMapper sut;

	@BeforeEach
	void setup()
	{
		sut = new UuidMapper();
	}

	@Test
	@DisplayName("UUID zu String konvertieren")
	void test01()
	{
		final var uuid = UUID.randomUUID();

		assertAll(
			() -> assertThat(sut.convertToDatabaseColumn(null)).isEqualTo(null),
			() -> assertThat(sut.convertToDatabaseColumn(uuid)).isEqualTo(uuid.toString()));
	}

	@Test
	@DisplayName("String zu UUID konvertieren")
	void test02()
	{
		final var uuid = UUID.randomUUID().toString();

		assertAll(
			() -> assertThat(sut.convertToEntityAttribute(null)).isEqualTo(null),
			() -> assertThat(sut.convertToEntityAttribute(uuid)).isEqualTo(UUID.fromString(uuid)));
	}
}
