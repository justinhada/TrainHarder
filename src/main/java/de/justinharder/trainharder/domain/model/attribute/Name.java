package de.justinharder.trainharder.domain.model.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@Embeddable
public class Name implements Serializable
{
	private static final long serialVersionUID = 5815817146290998651L;

	@Column(name = "Vorname")
	private String vorname;
	@Column(name = "Nachname")
	private String nachname;

	public Name()
	{}

	public Name(@NonNull String vorname, @NonNull String nachname)
	{
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public Name setVorname(@NonNull String vorname)
	{
		this.vorname = vorname;
		return this;
	}

	public Name setNachname(@NonNull String nachname)
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
		if (!(o instanceof Name))
		{
			return false;
		}
		var name = (Name) o;
		return Objects.equals(vorname, name.vorname) && Objects.equals(nachname, name.nachname);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(vorname, nachname);
	}
}
