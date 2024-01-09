package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class GrunduebungBelastungDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = -2965819708753449763L;

	private String squat;
	private String benchpress;
	private String deadlift;

	public GrunduebungBelastungDto()
	{}

	public GrunduebungBelastungDto(@NonNull String squat, @NonNull String benchpress, @NonNull String deadlift)
	{
		this.squat = squat;
		this.benchpress = benchpress;
		this.deadlift = deadlift;
	}

	public GrunduebungBelastungDto setSquat(@NonNull String squat)
	{
		this.squat = squat;
		return this;
	}

	public GrunduebungBelastungDto setBenchpress(@NonNull String benchpress)
	{
		this.benchpress = benchpress;
		return this;
	}

	public GrunduebungBelastungDto setDeadlift(@NonNull String deadlift)
	{
		this.deadlift = deadlift;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof GrunduebungBelastungDto))
		{
			return false;
		}
		var that = (GrunduebungBelastungDto) o;
		return Objects.equals(squat, that.squat)
			&& Objects.equals(benchpress, that.benchpress)
			&& Objects.equals(deadlift, that.deadlift);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(squat, benchpress, deadlift);
	}
}
