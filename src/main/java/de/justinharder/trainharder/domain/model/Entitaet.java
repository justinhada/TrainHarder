package de.justinharder.trainharder.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@MappedSuperclass
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Entitaet implements Serializable
{
	@Serial
	private static final long serialVersionUID = 790786817201854580L;

	@NonNull
	@EmbeddedId
	protected final ID id;

	protected Entitaet()
	{
		id = new ID();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		var entitaet = (Entitaet) o;
		return Objects.equals(id, entitaet.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public String toString()
	{
		return MoreObjects
			.toStringHelper(this)
			.add("ID", id)
			.toString();
	}
}
