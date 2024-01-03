package de.justinharder.trainharder.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UUIDMappingSollte
{
	private UUIDMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new UUIDMapping();
	}

	@Test
	@DisplayName("UUID zu String mappen")
	void test01()
	{
		var uuid = UUID.randomUUID();

		assertAll(
			() -> assertThat(sut.convertToDatabaseColumn(null)).isNull(),
			() -> assertThat(sut.convertToDatabaseColumn(uuid)).isEqualTo(uuid.toString()));
	}

	@Test
	@DisplayName("String zu UUID mappen")
	void test02()
	{
		var uuid = UUID.randomUUID().toString();

		assertAll(
			() -> assertThat(sut.convertToEntityAttribute(null)).isNull(),
			() -> assertThat(sut.convertToEntityAttribute(uuid)).isEqualTo(UUID.fromString(uuid)));
	}
}
