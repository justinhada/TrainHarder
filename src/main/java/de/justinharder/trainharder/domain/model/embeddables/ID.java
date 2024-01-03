package de.justinharder.trainharder.domain.model.embeddables;

import com.google.common.base.MoreObjects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Embeddable
public class Primaerschluessel implements Serializable
{
	@Serial
	private static final long serialVersionUID = -7279995861374733781L;

	@NonNull
//	@Convert(converter = UuidMapper.class)
	@Column(name = "ID")
//	@Column(name = "ID", columnDefinition = "VARCHAR(36)")
	private UUID id;

	public Primaerschluessel()
	{
		this(UUID.randomUUID());
	}

	public Primaerschluessel(@NonNull UUID id)
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
		if (!(o instanceof Primaerschluessel that))
		{
			return false;
		}
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
