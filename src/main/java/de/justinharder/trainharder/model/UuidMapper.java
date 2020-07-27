package de.justinharder.trainharder.model;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UuidMapper implements AttributeConverter<UUID, String>
{
	@Override
	public String convertToDatabaseColumn(final UUID uuid)
	{
		return uuid == null ? null : uuid.toString();
	}

	@Override
	public UUID convertToEntityAttribute(final String uuid)
	{
		return uuid == null ? null : UUID.fromString(uuid);
	}
}
