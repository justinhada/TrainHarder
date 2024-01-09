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
public class OberkoerperBelastung implements Serializable
{
	private static final long serialVersionUID = 4162901587327777222L;

	@Column(name = "Triceps", nullable = false)
	private double triceps;
	@Column(name = "Chest", nullable = false)
	private double chest;
	@Column(name = "Core", nullable = false)
	private double core;
	@Column(name = "Back", nullable = false)
	private double back;
	@Column(name = "Biceps", nullable = false)
	private double biceps;
	@Column(name = "Shoulder", nullable = false)
	private double shoulder;

	public OberkoerperBelastung()
	{}

	public OberkoerperBelastung(double triceps, double chest, double core, double back, double biceps, double shoulder)
	{
		this.triceps = triceps;
		this.chest = chest;
		this.core = core;
		this.back = back;
		this.biceps = biceps;
		this.shoulder = shoulder;
	}

	public OberkoerperBelastung setTriceps(double triceps)
	{
		this.triceps = triceps;
		return this;
	}

	public OberkoerperBelastung setChest(double chest)
	{
		this.chest = chest;
		return this;
	}

	public OberkoerperBelastung setCore(double core)
	{
		this.core = core;
		return this;
	}

	public OberkoerperBelastung setBack(double back)
	{
		this.back = back;
		return this;
	}

	public OberkoerperBelastung setBiceps(double biceps)
	{
		this.biceps = biceps;
		return this;
	}

	public OberkoerperBelastung setShoulder(double shoulder)
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
		if (!(o instanceof OberkoerperBelastung))
		{
			return false;
		}
		var that = (OberkoerperBelastung) o;
		return Double.compare(that.triceps, triceps) == 0
			&& Double.compare(that.chest, chest) == 0
			&& Double.compare(that.core, core) == 0
			&& Double.compare(that.back, back) == 0
			&& Double.compare(that.biceps, biceps) == 0
			&& Double.compare(that.shoulder, shoulder) == 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(triceps, chest, core, back, biceps, shoulder);
	}
}
