package de.justinharder.trainharder.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class UnterkoerperBelastungDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = 563737977829644662L;

	private String glutes;
	private String quads;
	private String hamstrings;

	public UnterkoerperBelastungDto()
	{}

	public UnterkoerperBelastungDto(@NonNull String glutes, @NonNull String quads, @NonNull String hamstrings)
	{
		this.glutes = glutes;
		this.quads = quads;
		this.hamstrings = hamstrings;
	}

	public UnterkoerperBelastungDto setGlutes(@NonNull String glutes)
	{
		this.glutes = glutes;
		return this;
	}

	public UnterkoerperBelastungDto setQuads(@NonNull String quads)
	{
		this.quads = quads;
		return this;
	}

	public UnterkoerperBelastungDto setHamstrings(@NonNull String hamstrings)
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
		if (!(o instanceof UnterkoerperBelastungDto))
		{
			return false;
		}
		var that = (UnterkoerperBelastungDto) o;
		return Objects.equals(glutes, that.glutes)
			&& Objects.equals(quads, that.quads)
			&& Objects.equals(hamstrings, that.hamstrings);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(glutes, quads, hamstrings);
	}
}
