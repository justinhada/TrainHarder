package de.justinharder.trainharder.domain.model.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@Embeddable
public class GrunduebungBelastung implements Serializable
{
	private static final long serialVersionUID = 7260418704311243824L;

	@Column(name = "Squat", nullable = false)
	private double squat;
	@Column(name = "Benchpress", nullable = false)
	private double benchpress;
	@Column(name = "Deadlift", nullable = false)
	private double deadlift;

	public GrunduebungBelastung()
	{}

	public GrunduebungBelastung(double squat, double benchpress, double deadlift)
	{
		this.squat = squat;
		this.benchpress = benchpress;
		this.deadlift = deadlift;
	}

	public GrunduebungBelastung setSquat(double squat)
	{
		this.squat = squat;
		return this;
	}

	public GrunduebungBelastung setBenchpress(double benchpress)
	{
		this.benchpress = benchpress;
		return this;
	}

	public GrunduebungBelastung setDeadlift(double deadlift)
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
		if (!(o instanceof GrunduebungBelastung))
		{
			return false;
		}
		var that = (GrunduebungBelastung) o;
		return Double.compare(that.squat, squat) == 0 && Double.compare(that.benchpress, benchpress) == 0 && Double.compare(that.deadlift, deadlift) == 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(squat, benchpress, deadlift);
	}
}
