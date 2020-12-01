package de.justinharder.trainharder.model.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class Entitaet implements Serializable
{
	private static final long serialVersionUID = 790786817201854580L;

	public abstract Primaerschluessel getPrimaerschluessel();

	@Override
	public boolean equals(Object obj)
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
		var other = (Entitaet) obj;
		return Objects.equal(getPrimaerschluessel().getId(), other.getPrimaerschluessel().getId());
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
