package de.justinharder.powerlifting.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Belastungsfaktor extends Entitaet
{
	private static final long serialVersionUID = 5251603922467033680L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Uebung uebung;

	public Belastungsfaktor(
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
}
