package de.justinharder.trainharder.model.domain.embeddables;

import com.google.common.base.MoreObjects;
import de.justinharder.trainharder.model.UuidMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Embeddable
public class Primaerschluessel implements Serializable
{
	private static final long serialVersionUID = -7279995861374733781L;

	@Convert(converter = UuidMapper.class)
	@Column(columnDefinition = "VARCHAR(36)")
	private UUID id;

	public Primaerschluessel()
	{
		this(UUID.randomUUID());
	}

	public Primaerschluessel(UUID id)
	{
		this.id = id;
	}

	public Primaerschluessel(String id)
	{
		this(UUID.fromString(id));
	}

	public Primaerschluessel setId(UUID id)
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
		if (!(o instanceof Primaerschluessel))
		{
			return false;
		}
		var that = (Primaerschluessel) o;
		return Objects.equals(id, that.id);
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
