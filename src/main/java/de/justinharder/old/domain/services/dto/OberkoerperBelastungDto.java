package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class OberkoerperBelastungDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = 9105831153980639941L;

	private String triceps;
	private String chest;
	private String core;
	private String back;
	private String biceps;
	private String shoulder;

	public OberkoerperBelastungDto()
	{}

	public OberkoerperBelastungDto(@NonNull String triceps, @NonNull String chest, @NonNull String core, @NonNull String back, @NonNull String biceps, @NonNull String shoulder)
	{
		this.triceps = triceps;
		this.chest = chest;
		this.core = core;
		this.back = back;
		this.biceps = biceps;
		this.shoulder = shoulder;
	}

	public OberkoerperBelastungDto setTriceps(@NonNull String triceps)
	{
		this.triceps = triceps;
		return this;
	}

	public OberkoerperBelastungDto setChest(@NonNull String chest)
	{
		this.chest = chest;
		return this;
	}

	public OberkoerperBelastungDto setCore(@NonNull String core)
	{
		this.core = core;
		return this;
	}

	public OberkoerperBelastungDto setBack(@NonNull String back)
	{
		this.back = back;
		return this;
	}

	public OberkoerperBelastungDto setBiceps(@NonNull String biceps)
	{
		this.biceps = biceps;
		return this;
	}

	public OberkoerperBelastungDto setShoulder(@NonNull String shoulder)
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
		if (!(o instanceof OberkoerperBelastungDto))
		{
			return false;
		}
		var that = (OberkoerperBelastungDto) o;
		return Objects.equals(triceps, that.triceps)
			&& Objects.equals(chest, that.chest)
			&& Objects.equals(core, that.core)
			&& Objects.equals(back, that.back)
			&& Objects.equals(biceps, that.biceps)
			&& Objects.equals(shoulder, that.shoulder);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(triceps, chest, core, back, biceps, shoulder);
	}
}
