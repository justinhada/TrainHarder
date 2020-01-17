package de.justinharder.powerlifting.model.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BelastungsfaktorEintrag
{
	private int id;
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
}
