package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class KraftwertDto extends EntitaetDto
{
	@Serial
	private static final long serialVersionUID = -5177169492291346152L;

	@NonNull
	private final String datum;

	@NonNull
	private final String gewicht;

	@NonNull
	private final String wiederholungen;

	public KraftwertDto(
		String id,
		@NonNull String datum,
		@NonNull String gewicht,
		@NonNull String wiederholungen)
	{
		super(id);
		this.datum = datum;
		this.gewicht = gewicht;
		this.wiederholungen = wiederholungen;
	}

	@Override
	public KraftwertDto setId(@NonNull String id)
	{
		this.id = id;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof KraftwertDto that))
		{
			return false;
		}
		return Objects.equals(id, that.id)
			&& Objects.equals(gewicht, that.gewicht)
			&& Objects.equals(datum, that.datum)
			&& Objects.equals(wiederholungen, that.wiederholungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, gewicht, datum, wiederholungen);
	}
}
