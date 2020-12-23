package de.justinharder.trainharder.model.domain.embeddables;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import de.justinharder.trainharder.model.UuidMapper;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;
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
		return Objects.equal(getId(), that.getId());
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
