package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class BelastungsfaktorDto extends Dto
{
	private static final long serialVersionUID = -2050880743329267381L;

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
		@NonNull String primaerschluessel,
		double squat,
		double benchpress,
		double deadlift,
		double triceps,
		double chest,
		double core,
		double back,
		double biceps,
		double glutes,
		double quads,
		double hamstrings,
		double shoulder)
	{
		super(primaerschluessel);
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

	@Override
	public BelastungsfaktorDto setPrimaerschluessel(@NonNull String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public BelastungsfaktorDto setSquat(double squat)
	{
		this.squat = squat;
		return this;
	}

	public BelastungsfaktorDto setBenchpress(double benchpress)
	{
		this.benchpress = benchpress;
		return this;
	}

	public BelastungsfaktorDto setDeadlift(double deadlift)
	{
		this.deadlift = deadlift;
		return this;
	}

	public BelastungsfaktorDto setTriceps(double triceps)
	{
		this.triceps = triceps;
		return this;
	}

	public BelastungsfaktorDto setChest(double chest)
	{
		this.chest = chest;
		return this;
	}

	public BelastungsfaktorDto setCore(double core)
	{
		this.core = core;
		return this;
	}

	public BelastungsfaktorDto setBack(double back)
	{
		this.back = back;
		return this;
	}

	public BelastungsfaktorDto setBiceps(double biceps)
	{
		this.biceps = biceps;
		return this;
	}

	public BelastungsfaktorDto setGlutes(double glutes)
	{
		this.glutes = glutes;
		return this;
	}

	public BelastungsfaktorDto setQuads(double quads)
	{
		this.quads = quads;
		return this;
	}

	public BelastungsfaktorDto setHamstrings(double hamstrings)
	{
		this.hamstrings = hamstrings;
		return this;
	}

	public BelastungsfaktorDto setShoulder(double shoulder)
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
		if (!(o instanceof BelastungsfaktorDto))
		{
			return false;
		}
		var that = (BelastungsfaktorDto) o;
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
		return Objects.hash(primaerschluessel, squat, benchpress, deadlift, triceps, chest, core, back, biceps, glutes,
			quads, hamstrings, shoulder);
	}
}
