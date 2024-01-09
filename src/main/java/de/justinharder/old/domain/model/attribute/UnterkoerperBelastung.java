package de.justinharder.old.domain.model.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@Embeddable
public class UnterkoerperBelastung implements Serializable
{
	private static final long serialVersionUID = 9052323312691799570L;

	@Column(name = "Glutes", nullable = false)
	private double glutes;
	@Column(name = "Quads", nullable = false)
	private double quads;
	@Column(name = "Hamstrings", nullable = false)
	private double hamstrings;

	public UnterkoerperBelastung()
	{}

	public UnterkoerperBelastung(double glutes, double quads, double hamstrings)
	{
		this.glutes = glutes;
		this.quads = quads;
		this.hamstrings = hamstrings;
	}

	public UnterkoerperBelastung setGlutes(double glutes)
	{
		this.glutes = glutes;
		return this;
	}

	public UnterkoerperBelastung setQuads(double quads)
	{
		this.quads = quads;
		return this;
	}

	public UnterkoerperBelastung setHamstrings(double hamstrings)
	{
		this.hamstrings = hamstrings;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof UnterkoerperBelastung))
		{
			return false;
		}
		var that = (UnterkoerperBelastung) o;
		return Double.compare(that.glutes, glutes) == 0
			&& Double.compare(that.quads, quads) == 0
			&& Double.compare(that.hamstrings, hamstrings) == 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(glutes, quads, hamstrings);
	}
}
