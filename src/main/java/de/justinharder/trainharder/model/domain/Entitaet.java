package de.justinharder.trainharder.model.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.MappedSuperclass;

import com.google.common.base.MoreObjects;

@MappedSuperclass
public abstract class Entitaet implements Serializable
{
	private static final long serialVersionUID = 790786817201854580L;

	public abstract int getId();

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final var other = (Entitaet) obj;
		return getId() == other.getId();
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(getId());
	}

	@Override
	public String toString()
	{
		return MoreObjects
			.toStringHelper(this)
			.add("ID", getId())
			.toString();
	}
}
