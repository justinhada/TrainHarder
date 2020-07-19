package de.justinharder.trainharder.model.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.MappedSuperclass;

import com.google.common.base.MoreObjects;

@MappedSuperclass
public abstract class Entitaet implements Serializable
{
	private static final long serialVersionUID = 790786817201854580L;

	public abstract Primaerschluessel getPrimaerschluessel();

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
		return Objects.equals(getPrimaerschluessel().getId(), other.getPrimaerschluessel().getId());
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(getPrimaerschluessel().getId());
	}

	@Override
	public String toString()
	{
		return MoreObjects
			.toStringHelper(this)
			.add("ID", getPrimaerschluessel().getId())
			.toString();
	}
}
