package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class NameDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = -2811402786453050598L;

	private String vorname;
	private String nachname;

	public NameDto()
	{}

	public NameDto(@NonNull String vorname, @NonNull String nachname)
	{
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public NameDto setVorname(@NonNull String vorname)
	{
		this.vorname = vorname;
		return this;
	}

	public NameDto setNachname(@NonNull String nachname)
	{
		this.nachname = nachname;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof NameDto))
		{
			return false;
		}
		var that = (NameDto) o;
		return Objects.equals(vorname, that.vorname) && Objects.equals(nachname, that.nachname);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(vorname, nachname);
	}
}
