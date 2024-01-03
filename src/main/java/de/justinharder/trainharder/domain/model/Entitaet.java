package de.justinharder.trainharder.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class Entitaet implements Serializable
{
	private static final long serialVersionUID = 790786817201854580L;

	public abstract Primaerschluessel getPrimaerschluessel();

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Entitaet))
		{
			return false;
		}
		var that = (Entitaet) o;
		return Objects.equals(getPrimaerschluessel().getId(), that.getPrimaerschluessel().getId());
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
