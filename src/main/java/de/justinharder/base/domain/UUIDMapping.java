package de.justinharder.base.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class UUIDMapping implements AttributeConverter<UUID, String>
{
	@Override
	public String convertToDatabaseColumn(UUID uuid)
	{
		return uuid == null ? null : uuid.toString();
	}

	@Override
	public UUID convertToEntityAttribute(String uuid)
	{
		return uuid == null ? null : UUID.fromString(uuid);
	}
}
