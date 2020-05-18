package de.justinharder.trainharder.model;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UuidKonvertierer implements AttributeConverter<UUID, String>
{
	@Override
	public String convertToDatabaseColumn(final UUID uuid)
	{
		if (uuid == null)
		{
			return null;
		}
		return uuid.toString();
	}

	@Override
	public UUID convertToEntityAttribute(final String uuid)
	{
		if (uuid == null)
		{
			return null;
		}
		return UUID.fromString(uuid);
	}
}
