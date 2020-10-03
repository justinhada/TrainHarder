package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class BelastungsfaktorDto implements Serializable
{
	private static final long serialVersionUID = -2050880743329267381L;

	private String primaerschluessel;
	private double squat;
	private double benchpress;
	private double deadlift;
	private double triceps;
	private double chest;
	private double core;
	private double back;
	private double biceps;
	private double glutes;
	private double quads;
	private double hamstrings;
	private double shoulder;

	public BelastungsfaktorDto()
	{}

	public BelastungsfaktorDto(
		final String primaerschluessel,
		final double squat,
		final double benchpress,
		final double deadlift,
		final double triceps,
		final double chest,
		final double core,
		final double back,
		final double biceps,
		final double glutes,
		final double quads,
		final double hamstrings,
		final double shoulder)
	{
		this.primaerschluessel = primaerschluessel;
		this.squat = squat;
		this.benchpress = benchpress;
		this.deadlift = deadlift;
		this.triceps = triceps;
		this.chest = chest;
		this.core = core;
		this.back = back;
		this.biceps = biceps;
		this.glutes = glutes;
		this.quads = quads;
		this.hamstrings = hamstrings;
		this.shoulder = shoulder;
	}

	public BelastungsfaktorDto setPrimaerschluessel(final String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public BelastungsfaktorDto setSquat(final double squat)
	{
		this.squat = squat;
		return this;
	}

	public BelastungsfaktorDto setBenchpress(final double benchpress)
	{
		this.benchpress = benchpress;
		return this;
	}

	public BelastungsfaktorDto setDeadlift(final double deadlift)
	{
		this.deadlift = deadlift;
		return this;
	}

	public BelastungsfaktorDto setTriceps(final double triceps)
	{
		this.triceps = triceps;
		return this;
	}

	public BelastungsfaktorDto setChest(final double chest)
	{
		this.chest = chest;
		return this;
	}

	public BelastungsfaktorDto setCore(final double core)
	{
		this.core = core;
		return this;
	}

	public BelastungsfaktorDto setBack(final double back)
	{
		this.back = back;
		return this;
	}

	public BelastungsfaktorDto setBiceps(final double biceps)
	{
		this.biceps = biceps;
		return this;
	}

	public BelastungsfaktorDto setGlutes(final double glutes)
	{
		this.glutes = glutes;
		return this;
	}

	public BelastungsfaktorDto setQuads(final double quads)
	{
		this.quads = quads;
		return this;
	}

	public BelastungsfaktorDto setHamstrings(final double hamstrings)
	{
		this.hamstrings = hamstrings;
		return this;
	}

	public BelastungsfaktorDto setShoulder(final double shoulder)
	{
		this.shoulder = shoulder;
		return this;
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
		BelastungsfaktorDto that = (BelastungsfaktorDto) o;
		return Double.compare(that.squat, squat) == 0 &&
			Double.compare(that.benchpress, benchpress) == 0 &&
			Double.compare(that.deadlift, deadlift) == 0 &&
			Double.compare(that.triceps, triceps) == 0 &&
			Double.compare(that.chest, chest) == 0 &&
			Double.compare(that.core, core) == 0 &&
			Double.compare(that.back, back) == 0 &&
			Double.compare(that.biceps, biceps) == 0 &&
			Double.compare(that.glutes, glutes) == 0 &&
			Double.compare(that.quads, quads) == 0 &&
			Double.compare(that.hamstrings, hamstrings) == 0 &&
			Double.compare(that.shoulder, shoulder) == 0 &&
			primaerschluessel.equals(that.primaerschluessel);
	}

	@Override
	public int hashCode()
	{
		return Objects
			.hash(primaerschluessel, squat, benchpress, deadlift, triceps, chest, core, back, biceps, glutes, quads,
				hamstrings, shoulder);
	}
}
