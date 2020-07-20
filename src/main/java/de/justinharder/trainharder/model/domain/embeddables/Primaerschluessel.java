package de.justinharder.trainharder.model.domain.embeddables;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import de.justinharder.trainharder.model.UuidMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Primaerschluessel implements Serializable
{
	private static final long serialVersionUID = -7279995861374733781L;

	@Column(columnDefinition = "VARCHAR(36)")
	@Convert(converter = UuidMapper.class)
	private UUID id;

	public Primaerschluessel()
	{
		this(UUID.randomUUID());
	}

	public Primaerschluessel(final String id)
	{
		this(UUID.fromString(id));
	}

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
		final var other = (Primaerschluessel) obj;
		return Objects.equal(getId(), other.getId());
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
